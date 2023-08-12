package mvc;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BoardView extends StackPane {
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final int TILE_SIZE = 50;
	private static final Color BOARD_COLOR = Color.BLUE;
	private static final Color SCENE_COLOR = Color.rgb(245,245,245);
    private static final Color PLAYER_ONE_COLOR = Color.RED;
    private static final Color PLAYER_TWO_COLOR = Color.YELLOW;
	
	private GridPane gameGrid;
	
	public BoardView() {
		gameGrid = new GridPane();
		initializeBoard();
		refreshBoard();
	}
	private void refreshBoard() {
		
	}
	private void initializeBoard() {
		Shape rectWithHoles = getRectWithHoles();
		insertTransparentCircles();
		getChildren().addAll(gameGrid, rectWithHoles);
	}
	
	private void insertTransparentCircles() {
		for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setFill(SCENE_COLOR);
                gameGrid.add(circle,col,row);
                
            }
        }
	}
	
	private Shape getRectWithHoles()  {
        Rectangle rect = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        Shape shape = rect;
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);
                shape = Shape.subtract(shape, circle);
            }
        }
        shape.setFill(BOARD_COLOR);
        shape.setStroke(Color.WHITE);
        return shape;
	}
	
	public void update(int col, int row, char player) {
		Color color = (player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR); 
		Circle circle = new Circle(TILE_SIZE / 2,color);
		
		gameGrid.add(circle, col,row);
	}

}
