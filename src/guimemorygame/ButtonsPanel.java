package guimemorygame;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import static javax.swing.JOptionPane.showMessageDialog;








public class ButtonsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private LeaderBoard lb;
	private final GameBoard board;
	//private KeyBoardButtonAction action;
	private final JButton[][] grid;
	private JButton curButton;
	private int curButtonRow;
	private int curButtonCol;
	private String currentPiece;
	private GUI view;
	private int turnCount=0;
	private JPanel buttons = new JPanel();

	public ButtonsPanel(GameBoard board, GUI view) {
		turnCount = 0;
		lb = new LeaderBoard();
		this.view=view;
		this.setLayout(null);
		this.board = board;
		this.board.setBoardVisibility(false);
		
		this.grid = createButtonGrid();
		
		this.buttons = addButtons(grid);
	}
	
	
	private JButton[][] createButtonGrid() {
		JButton[][] grid = new JButton[board.getRows()][board.getCols()];
		board.shuffle();
		//nested loop to iterate through board rows and columns
		for(int i = 0; i<board.getRows(); i++) {
			for(int j = 0; j<board.getCols(); j++) {
				grid[i][j]= new JButton();
			}
		}
		return grid;
	}
	
	//add the buttons to the panel one by one
	private JPanel addButtons(JButton[][] grid) {
		JPanel panel = new JPanel(new GridLayout(board.getRows(),board.getCols()));
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				final int i = row;
				final int j = col;
				//need to add the action listener for the button function
				grid[row][col].addActionListener(new ActionListener() {
					 @Override
	                    public void actionPerformed(ActionEvent e) {
	                        action(i, j);
	                    }
				});
				//set their size and color
				grid[row][col].setPreferredSize(new Dimension(100,100));
				grid[row][col].setBackground(Color.LIGHT_GRAY);
				panel.add(grid[row][col]);
			}
		}
		return panel;
	}

	public JPanel getButtonPanel() {
		return buttons;
	}
	
	public JButton[][] getButtonGrid(){
		return grid;
	}
	

	private void action(int row, int col) {
		JButton clickedButton = grid[row][col];
		if (clickedButton == curButton || clickedButton.getText().equals(currentPiece)||
				(curButton!= null && curButton.getBackground().equals(Color.GREEN)||
				clickedButton!=null && clickedButton.getBackground().equals(Color.GREEN))){
			//ignore if the button is already matched or if the same button is clicked twice
			JOptionPane.showMessageDialog(null, "You've alreacy chosen this tile!");
			return;
		}
		//show buttons
		clickedButton.setText(""+ board.getCharacter(row, col));
		clickedButton.setBackground(Color.WHITE);
		if (currentPiece == null) {
			//first guess
			//remember the current icon for the next click
			currentPiece = clickedButton.getText();
			//remember the button for the next click
			curButton = clickedButton;
			//remember location
			curButtonRow = row;
			curButtonCol = col;
		} else {
			if (currentPiece.equals(clickedButton.getText())){
			//match found
			//change visibility in GameBoard
			board.setVisible(row,col,true);
			board.setVisible(curButtonRow, curButtonCol, true);
			//change piece color
			clickedButton.setOpaque(true);
			curButton.setOpaque(true);
			clickedButton.setBackground(Color.GREEN);
			curButton.setBackground(Color.GREEN);
			//clear these variables for the next turn
			currentPiece = null;
			curButton = null;
			checkCompletion();
			} else {
				//No match, hide both icons after a short delay
				turnCount++;
				view.setTurnCount(turnCount);
				clickedButton.setOpaque(true);
				curButton.setOpaque(true);
				clickedButton.setBackground(Color.RED);
				curButton.setBackground(Color.RED);
				//dont't allow user to click on other buttons before timer is up
				for (JButton[] i : grid) {
					for (JButton button : i) {
						button.setEnabled(false);
					}
				}
				Timer timer = new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//after time is up hide buttons that were clicked
						curButton.setText("");
						clickedButton.setText("");
						clickedButton.setOpaque(false);
						curButton.setOpaque(false);
						curButton.setBackground(Color.GRAY);
						clickedButton.setBackground(Color.GRAY);
						//make buttons clickable again
						for (JButton[] i : grid) {
							for (JButton button : i) {
								button.setEnabled(true);
							}
						}
						currentPiece = null;
						curButton=null;
						
					}
				});
				timer.setRepeats(false);
				timer.start();
			}	
		}
	}
	

//function to check completion
void checkCompletion() {
	//first check if the board is all visible
	if (board.checkCompletion()) {
		//if it is then check if the user had inputed a name
		if (view.getName()!= null) {
			//update the leaderboard with the difficulty currently being used
			String difficulty;
			if (board.getRows() == 3 && board.getCols()==4)
				difficulty = "EASY MODE";
			else if (board.getRows()==4 && board.getCols()==7) 
				difficulty = "MEDIUM MODE";
			 else 
				difficulty = "HARD MODE";
			lb.updateLeaderBoard(turnCount, difficulty, view.getName());
			//add play again and exit buttons
			JPanel panel = new JPanel();
			JButton newGame = new JButton("Play Again!");
			JButton exitButton = new JButton("Exit");
			//action listener to reset game with default settings
			newGame.addActionListener(new ActionListener() {
		         @Override
		          public void actionPerformed(ActionEvent e) {
		             view.getFrame().dispose();
		             new GUI(new GameBoard(4,7, new SymbolAlphabet()));
		          }
		    });
			//action listener to dispose of the frame if exit is selected
			exitButton.addActionListener(new ActionListener() {
					@Override
		            public void actionPerformed(ActionEvent e) {
		                System.exit(0);
		            }
		        });
			panel.add(exitButton);
			panel.add(newGame);
			view.getFrame().add(panel, BorderLayout.SOUTH);
			JOptionPane.showMessageDialog(null, "Congradulations "+view.getName()+", you won the game! If you got a high score it will be displayed on the leaderboard.");
		} else {
			//user hasn't submitted a name yet
			JOptionPane.showMessageDialog(null, "Please submit a name!");
		}
	}
}

public int getTurnCount() {
	return turnCount;
}



}