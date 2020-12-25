package micronaut.tut;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Introspected
@AllArgsConstructor
@Getter
@Setter
public class Item {

  @NotBlank
  @ItemIdValidator
  String id;

  @NotBlank
  String name;

  @NotBlank
  String description;

  @PostConstruct
  void init() {
    
  }

}
