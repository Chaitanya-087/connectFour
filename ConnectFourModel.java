package mvc;

public class ConnectFourModel {
	public final int ROWS = 6;
	public final int COLUMNS = 7;
	
	private char[][] board;
	private char currentPlayer;
	
	modelPersistence persistence;

	public ConnectFourModel() {
		persistence = new modelPersistence();
		initializeBoard();
	}

	private void initializeBoard() {
		board = persistence.retriveBoard();
		currentPlayer = persistence.getCurrentPlayer();
	}

	public char[][] getBoard() {
		return board;
	}

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	public void makeMove(int row, int col) {
		board[row][col] = currentPlayer;
		persistence.insertMove(currentPlayer, row, col);
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
		return checkRows() || checkColumns() || checkDiagonals();
	}

	private boolean checkRows() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length - 3; col++) {
				if (checkConsecutive(row, col, 0, 1)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkColumns() {
		for (int row = 0; row < board.length - 3; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (checkConsecutive(row, col, 1, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isEmpty() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j] != ' ') return false;
				}
			}
		return true;
	}
	
	private boolean checkDiagonals() {
		// Check upward diagonals
		for (int row = 3; row < board.length; row++) {
			for (int col = 0; col < board[0].length - 3; col++) {
				if (checkConsecutive(row, col, -1, 1)) {
					return true;
				}
			}
		}

		// Check downward diagonals
		for (int row = 0; row < board.length - 3; row++) {
			for (int col = 0; col < board[0].length - 3; col++) {
				if (checkConsecutive(row, col, 1, 1)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkConsecutive(int startRow, int startCol, int rowIncrement, int colIncrement) {
		for (int i = 0; i < 4; i++) {
			int row = startRow + i * rowIncrement;
			int col = startCol + i * colIncrement;
			if (board[row][col] != currentPlayer) {
				return false;
			}
		}
		return true;
	}

	public void switchPlayer() {
		currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
	}
	
	public void reset() {
		persistence.truncate();
		initializeBoard();
	}
}
