package micronaut.tut;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;


@Singleton
public class BCryptPasswordEncoderService {

  @Inject
  BCryptPasswordEncoder delegate;

  // BCryptPasswordEncoderService() {
  //   delegate = new BCryptPasswordEncoder();
  // }

  String encode(@NotBlank String rawPassword) {
    return this.delegate.encode(rawPassword);
  }

  boolean matches(@NotBlank String rawPassword, @NotBlank String encodedPassword) {
    return delegate.matches(rawPassword, encodedPassword);
  }

}
