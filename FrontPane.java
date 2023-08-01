package connectFourGame;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FrontPane  extends  Pane{
	
	public static final int TILE_SIZE = 45;
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	
//	private Disc[][] grid = new Disc[ROWS][COLUMNS];
	private Shape shape;
	
	public FrontPane(Color color) {
		shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {
				Circle circle = new  Circle(TILE_SIZE / 2);
				circle.setCenterX(TILE_SIZE / 2);
				circle.setCenterY(TILE_SIZE / 2);
				circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
				circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);
				shape = Shape.subtract(shape, circle);
			}
		}
		
		shape.setFill(color);
		getChildren().add(shape);
	}
	
//	public Board(Color color, double yOffset) {
//		Shape newBoard = createShape(color);
//		newBoard.setTranslateZ(yOffset);
//		this.board = newBoard;
//	}
	
//	public Optional<Disc> getDisc(int column, int row) {
//		if (column < 0 || column >= COLUMNS || row < 0 || row >= ROWS) {
//			return Optional.empty();
//		}
//		else return Optional.ofNullable(grid[row][column]);
//	}
}
