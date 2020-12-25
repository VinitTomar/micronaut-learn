package micronaut.tut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class EngineImplTest {

	@Inject
	EngineImpl engine;

	@Test
	void checkEngineStart() {
		Assertions.assertEquals("Starting V2 Engine", this.engine.start());
	}
}
