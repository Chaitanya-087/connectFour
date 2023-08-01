package connectFourGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle {
	private final static int SIZE = 25;
	public Color color;
	
	public Disc(Color color) {
		super(SIZE, color);
		setCenterX(SIZE);
		setCenterY(SIZE);
		this.color = color;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Disc)) {
			return false;
		}
		
		Disc otherDisc = (Disc) other;
		return color.equals(otherDisc.color);
	}
}
