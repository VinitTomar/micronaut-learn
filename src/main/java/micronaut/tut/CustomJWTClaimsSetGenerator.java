package micronaut.tut;

import javax.inject.Singleton;

import com.nimbusds.jwt.JWTClaimsSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.generator.claims.ClaimsAudienceProvider;
import io.micronaut.security.token.jwt.generator.claims.JWTClaimsSetGenerator;
import io.micronaut.security.token.jwt.generator.claims.JwtIdGenerator;

@Singleton
@Replaces(bean = JWTClaimsSetGenerator.class)
public class CustomJWTClaimsSetGenerator extends JWTClaimsSetGenerator {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


  public CustomJWTClaimsSetGenerator(
    TokenConfiguration tokenConfiguration,
    @Nullable JwtIdGenerator jwtIdGenerator,
    @Nullable ClaimsAudienceProvider claimsAudienceProvider,
    @Nullable ApplicationConfiguration applicationConfiguration
  ) {
    super(tokenConfiguration, jwtIdGenerator, claimsAudienceProvider, applicationConfiguration);
  }

  @Override
  protected void populateWithUserDetails(JWTClaimsSet.Builder builder, UserDetails userDetails) {
    super.populateWithUserDetails(builder, userDetails);
    logger.info("Username from detail: " + userDetails.getUsername());
  }
}