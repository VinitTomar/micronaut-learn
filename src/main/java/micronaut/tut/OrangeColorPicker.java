package micronaut.tut;

// import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Secondary;

import javax.inject.Singleton;

//@Primary
@Secondary
@Singleton
public class OrangeColorPicker implements ColorPicker {
	@Override
	public String color() {
		return "Orange color";
	}
}
