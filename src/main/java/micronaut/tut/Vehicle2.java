package micronaut.tut;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Vehicle2 {

	@Inject
	@V6
	private Engine engine;

	public String start() {
		return this.engine.start();
	}

}
