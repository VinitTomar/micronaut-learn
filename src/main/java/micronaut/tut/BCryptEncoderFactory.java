package micronaut.tut;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Factory;

@Factory
public class BCryptEncoderFactory {
  

  @Singleton
  BCryptPasswordEncoder bcEncoder() {
    return new BCryptPasswordEncoder(5);
  }

}
