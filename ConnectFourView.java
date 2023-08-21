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
