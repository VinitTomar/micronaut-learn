package micronaut.tut;

import java.security.Principal;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

@Controller("secured/{pid}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@CheckForPid(ignore = false)
public class SecuredController {

  @Get(produces = MediaType.TEXT_PLAIN)
  public String securedPath(@Nullable Principal principal, @Nullable Authentication authentication, int pid) {
    System.out.println("Pricipal: " + principal.getName());
    System.out.println("Index function Controller Pid: " + pid);
    System.out.println("In controller Authentication: " + authentication.getAttributes());
    System.out.println("In controller current user: " + ((MyAuthentication) authentication).getCurrentUser());
    return "This is secured";
  }

  @Get("/admin")
  @Produces(MediaType.TEXT_PLAIN)
  @Secured({"ROLE_ADMIN", "ROLE_X"}) 
  public String withroles() {
    return "You have ROLE_ADMIN or ROLE_X roles";
  }
  
  @Get("/pid-check")
  @Produces(MediaType.TEXT_PLAIN)
  public String checkmyPid(int pid) {
    System.out.println("Controller Pid: " + pid);
    return "check for pid";
  }
  
}
