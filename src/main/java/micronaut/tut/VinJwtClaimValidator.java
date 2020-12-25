package micronaut.tut;

import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.token.jwt.generator.claims.JwtClaims;
import io.micronaut.security.token.jwt.validator.GenericJwtClaimsValidator;

@Singleton
public class VinJwtClaimValidator implements GenericJwtClaimsValidator {

	@Override
  public boolean validate(JwtClaims claims, HttpRequest<?> request) {
    System.out.println("validator with request => " + request);
    return true;
  }

	@Override
  public boolean validate(JwtClaims claims) {
    System.out.println("validator without request");
    return true;
	}
  
}
