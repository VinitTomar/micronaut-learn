package micronaut.tut;

import java.security.Principal;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("secured")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SecuredController {

  @Get(produces = MediaType.TEXT_PLAIN)
  public String securedPath(@Nullable Principal principal,@Nullable HttpRequest<?> request) {
    System.out.println("Pricipal: " + principal.getName());
    return "This is secured";
  }

  @Get("/admin")
  @Produces(MediaType.TEXT_PLAIN)
  @Secured({"ROLE_ADMIN", "ROLE_X"}) 
  public String withroles() {
      return "You have ROLE_ADMIN or ROLE_X roles";
  }
  
}
