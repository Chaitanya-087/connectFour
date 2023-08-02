package mvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
		ConnectFourModel model = new ConnectFourModel();
		ConnectFourController controller = new ConnectFourController(model);
		ConnectFourView view = new ConnectFourView(model, controller);
		
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Connect Four");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
