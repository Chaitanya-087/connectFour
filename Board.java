package connectFourGame;

//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class Board extends StackPane {
	
	private List<Disc> players = new ArrayList<Disc>();
	private int currentIndex = 0;
	private StackPane discPane;
	private FrontPane frontPane;
	
	public Board(Color color) {		
		frontPane = new FrontPane(color);
		discPane = new StackPane();
		
		//adding the players (Discs)
		players.add(new Disc(Color.RED));
		players.add(new Disc(Color.YELLOW));
		
		//adding clickable columns in frontPane of board
		frontPane.getChildren().addAll(columns());
		
		//adding panes to construct board
		this.getChildren().add(discPane);
		this.getChildren().add(frontPane);
	}
	
	public void changePlayer() {
		currentIndex = (currentIndex + 1) % players.size();
	}
	
	public List<Rectangle> columns() {
		List<Rectangle> columns = new ArrayList<>();
		
		for (int x = 0; x < FrontPane.COLUMNS; x++) {
			Rectangle rect = new Rectangle(FrontPane.TILE_SIZE,(FrontPane.ROWS + 1) * FrontPane.TILE_SIZE);
			rect.setTranslateX(x * (FrontPane.TILE_SIZE + 5) + FrontPane.TILE_SIZE / 4);
			rect.setFill(Color.TRANSPARENT);
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(100, 100, 100, 0.5)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			final int column = x;
			rect.setOnMouseClicked(e -> {
				System.out.println(column + "" + currentIndex);
				placeDisc(column);
			});
			columns.add(rect);
		}
		return columns;
	}
	
	public void placeDisc(int column) {
		int rows = FrontPane.ROWS - 1;
		
		changePlayer();
	}
}
