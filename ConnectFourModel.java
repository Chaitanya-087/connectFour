package mvc;

public class ConnectFourModel {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private char[][] board;
    private char currentPlayer;
    
    public ConnectFourModel() {
        board = new char[ROWS][COLUMNS];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void makeMove(int col) {
    	int row = getEmptyRowforColumn(col);
    	board[row][col] = currentPlayer;
    }
    
    public int getEmptyRowforColumn(int col) {
    	int row;
    	for (row = board.length - 1; row >= 0; row--) {
    		if (board[row][col] == ' ') {
    			break;
    		}
    	}
    	return row;
    }
    
    public boolean isValid(int col) {
    	    if (col < 0 || col >= COLUMNS) 
        return false;
    // Check if the top row of the column is empty, indicating a valid move
    return board[0][col] == ' ';
    }
    
    public boolean checkWin() {
        // Implement the win condition check here
        // You need to check for four consecutive tokens in a row, column, or diagonals.
    			for(int row = 0; row <  board.length; row++){
    				for (int col = 0;col < board[0].length - 3;col++){
    					if ( board[row][col] == currentPlayer   && 
    						 board[row][col+1] == currentPlayer &&
    						 board[row][col+2] == currentPlayer &&
    						 board[row][col+3] == currentPlayer){
    						return true;
    					}
    				}			
    			}
    			
    			for(int row = 0; row < board.length - 3; row++){
    				for(int col = 0; col < board[0].length; col++){
    					if (board[row][col] == currentPlayer   && 
    						board[row+1][col] == currentPlayer &&
    						board[row+2][col] == currentPlayer &&
    						board[row+3][col] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			//check upward diagonal
    			for(int row = 3; row < board.length; row++){
    				for(int col = 0; col < board[0].length - 3; col++){
    					if (board[row][col] == currentPlayer   && 
    						board[row-1][col+1] == currentPlayer &&
    						board[row-2][col+2] == currentPlayer &&
    						board[row-3][col+3] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			//check downward diagonal
    			for(int row = 0; row < board.length - 3; row++){
    				for(int col = 0; col < board[0].length - 3; col++){
    					if (board[row][col] == currentPlayer   && 
    						board[row+1][col+1] == currentPlayer &&
    						board[row+2][col+2] == currentPlayer &&
    						board[row+3][col+3] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			
    			return false;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }
    
}

