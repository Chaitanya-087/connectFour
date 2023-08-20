package mvc;

public class ConnectFourController {
    private ConnectFourModel model;
	private ConnectFourView view;
	
    public ConnectFourController(ConnectFourModel model, ConnectFourView view) {
        this.model = model;
        this.view = view;
    }

    public void handlePlayerMove(int col) {
    	if (model.isValid(col) && !model.checkWin() ) {
    		int row = model.getEmptyRowforColumn(col);
    		model.makeMove(row,col);
    		char currentPlayer = model.getCurrentPlayer();
    		
    		view.updateStatusText(currentPlayer == 'X' ? "YELLOW'S TURN" : "RED'S TURN");
    		view.updateBoard(currentPlayer, row, col);
    		
    		if (model.checkWin()) {
    			view.updateStatusText(currentPlayer == 'X' ? "RED PLAYER WIN" : "YELLOW PLAYER WIN");
    			view.setButtonText("play again");
    		} else {
    			model.switchPlayer();
    		}
    	}
    }
    
    public void reset() {
    	model.reset();
    	view.clearBoard();
    	view.updateStatusText("RED'S TURN");
    	view.setButtonText("reset");
    }
  
    public void reloadView() {
    	char[][] board = model.getBoard().clone();
    	String statusText = "";
    	String buttonText = "reset";
    	if (model.isEmpty()) {
    		statusText = "RED'S TURN";
    	} else {
    		if (model.checkWin()) {
    			statusText = model.getCurrentPlayer() == 'X' ? "RED PLAYER WIN" : "YELLOW PLAYER WIN";
    			buttonText = "play again";
    		} else {
    			statusText = model.getCurrentPlayer() == 'X' ? "YELLOW'S TURN" : "RED'S TURN";
    		}
    	}
    	view.updateStatusText(statusText);
    	view.setButtonText(buttonText);
    	
    	for (int i = 0; i < model.ROWS; i++) {
    		for (int j = 0; j < model.COLUMNS; j++) {
    			if (board[i][j] == 'X' || board[i][j] == 'O') {
    				view.updateBoard(board[i][j], i, j);    				
    			}
    		}
    	}
    	if (!model.isEmpty()) {
    		System.out.println("not empty");
    		model.switchPlayer();    		
    	}
    }
    
    public void text() {
    	System.out.println("hello, Controller....");
    }
}
