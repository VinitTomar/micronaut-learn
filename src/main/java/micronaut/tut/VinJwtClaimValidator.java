package micronaut.tut;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.token.jwt.generator.claims.JwtClaims;
import io.micronaut.security.token.jwt.validator.GenericJwtClaimsValidator;
import io.micronaut.security.utils.SecurityService;

@Singleton
public class VinJwtClaimValidator implements GenericJwtClaimsValidator {

  @Inject
  SecurityService securityService;

  @Override
  public boolean validate(JwtClaims claims, HttpRequest<?> request) {
    // var attr = this.securityService.getAuthentication().get().getAttributes();
    // System.out.println("Attribute => " + attr);
    System.out.println("validator with request => " + request);
    return true;
  }

  @Override
  public boolean validate(JwtClaims claims) {
    System.out.println("validator without request");
    return true;
	}
  
}
