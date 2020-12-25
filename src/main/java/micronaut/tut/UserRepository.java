package micronaut.tut;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class UserRepository extends ConcurrentHashMap<String, User>{
  
  private static final long serialVersionUID = 1L;
  
  @Inject
  BCryptPasswordEncoderService passwordEncoder;

  @PostConstruct
  public void init() {
    this.put("vinit", new User("vinit", this.passwordEncoder.encode("tomar")));
    this.put("rupit", new User("rupit", this.passwordEncoder.encode("tomar")));
    this.put("tejash", new User("tejash", this.passwordEncoder.encode("tomar")));
  }

  @Override
  public String toString() {
    return super.toString();
  }

  Single<Optional<User>> findUserByName(String name) {
    return Single.just(Optional.ofNullable(this.get(name)));
  }

}
