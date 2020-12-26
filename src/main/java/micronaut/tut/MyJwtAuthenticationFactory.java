package micronaut.tut;

import java.text.ParseException;
import java.util.Optional;

import javax.inject.Singleton;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.validator.DefaultJwtAuthenticationFactory;
import io.micronaut.security.token.jwt.validator.JwtAuthenticationFactory;


@Singleton
@Replaces(DefaultJwtAuthenticationFactory.class)
public class MyJwtAuthenticationFactory implements JwtAuthenticationFactory {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


  private final TokenConfiguration tokenConfiguration;

  public MyJwtAuthenticationFactory(TokenConfiguration tokenConfiguration) {
      this.tokenConfiguration = tokenConfiguration;
  }

  @Override
  public Optional<Authentication> createAuthentication(JWT token) {
      try {
          final JWTClaimsSet claimSet = token.getJWTClaimsSet();
          if (claimSet == null) {
              return Optional.empty();
          }
          return usernameForClaims(claimSet).map(username -> {
            logger.info("My jwt authentication factory (create authentication)");
            return new MyAuthentication(claimSet.getClaims(), username);
          });
      } catch (ParseException e) {
          logger.info("ParseException creating authentication");
      }
      return Optional.empty();
  }

  protected Optional<String> usernameForClaims(JWTClaimsSet claimSet) throws ParseException {
      String username = claimSet.getStringClaim(tokenConfiguration.getNameKey());
      if (username == null) {
          return Optional.ofNullable(claimSet.getSubject());
      }
      return Optional.of(username);
  }


}
