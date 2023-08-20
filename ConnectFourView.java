package mvc;

import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ConnectFourView {
	private static final Color SCENE_COLOR = Color.rgb(245, 245, 245);
	private static final Color TEXT_COLOR = Color.BLACK;

<<<<<<< HEAD
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
    	boolean isWin = model.checkWin();
		if (model.checkWin()) {
			System.out.println(player + " wins!");
			//code to reset everything and print a victory screen
			return;
		} 
	
    	if(model.isValid(col)) {
    		int row = model.getEmptyRowforColumn(col);
    		Circle circle = new Circle(TILE_SIZE / 2, player == 'X' ? PLAYER_ONE_COLOR : PLAYER_TWO_COLOR);
    		gridPane.add(circle, col, row);
    		if (model.checkWin())
    			System.out.println(player + "wins!");
    		//code to reset everything and print a victory screen
    		controller.handlePlayerMove(col);
    	} else {
    		System.out.println("not valid!!");
    	}
=======
	private VBox root;
	private Scene scene;
	private Button button;
	private BoardView board;
	private Text statusText;
	private ConnectFourController controller;
	
	public void setController(ConnectFourController controller) {
		this.controller = controller;
		initializeComponents();
		controller.reloadView();
	}
>>>>>>> experiment
	
	public Scene getScene() {
		return scene;
	}

	private void initializeComponents() {
		root = new VBox(5);
		board = new BoardView(controller);
		button = new Button("RESET");
		
		Text title = new Text("\nRULES");
		Font titleFont = Font.font("Brush Script MT", FontWeight.BOLD, 24);
		title.setFont(titleFont);
		title.setFill(TEXT_COLOR);

		Text rules = new Text("1. Point the cursor over the row you wish to drop ur piece in."
				+ " \n2. Left click to dropo your piece."
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

		statusText = new Text("Status: ");
		statusText.setFont(rulesFont);
		statusText.setFill(TEXT_COLOR);
		
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(statusText,board,button);
		
		root.getChildren().add(textFlow);
		root.getChildren().add(textFlow1);
		
		scene = new Scene(root);
		scene.setFill(SCENE_COLOR);

		button.setOnAction(event -> controller.reset());
	}
	
	public void updateStatusText(String text) {
		statusText.setText(text);
	}
	
	public void updateBoard(char player, int row, int col) {
		board.update(player, row, col);
	}
	
	public void clearBoard() {
		board.insertTransparentCircles();
	}
	
	public void setButtonText(String text) {
		button.setText(text);
	}
}
