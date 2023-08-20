package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Arrays;

public class modelPersistence {
	private Connection cnx;
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final String USERNAME = "chaitanya";
	private static final String PASSWORD = "chaitanya";
	private static final String URI = "jdbc:mysql://localhost:3306/connect4";
	public modelPersistence() {
		try {
			cnx = DriverManager.getConnection(URI,USERNAME,PASSWORD);	
			System.out.println("connect 4 database connection successful");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertMove(char player, int row, int col) {
		try {
			String query = "insert into moves (player, `row`, col) values (?, ?, ?)";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			pstmt.setString(1, String.valueOf(player));
			pstmt.setInt(2, row);
			pstmt.setInt(3, col);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public char getCurrentPlayer() {
		char currentPlayer = ' ';
		try {
			String query = "SELECT * from moves";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				currentPlayer = rs.getString("player").toCharArray()[0];
			}
			if (currentPlayer == ' ') {
				currentPlayer = 'X';
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return currentPlayer;
	}
	
	public char[][] retriveBoard() {
		char[][] board = new char[ROWS][COLUMNS];
		try {
			String query = "SELECT * from moves";
			PreparedStatement pstmt = cnx.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int col = rs.getInt("col");
				int row = rs.getInt("row");
				String player = rs.getString("player");
				board[row][col] = player.toCharArray()[0];
			}

			for (int i = 0; i < ROWS; i++) {
				for (int  j = 0; j < COLUMNS; j++) {
					if (board[i][j] == '\u0000') {
						board[i][j] = ' ';
					}
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return board;
	}
	public void truncate() {
		try {
			Statement stmt = cnx.createStatement();
			stmt.executeUpdate("DELETE from moves");
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}

}
