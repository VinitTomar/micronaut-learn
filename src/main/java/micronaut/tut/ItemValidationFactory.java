package micronaut.tut;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Factory;
import io.micronaut.validation.validator.constraints.ConstraintValidator;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Factory
public class ItemValidationFactory {

  @Singleton
  ConstraintValidator<ItemIdValidator, String> validateId(ItemRepository itemRepository) {
    return (value, annotationMetadata, context) -> {
      if (Integer.parseInt(value) < 0) {
        return false;
      }

      Boolean isIdAvailable = itemRepository.isIdAvailableAsync(value).observeOn(Schedulers.newThread()).blockingGet();

      return isIdAvailable;
    };
  }
  

  @Singleton
  Single<ConstraintValidator<ItemIdValidator, String>> validateIdAsync(ItemRepository itemRepository) {
    return Single.just(
      (value, annotationMetadata, context) -> {
        if (Integer.parseInt(value) < 0) {
          return false;
        }
  
        Boolean isIdAvailable = itemRepository.isIdAvailableAsync(value)
        .observeOn(Schedulers.newThread())
        .blockingGet();
  
        return isIdAvailable;
      }
    );
  }
  
}
