package micronaut.tut;

import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext;

public class MyCustomValidator implements ConstraintValidator {

	@Override
	public boolean isValid(Object value, AnnotationValue annotationMetadata, ConstraintValidatorContext context) {
		return false;
	}
  
}
