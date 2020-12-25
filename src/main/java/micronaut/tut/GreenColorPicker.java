package micronaut.tut;

import javax.inject.Singleton;

@Singleton
public class GreenColorPicker implements ColorPicker {
	@Override
	public String color() {
		return "Green color";
	}
}
