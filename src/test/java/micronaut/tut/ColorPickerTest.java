package micronaut.tut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class ColorPickerTest {

	@Inject
	ColorPicker colorPicker;

	@Test
	void testPrimaryColor() {
		Assertions.assertEquals("Green color", this.colorPicker.color());
	}

}
