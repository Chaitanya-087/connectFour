package connectFourGame;

//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Board extends StackPane {
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	public static final int TILE_SIZE = 45;
	
	private List<Color> players = new ArrayList<>();
	private int currentIndex = 0;
	private Pane discPane;
	private FrontPane frontPane;
	private Disc[][] grid = new Disc[ROWS][COLUMNS];
	
	public Board(Color color) {		
		frontPane = new FrontPane(color);
		discPane = new Pane();
		setAlignment(Pos.CENTER);
		//adding the players (Discs)
		players.add(Color.RED);
		players.add(Color.YELLOW);
		
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
			Rectangle rect = new Rectangle(TILE_SIZE,(ROWS + 1) * TILE_SIZE);
			rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
			rect.setFill(Color.TRANSPARENT);
			rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(100, 100, 100, 0.5)));
			rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			final int column = x;
			rect.setOnMouseClicked(e -> {
				System.out.println(column + "" + currentIndex);
				placeDisc(new Disc(players.get(currentIndex)),column);
			});
			columns.add(rect);
		}
		return columns;
	}
	
	public void placeDisc(Disc disc, int column) {
		int row = FrontPane.ROWS - 1;
		do {
			if (!getDisc(column, row).isPresent()) break;
			row--;
		}while(row >= 0);
		if (row < 0) return;
		grid[row][column] = disc;
		discPane.getChildren().add(disc);
		disc.setTranslateX(column * (TILE_SIZE + 4) + TILE_SIZE / 4);
		
		TranslateTransition animation  =  new TranslateTransition(Duration.seconds(0.5),disc);
		
		animation.setToY(row * (TILE_SIZE + 4) + TILE_SIZE / 4);
		
		animation.setOnFinished(e -> {
			changePlayer();
		});
		animation.play();
	}
	
	public Optional<Disc> getDisc(int column, int row) {
	if (column < 0 || column >= FrontPane.COLUMNS || row < 0 || row >= ROWS) {
		return Optional.empty();
	}
	else return Optional.ofNullable(grid[row][column]);
}
}
