package micronaut.tut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Inherited
// @Secured(SecurityRule.IS_AUTHENTICATED)
public @interface CheckForPid {
  boolean ignore() default false;
}
