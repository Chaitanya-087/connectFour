package mvc;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ConnectFourView {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int TILE_SIZE = 50;
    private static final Color BOARD_COLOR = Color.rgb(138, 43, 226);
    private static final Color PLAYER_ONE_COLOR = Color.RED;
    private static final Color PLAYER_TWO_COLOR = Color.YELLOW;
    private SequentialTransition sequentialTransition = new SequentialTransition();
    private GridPane gridPane = new GridPane();
    private StackPane board;
    private Scene scene;
    private HBox clickableColumns;
    private ConnectFourController controller;
    private ConnectFourModel model;
    private Circle animationCircle = new Circle(TILE_SIZE / 2);
    
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
        clickableColumns = new HBox(4);
        board.getChildren().add(gridPane);
        board.getChildren().add(setupBoardBackground());
        board.getChildren().add(clickableColumns);
        clickableColumns.getChildren().addAll(getColumns());
        clickableColumns.setAlignment(Pos.CENTER);
//        clickableColumns.
        gridPane.setHgap(4.3);
        gridPane.setVgap(4.3);
        gridPane.setAlignment(Pos.CENTER);
        scene = new Scene(board);
        scene.setFill(BOARD_COLOR);
        createBoardCircles();
    }

    private Shape setupBoardBackground() {        
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
        return shape;
    }
    private List<Rectangle> getColumns() {
    	List<Rectangle> columns = new ArrayList<>();
    	for (int x = 0; x < COLUMNS; x++) {
    		Rectangle rect =  new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE,Color.TRANSPARENT);
    		rect.setTranslateX(x);
    		rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(100, 100, 100, 0.5)));
    		rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
    		final int finalCol = x;
    		rect.setOnMouseClicked(e -> {
    			controller.handlePlayerMove(finalCol);
    			refreshBoard();
    		});
    		columns.add(rect);
    	}
    	return columns;
    }
    
    private void createBoardCircles() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setFill(BOARD_COLOR);
                circle.setStroke(Color.BLACK);
                gridPane.add(circle,col,row);
                
            }
        }
    }
//    public void animateDrop() {
//    	char[][] boardData = model.getBoard().clone();
//    	
//    	for (int row = 0; row < ROWS; row++) {
//    		for (int col = 0; col < COLUMNS; col++) {
//    			char player = boardData[row][col];
//    			if (player != ' ') {	
//    				animationCircle = new Circle(TILE_SIZE / 2 , player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR);
//    				animationCircle.setTranslateY(-TILE_SIZE * (ROWS + 1));
//    				gridPane.add(animationCircle, col, row);
//    				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), animationCircle);
//    				transition.setToY((ROWS - 1 - row) * TILE_SIZE);
//    				transition.setOnFinished(e -> {
//    					animationCircle.setTranslateY(0);
//    					gridPane.getChildren().remove(animationCircle);
//    				});
//    			}
//    		}
//    	} 
//    }
    public void refreshBoard() {
        char[][] boardData = model.getBoard().clone();
        ArrayList<Circle> animationCircles = new ArrayList<>();
        for (int row = 0; row <  ROWS; row++) {
        for (int col = 0; col < COLUMNS; col++) {
            char disc = boardData[row][col];
            if (disc != ' ') {
                Color discColor = disc == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR;
                Circle animationCircle = new Circle(TILE_SIZE / 2, discColor);
                animationCircles.add(animationCircle);
                gridPane.add(animationCircle, col, row);
                double startY = -TILE_SIZE * (ROWS - row); // Start from above the grid
                double endY = 0; // The final position within the grid
                
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), animationCircle);
                transition.setFromY(startY);
                transition.setToY(endY);
                transition.play();

                final int finalCol = col;
                final int finalRow = row;
                
                transition.setOnFinished(e -> {                    
                    Circle circle = new Circle(TILE_SIZE / 2, discColor);
                    gridPane.add(circle, finalCol, finalRow); 
                });
            }
        }
    }
        
}


// public void refreshBoard() {
//    char[][] boardData = model.getBoard().clone();
//    for (int row = 0; row < ROWS; row++) {
//        for (int col = 0; col < COLUMNS; col++) {
//            char disc = boardData[row][col];
//            if (disc != ' ') {
//                Color discColor = disc == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR;
//                animateDiscDrop(col, discColor);
//            }
//        }
//    }
//}
//
//private void animateDiscDrop(int col, Color discColor) {
//    animationCircle = new Circle(TILE_SIZE / 2, discColor);
//    animationCircle.setTranslateY(-TILE_SIZE * (ROWS + 1));
//    gridPane.add(animationCircle, col, model.getEmptyRowforColumn(col));
//
//    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), animationCircle);
//    transition.setToY((ROWS - 1 - model.getEmptyRowforColumn(col)) * TILE_SIZE);
//    transition.setOnFinished(e -> {
//        animationCircle.setTranslateY(0);
//        gridPane.getChildren().remove(animationCircle);
//        refreshBoardComplete(col, discColor);
//    });
//    transition.play();
//}
//
//private void refreshBoardComplete(int col, Color discColor) {
//    char[][] boardData = model.getBoard().clone();
//    for (int row = 0; row < ROWS; row++) {
//        char disc = boardData[row][col];
//        if (disc != ' ') {
//            Circle circle = new Circle(TILE_SIZE / 2, discColor);
//            gridPane.add(circle, col, row);
//        }
//    }
//}

//    public void updateBoard(int col, char player) {
//    	//boolean isWin = model.checkWin();
//		if (model.checkWin()) {
//			System.out.println(player + " wins!");
//			//code to reset everything and print a victory screen
//			return;
//		} 
//	
//    	if(model.isValid(col)) {
//    		int row = model.getEmptyRowforColumn(col);
//    		Circle circle = new Circle(TILE_SIZE / 2, player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR);
//    		gridPane.add(circle, col, row);
//    		controller.handlePlayerMove(col);
//    		if (model.checkWin())
//    			System.out.println(player + "wins!");
//    		//code to reset everything and print a victory screen
//    	} else {
//    		System.out.println("not valid!!");
//    	}
//	
//    }
//    
    
    public void resetBoard() {
        gridPane.getChildren().clear();
        initializeComponents();
    }
}
