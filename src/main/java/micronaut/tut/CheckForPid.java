package micronaut.tut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(value=RetentionPolicy.RUNTIME)
// @Inherited
public @interface CheckForPid {
  boolean ignore() default false;
}
