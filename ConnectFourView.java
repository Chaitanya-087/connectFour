package mvc;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ConnectFourView {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int TILE_SIZE = 50;
    private static final Color BOARD_COLOR = Color.rgb(138, 43, 226);
    private static final Color PLAYER_ONE_COLOR = Color.RED;
    private static final Color PLAYER_TWO_COLOR = Color.YELLOW;

    private GridPane gridPane = new GridPane();
    private StackPane board;
    private Scene scene;
    
    private ConnectFourController controller;
    private ConnectFourModel model;

    public ConnectFourView(ConnectFourModel model, ConnectFourController controller) {
        this.controller = controller;
        this.model = model;
        
        initializeComponents();
    }

    public Scene getScene() {
        return scene;
    }

    private void initializeComponents() {
        board = new StackPane();
        board.getChildren().add(gridPane);
        
        setupBoardBackground();
        createBoardCircles();
        
        gridPane.setHgap(4.3);
        gridPane.setVgap(4.3);
        gridPane.setAlignment(Pos.CENTER);
    }

    private void setupBoardBackground() {
        scene = new Scene(board);
        scene.setFill(BOARD_COLOR);
        
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
        shape.setFill(Color.WHITE);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(2);
        board.getChildren().add(shape);
    }

    private void createBoardCircles() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
            	
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setStroke(Color.BLACK);
                circle.setFill(BOARD_COLOR);
                
                final int finalcol = col;
                circle.setOnMouseClicked(e -> updateBoard(finalcol, model.getCurrentPlayer()));
                gridPane.add(circle, col, row);
                
            }
        }
    }
    
    public void updateBoard(int col, char player) {
    	//boolean isWin = model.checkWin();
		if (model.checkWin()) {
			System.out.println(player + " wins!");
			//code to reset everything and print a victory screen
			return;
		} 
	
    	if(model.isValid(col)) {
    		int row = model.getEmptyRowforColumn(col);
    		Circle circle = new Circle(TILE_SIZE / 2, player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR);
    		gridPane.add(circle, col, row);
    		controller.handlePlayerMove(col);
    		if (model.checkWin())
    			System.out.println(player + "wins!");
    		//code to reset everything and print a victory screen
    	} else {
    		System.out.println("not valid!!");
    	}
	
    }
    
    
    public void resetBoard() {
        gridPane.getChildren().clear();
        initializeComponents();
    }
}
