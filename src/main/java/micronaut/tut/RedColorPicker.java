package micronaut.tut;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Secondary;

import javax.inject.Singleton;

@Secondary
@Singleton
public class RedColorPicker implements ColorPicker {
	@Override
	public String color() {
		return "Red color";
	}
}
