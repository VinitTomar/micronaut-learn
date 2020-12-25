package micronaut.tut;

import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;

@Singleton
public class EngineImpl implements Engine {

	@Value("${my.engine.cylinders:2}")
	protected int cylinders;

	@Override
	public String start() {
		return "Starting V" + getCylinders() + " Engine";
	}

	@Override
	public int getCylinders() {
		return this.cylinders;
	}
}
