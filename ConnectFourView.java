package mvc;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Font;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class ConnectFourView {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int TILE_SIZE = 50;
    private static final Color BOARD_COLOR = Color.BLUE; 
    private static final Color SCENE_COLOR = Color.rgb(245,245,245);
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color PLAYER_ONE_COLOR = Color.RED;
    private static final Color PLAYER_TWO_COLOR = Color.YELLOW;
    private GridPane gridPane = new GridPane();
    private StackPane board;
    private Scene scene;
    private HBox clickableColumns;
    private ConnectFourController controller;
    private ConnectFourModel model;
    private VBox vertical;
    private Text playerTurn;
    private Text winDisplayTest;
    
    public ConnectFourView(ConnectFourModel model, ConnectFourController controller) {
        this.controller = controller;
        this.model = model;
        initializeComponents();
    }

    public Scene getScene() {
        return scene;
    }

    private void initializeComponents() {
    	vertical = new VBox(5);
    	winDisplayTest =  new Text();
		Text title = new Text("\nRULES");
		Font titleFont = Font.font("Brush Script MT", FontWeight.BOLD,24);
		title.setFont(titleFont);
		title.setFill(TEXT_COLOR);
		
		winDisplayTest.setFont(titleFont);
		Text rules = new Text("1. Point the cursor over the row you wish to drop ur piece in." + " \n2. Left click to dropo your piece."
		  + "\n3. When you can connect four pieces vertically, horizontally and diagonally you win.");
		Font rulesFont = Font.font("Verdana", FontWeight.LIGHT, 18);
		rules.setFont(rulesFont);
		rules.setFill(TEXT_COLOR);
		
		TextFlow textFlow = new TextFlow();
		textFlow.setMaxWidth(900);
		textFlow.setTextAlignment(TextAlignment.CENTER);
		textFlow.getChildren().add(title);
		  
		TextFlow textFlow1 = new TextFlow();
		textFlow1.getChildren().add(rules);
		textFlow1.setMaxWidth(900);
      
		board = new StackPane();
		clickableColumns = new HBox(4);
		clickableColumns.getChildren().addAll(getColumns());
		clickableColumns.setAlignment(Pos.CENTER);
		
		board.getChildren().add(gridPane);
		board.getChildren().add(createRectangleWithHoles());
		board.getChildren().add(clickableColumns);
		
		playerTurn =  new Text("RED'S TURN");
		playerTurn.setFont(rulesFont);
		playerTurn.setFill(TEXT_COLOR);
		vertical.getChildren().add(playerTurn);
		vertical.getChildren().add(board);
		vertical.getChildren().add(textFlow);
		vertical.getChildren().add(textFlow1);
		vertical.setAlignment(Pos.CENTER);
		board.getChildren().add(winDisplayTest);
		gridPane.setHgap(4.3);
		gridPane.setVgap(4.3);
		gridPane.setAlignment(Pos.CENTER);
		
		scene = new Scene(vertical);
		scene.setFill(SCENE_COLOR);
		createBoardCircles();
    }

    private Shape createRectangleWithHoles() {        
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
    
    private List<Rectangle> getColumns() {
    	List<Rectangle> columns = new ArrayList<>();
    	for (int x = 0; x < COLUMNS; x++) {
    		Rectangle rect =  new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE,Color.TRANSPARENT);
    		rect.setTranslateX(x);
    		rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(100, 100, 100, 0.5)));
    		rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
    		final int finalCol = x;
    		
    		rect.setOnMouseClicked(e -> {
    			updateBoard(finalCol, model.getCurrentPlayer());
    		});
    		columns.add(rect);
    	}
    	return columns;
    }
    
    private void createBoardCircles() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setFill(SCENE_COLOR);
                gridPane.add(circle,col,row);
                
            }
        }
    }
    
    public void updateBoard(int col, char player) {	
    	if(model.isValid(col) && !model.checkWin()) {
    		int row = model.getEmptyRowforColumn(col);
    		Color discColor = player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR;
    		Circle animationCircle = new Circle(TILE_SIZE / 2, discColor);
    		gridPane.add(animationCircle, col, 0); 
    		
    		TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), animationCircle);
    		transition.setFromY(-TILE_SIZE * (ROWS + 1));
    		transition.setToY((ROWS - 1 - row) * TILE_SIZE);
    		transition.play();
    		
    		transition.setOnFinished(event -> {
    			gridPane.getChildren().remove(animationCircle);
    			Circle circle = new Circle(TILE_SIZE / 2, discColor);
    			gridPane.add(circle, col, row);
    			
    			if (model.checkWin()) {
    				return;
    			} else {
    				model.switchPlayer();
    				playerTurn.setText(model.getCurrentPlayer() == 'X' ? "RED'S TURN" : "YELLOW'S TURN");
    			}
    			
    			controller.handlePlayerMove(col);
    		});
    		
    	} else {
    		System.out.println("not valid!!");
    	}
	
    }
    
    public void resetBoard() {
        gridPane.getChildren().clear();
        initializeComponents();
    }
    
}
