package micronaut.tut;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

@Controller("secured/{pid}")
@Secured(SecurityRule.IS_ANONYMOUS)
@CheckForPid()
public class SecuredController {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);


  @Get(produces = MediaType.TEXT_PLAIN)
  public String securedPath(@Nullable Principal principal, @Nullable Authentication authentication, int pid) {
    logger.info("Pricipal: " + principal.getName());
    logger.info("Index function Controller Pid: " + pid);
    logger.info("In controller Authentication: " + authentication.getAttributes());
    logger.info("In controller current user: " + ((MyAuthentication) authentication).getCurrentUser());
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
  @CheckForPid(ignore = true)
  public String checkmyPid(int pid) {
    logger.info("Controller Pid: " + pid);
    return "check for pid";
  }
  
}
