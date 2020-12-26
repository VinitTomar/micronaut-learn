package micronaut.tut;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.token.jwt.generator.claims.JwtClaims;
import io.micronaut.security.token.jwt.validator.GenericJwtClaimsValidator;
import io.micronaut.security.utils.SecurityService;

@Singleton
public class VinJwtClaimValidator implements GenericJwtClaimsValidator {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


  @Inject
  SecurityService securityService;

  @Override
  public boolean validate(JwtClaims claims, HttpRequest<?> request) {
    // var attr = this.securityService.getAuthentication().get().getAttributes();
    // logger.info("Attribute => " + attr);
    logger.info("validator with request => " + request);
    return true;
  }

  @Override
  public boolean validate(JwtClaims claims) {
    logger.info("validator without request");
    return true;
	}
  
}
