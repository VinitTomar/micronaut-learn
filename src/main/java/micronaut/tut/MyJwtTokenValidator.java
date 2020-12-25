package micronaut.tut;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.nimbusds.jwt.JWTClaimsSet;

import org.reactivestreams.Publisher;

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
    System.out.println("My Jwt token validator");

    return Flowable.create(emitter -> {

      MyAuthentication auth = (MyAuthentication) super.validator.validate(token)
      .flatMap(super.jwtAuthenticationFactory::createAuthentication).get();

      this.userRepository.findUserByName(auth.getName()).subscribe(usrOptnl -> {
        if(usrOptnl.isPresent()) {
          var usr = usrOptnl.get();
          System.out.println("Curretn user: " + usr);
          auth.setCurrentUser(usr);
          System.out.println("Auth current user: " + auth.getCurrentUser());
          emitter.onNext(auth);
        } else {
          emitter.onError(new AuthenticationException("User not found with this token"));
        }
      });

      emitter.onComplete();
    }, BackpressureStrategy.ERROR);
  }

}
