package micronaut.tut;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Introspected
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
  String username;
  String password;
}
