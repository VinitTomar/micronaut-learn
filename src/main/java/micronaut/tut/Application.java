package micronaut.tut;

import io.micronaut.runtime.Micronaut;

import static io.micronaut.context.ApplicationContext.run;

public class Application {

	public static void main(String[] args) {
		Micronaut.run(Application.class, args);

		try (var applicationContext = run()) {
			Vehicle vehicle = applicationContext.getBean(Vehicle.class);
			System.out.println("Vehicle1: "+vehicle.start());
			Vehicle2 vehicle2 = applicationContext.getBean(Vehicle2.class);
			System.out.println("Vehicle2: "+vehicle2.start());
		}


	}

}
