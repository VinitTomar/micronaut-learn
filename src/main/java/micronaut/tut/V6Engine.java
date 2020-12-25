package micronaut.tut;

import javax.inject.Singleton;

@Singleton
public class V6Engine implements Engine {
	private int cylinders = 6;

	@Override
	public String start() {
		return "Starting v6 engine.";
	}

	@Override
	public int getCylinders() {
		return cylinders;
	}

	@Override
	public String toString() {
		return "V6Engine{" +
						"cylinders=" + cylinders +
						'}';
	}
}
