package micronaut.tut;

import javax.inject.Singleton;

@Singleton
public class V8Engine implements Engine {
	private int cylinders = 8;

	@Override
	public String start() {
		return "Starting v8 engine.";
	}

	@Override
	public int getCylinders() {
		return cylinders;
	}

	@Override
	public String toString() {
		return "V8Engine{" +
						"cylinders=" + cylinders +
						'}';
	}
}
