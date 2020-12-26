package micronaut.tut;

import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.rules.SecuredAnnotationRule;
import io.micronaut.security.rules.SecurityRuleResult;
import io.micronaut.web.router.MethodBasedRouteMatch;
import io.micronaut.web.router.RouteMatch;

@Singleton
public class CheckForPidSecurityRule implements SecurityRule {
  public static final Integer ORDER = SecuredAnnotationRule.ORDER - 100;

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


	@Override
    public SecurityRuleResult check(HttpRequest<?> request, RouteMatch<?> routeMatch, Map<String, Object> claims) {
      logger.info("Claims in security rule: " + claims);
      if (routeMatch instanceof MethodBasedRouteMatch) {
        MethodBasedRouteMatch<?,?> methodBasedRouteMatch = (MethodBasedRouteMatch<?,?>) routeMatch;
        if (methodBasedRouteMatch.hasAnnotation(CheckForPid.class)) {
          AnnotationValue<CheckForPid> checkPidAnnotation = methodBasedRouteMatch.getAnnotation(CheckForPid.class);
          Optional<Boolean> value = checkPidAnnotation.booleanValue("ignore");

          logger.info("checkPidAnnotation: " + checkPidAnnotation);

          if (value.isEmpty()) {
            Map<String, Object> variablevalues = methodBasedRouteMatch.getVariableValues();
            logger.info("RouterMachVariable values: " + variablevalues);

            logger.info("Security rule request: " + request);

            Integer pid = Integer.parseInt((String) variablevalues.get("pid"));

            logger.info("Pid in security rule: " + pid);

            if (pid == 3) {
              return SecurityRuleResult.ALLOWED;

            } else {
              return SecurityRuleResult.REJECTED;
            }

          }

          if (value.isPresent() && value.get()) {
            return SecurityRuleResult.ALLOWED;
          }

          logger.info("Value not present");

          // Optional<String> resourceIdName = requiredPermissionAnnotation.stringValue("resourceIdName");
          // Optional<String> permission = requiredPermissionAnnotation.stringValue("permission");
          // if (permission.isPresent() && resourceIdName.isPresent() && claims != null) {
          //     // Use name of parameter to get the value passed in as an argument to the method
          //     String resourceId = methodBasedRouteMatch.getVariableValues().get(resourceIdName.get()).toString();
          //     // Get claim from jwt using the resource ID
          //     Object permissionForResource = ((Map) claims.get("https://your-domain.com/claims")).get(resourceId);
          //     if (permissionForResource != null && permissionForResource.equals(permission.get())) {
          //         // if the permission exists and it's equal, allow access
          //         return SecurityRuleResult.ALLOWED;
          //     }
          // }
        }
        
        logger.info("Annotation not present");
      }
      return SecurityRuleResult.UNKNOWN;
    }
  
    public int getOrder() {
      return ORDER;
    }
  
}
