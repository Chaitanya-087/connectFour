package mvc;

public class ConnectFourController {
    private ConnectFourModel model;
	
    public ConnectFourController(ConnectFourModel model) {
        this.model = model;
    }

    public void handlePlayerMove(int col) {
        model.makeMove(col);  
    }
    
}
