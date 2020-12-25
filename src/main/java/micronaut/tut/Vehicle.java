package micronaut.tut;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class Vehicle {
	private final Engine engine;

	public Vehicle(@Named("v8") Engine engine) {
		this.engine = engine;
	}

	public String start() {
		return engine.start();
	}

	@Override
	public String toString() {
		return "Vehicle{" +
						"engine=" + engine +
						'}';
	}
}
