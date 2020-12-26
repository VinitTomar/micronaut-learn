package micronaut.tut;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.token.jwt.encryption.EncryptionConfiguration;
import io.micronaut.security.token.jwt.signature.SignatureConfiguration;
import io.micronaut.security.token.jwt.validator.GenericJwtClaimsValidator;
import io.micronaut.security.token.jwt.validator.JwtAuthenticationFactory;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;


@Singleton
@Replaces(JwtTokenValidator.class)
public class MyJwtTokenValidator extends JwtTokenValidator {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


  @Inject
  UserRepository userRepository;

	public MyJwtTokenValidator(Collection<SignatureConfiguration> signatureConfigurations,
			Collection<EncryptionConfiguration> encryptionConfigurations,
			Collection<GenericJwtClaimsValidator> genericJwtClaimsValidators,
			JwtAuthenticationFactory jwtAuthenticationFactory) {
		super(signatureConfigurations, encryptionConfigurations, genericJwtClaimsValidators,jwtAuthenticationFactory);
	}
  

  @Override
  @Deprecated
  public Publisher<Authentication> validateToken(String token) {
    logger.info("My Jwt token validator");

    return Flowable.create(emitter -> {

      Optional<Authentication> authOptional = super.validator.validate(token)
      .flatMap(super.jwtAuthenticationFactory::createAuthentication);
      
      if (authOptional.isPresent()) {
        MyAuthentication auth = (MyAuthentication) authOptional.get();
        this.userRepository.findUserByName(auth.getName()).subscribe(usrOptnl -> {
          if(usrOptnl.isPresent()) {
            var usr = usrOptnl.get();
            logger.info("Curretn user: " + usr);
            auth.setCurrentUser(usr);
            logger.info("Auth current user: " + auth.getCurrentUser());
            emitter.onNext(auth);
          } else {
            emitter.onError(new AuthenticationException("User not found with this token"));
          }
        });
      } else {

        logger.info("Auth in my validator not present");

        emitter.onError(new AuthenticationException("Invalid token"));
      }
      
      emitter.onComplete();
    }, BackpressureStrategy.ERROR);
  }

}
