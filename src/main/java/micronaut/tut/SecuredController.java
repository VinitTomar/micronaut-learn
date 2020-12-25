package micronaut.tut;

import java.security.Principal;

import javax.inject.Inject;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.utils.SecurityService;

@Controller("secured")
@Secured(SecurityRule.IS_AUTHENTICATED)
@CheckForPid()
public class SecuredController {

  // @Inject
  // SecurityService securityService;

  @Get(produces = MediaType.TEXT_PLAIN)
  public String securedPath(@Nullable Principal principal, @Nullable Authentication authentication) {
    System.out.println("Pricipal: " + principal.getName());
    System.out.println("In controller Authentication: " + authentication.getAttributes());
    System.out.println("In controller current user: " + ((MyAuthentication) authentication).getCurrentUser());

    // this.securityService.getAuthentication().get();

    return "This is secured";
  }

  @Get("/admin")
  @Produces(MediaType.TEXT_PLAIN)
  @Secured({"ROLE_ADMIN", "ROLE_X"}) 
  public String withroles() {
    return "You have ROLE_ADMIN or ROLE_X roles";
  }
  
  @Get("/pid-check/{pid}")
  @Produces(MediaType.TEXT_PLAIN)
  public String checkmyPid(int pid) {
    System.out.println("Pid: " + pid);
    return "check for pid";
  }
  
}
