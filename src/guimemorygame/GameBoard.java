package guimemorygame;

import java.util.Random;
import java.util.Scanner;

public class GameBoard {
	private final GamePiece[][] board;
	private final Alphabet alph;
	private final int rows;
	private final int cols;
	
	public GameBoard(int rows, int cols, Alphabet a) {
		this.rows = rows;
		this.cols = cols;
		alph = a;
		//Initialize board to have rows and columns equal to parameters
		board = new GamePiece[rows][cols];
		int size = rows*cols;
		//map half the size of the array number of characters to the array
		int k = 0; //variable to keep track of characters used
		//nested loop to iterate through board rows and columns
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<cols; j++) {
				//checking if the characters have reached half the size of the board. 
				//If they have, we start over and fill the rest of the board with their matching pairs.
				if (k<size/2) {
					board[i][j] = new CharacterGamePiece(a.toCharArray()[k]);
				} else {
					k=0;
					board[i][j] = new CharacterGamePiece(a.toCharArray()[k]);
				}
				k++;
			}
		}
	}
	public Alphabet getA() {
		return alph;
	}
	
	public int getRows() {
		return board.length;
	}
	public int getCols() {
		return board[0].length;
	}
	public void setVisible(int row, int col, boolean v) {
		board[row][col].setVisible(v);
	}
	public char getCharacter(int row, int col) {
		return board[row][col].getSymbol();
	}
	public boolean checkVisible(int row, int col) {
		return board[row][col].isVisible();
	}
	
	
	
	//shuffle function for when the game is started, i learned this method, the Fisher–Yates algorithm, from stack overflow
	public void shuffle() {
		Random random = new Random();
	    for (int i = board.length - 1; i > 0; i--) {
	        for (int j = board[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);
	            GamePiece temp = board[i][j];
	            board[i][j] = board[m][n];
	            board[m][n] = temp;
	        }
	    }
	}
	
	//function to set the entire board to a certain visibility
	public void setBoardVisibility(boolean v) {
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[0].length; j++) {
					board[i][j].setVisible(v);
			}
		}
	}
	

public boolean checkCompletion() {
	for (int i = 0; i<rows;i++) {
		for (int j = 0; j<cols; j++) {
			if (!board[i][j].isVisible())
				return false;
			}
		}
	return true;
}

	
	
}