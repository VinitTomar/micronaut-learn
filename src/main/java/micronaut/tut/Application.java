package micronaut.tut;

import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import io.micronaut.runtime.Micronaut;

import static io.micronaut.context.ApplicationContext.run;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {

  private static final Logger logger = (Logger) LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    
    var applicationContext = Micronaut.run(Application.class, args);
    
    logger.info("Application started");

    Environment env = applicationContext.getEnvironment();

    Vehicle vehicle = applicationContext.getBean(Vehicle.class);
    logger.info("Vehicle1: "+vehicle.start());
    Vehicle2 vehicle2 = applicationContext.getBean(Vehicle2.class);
    logger.info("Vehicle2: "+vehicle2.start());

	}

}
