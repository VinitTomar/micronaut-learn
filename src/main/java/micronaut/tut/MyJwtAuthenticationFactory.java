package micronaut.tut;

import java.text.ParseException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import java.util.Map;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.validator.DefaultJwtAuthenticationFactory;
import io.micronaut.security.token.jwt.validator.JwtAuthenticationFactory;


@Singleton
@Replaces(DefaultJwtAuthenticationFactory.class)
public class MyJwtAuthenticationFactory implements JwtAuthenticationFactory {


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
            System.out.println("My jwt authentication factory (create authentication)");
            return new MyAuthentication(claimSet.getClaims(), username);
          });
      } catch (ParseException e) {
          System.out.println("ParseException creating authentication");
      }
      return Optional.empty();
  }

  /**
   *
   * @param claimSet JWT Claims
   * @return the username defined by {@link TokenConfiguration#getNameKey()} ()} or the sub claim.
   * @throws ParseException might be thrown parsing claims
   */
  protected Optional<String> usernameForClaims(JWTClaimsSet claimSet) throws ParseException {
      String username = claimSet.getStringClaim(tokenConfiguration.getNameKey());
      if (username == null) {
          return Optional.ofNullable(claimSet.getSubject());
      }
      return Optional.of(username);
  }


}
