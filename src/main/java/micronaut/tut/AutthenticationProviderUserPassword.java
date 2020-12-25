package micronaut.tut;


import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.reactivestreams.Publisher;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

@Singleton
public class AutthenticationProviderUserPassword implements AuthenticationProvider {
  
  @Inject
  BCryptPasswordEncoderService passencoder;

  @Inject
  UserRepository repo;

	@Override
  public Publisher<AuthenticationResponse> authenticate(
    @Nullable HttpRequest<?> httpRequest,
    AuthenticationRequest<?, ?> authenticationRequest
  ) {

    return Flowable.create(emitter -> {

      System.out.println("Autthentication provider user password");

      var username = (String) authenticationRequest.getIdentity();
      var pass = (String) authenticationRequest.getSecret();
      
      repo.findUserByName(username).subscribe(usrOptional -> {
        
        if (usrOptional.isEmpty()) {
          emitter.onNext(new AuthenticationFailed("Invalid username and password"));
        } else {
          User usr = usrOptional.get();
          
          if (passencoder.matches(pass, usr.password)) {
            emitter.onNext(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
          } else {
            emitter.onNext(new AuthenticationFailed("Invalid username and password"));
          }
        }

        emitter.onComplete();
      });

    }, BackpressureStrategy.ERROR);
    
	}

}