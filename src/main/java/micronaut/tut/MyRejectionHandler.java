package micronaut.tut;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.authentication.DefaultAuthorizationExceptionHandler;


@Singleton
@Replaces(DefaultAuthorizationExceptionHandler.class)
public class MyRejectionHandler extends DefaultAuthorizationExceptionHandler{
  

  @Override
    public MutableHttpResponse<?> handle(HttpRequest request, AuthorizationException exception) {
      var response = super.handle(request, exception);
      var status = response.status();

      if (status == HttpStatus.FORBIDDEN) {
        response.body("You do not have permission for this operation.");
      } else {
        response.body("Invalid token or token expired.");
      }

      return response;
    }

}
