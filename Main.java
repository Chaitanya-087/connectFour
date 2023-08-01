package connectFourGame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private StackPane root;
	
	public Parent createContent() {		
		root = new StackPane();
//		root.setAlignment(Pos.CENTER);
		Board board = new Board(Color.BLUE);
		root.getChildren().add(board);
		return root;
	}
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(createContent());
		stage.setScene(scene);
		stage.setTitle("Connect four");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
