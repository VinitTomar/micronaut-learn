package micronaut.tut;

import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

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

	@Override
    public SecurityRuleResult check(HttpRequest<?> request, RouteMatch<?> routeMatch, Map<String, Object> claims) {
      System.out.println("Claims in security rule: " + claims);
      if (routeMatch instanceof MethodBasedRouteMatch) {
        MethodBasedRouteMatch<?,?> methodBasedRouteMatch = (MethodBasedRouteMatch<?,?>) routeMatch;
        if (methodBasedRouteMatch.hasAnnotation(CheckForPid.class)) {
          AnnotationValue<CheckForPid> checkPidAnnotation = methodBasedRouteMatch.getAnnotation(CheckForPid.class);
          Optional<Boolean> value = checkPidAnnotation.booleanValue("ignore");

          System.out.println("checkPidAnnotation: " + checkPidAnnotation);

          if (value.isEmpty()) {
            Map<String, Object> variablevalues = methodBasedRouteMatch.getVariableValues();
            System.out.println("RouterMachVariable values: " + variablevalues);

            System.out.println("Security rule request: " + request);

            Integer pid = Integer.parseInt((String) variablevalues.get("pid"));

            if (pid == 3) {
              return SecurityRuleResult.ALLOWED;

            } else {
              return SecurityRuleResult.REJECTED;
            }

          }

          if (value.isPresent() && value.get()) {
            return SecurityRuleResult.ALLOWED;
          }
          
          System.out.println("Value not present");
          
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
      }
      return SecurityRuleResult.UNKNOWN;
    }
  
    public int getOrder() {
      return ORDER;
    }
  
}
