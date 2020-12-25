package micronaut.tut;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import io.micronaut.security.authentication.Authentication;


public class MyAuthentication implements Authentication {
 
	private static final long serialVersionUID = 1L;

  private User currentUser;
  
  private Map<String, Object> claimSet;

  final String username;

  public MyAuthentication(Map<String, Object> claimSet, String username) {
    this.claimSet = claimSet;
    this.username = username;
    this.currentUser = null;
  }

	@Override
	public String getName() {
    return this.username;
	}

	@Override
  public Map<String, Object> getAttributes() {
    return this.claimSet;
  }
  
  public void setCurrentUser(User user) {
    if (this.currentUser == null) {
      this.currentUser = user;
      Map<String, Object> m = new ConcurrentHashMap<>();
      m.putAll(this.claimSet);
      m.put("current-user", this.currentUser);
      System.out.println("m: " + m);
      this.claimSet = m;
    }
  }

  public User getCurrentUser() {
    return this.currentUser;
  }
  
}
