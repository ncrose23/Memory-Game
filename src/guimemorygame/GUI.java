package guimemorygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;




public class GUI {
	private JFrame frame ;
	private ButtonsPanel BoardGUI = null;
	private GameBoard board = null;
	private String name = null;
	private JLabel turnLabel;
	
	

	public GUI(GameBoard board) {
		//create the Buttons Panel
		System.out.println("GUI");
		this.BoardGUI = new ButtonsPanel(board, this);
		//create the frame
		this.board = board;
		this.frame = createAndShowGUI();
		new LeaderBoardDialog(this);
		
	}
	
	private JFrame createAndShowGUI() {
		frame = new JFrame("Memory Game");
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		//add the button panel
		frame.add(BoardGUI.getButtonPanel(),BorderLayout.CENTER);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		System.out.println("Frame size: " + frame.getSize());
		return frame;
	}
	
	//function for setting difficulty when user selects a difficulty from one of them drop downs
	private void setDifficulty(int diff) {
		Alphabet a = board.getA();
		frame.dispose();
		if (diff == 0){
			new GUI(new GameBoard(3,4, a));
		} else if(diff == 1) {
			new GUI(new GameBoard(4,7, a));
		} else {
			new GUI(new GameBoard(7,8, a));
		}
	}
	
	//add function for setting the alphabet when the user selects one from the drop down
	private void setAlphabet(int alph) {
		int rows = board.getRows();
		int cols = board.getCols();
		frame.dispose();
		if (alph ==0) {
			new GUI(new GameBoard(rows,cols, new SymbolAlphabet()));
		} else {
			new GUI(new GameBoard(rows,cols, new LatinAlphabet()));
		}
	}
	
	//function to create the menu bar
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JLabel name = new JLabel("");
		JMenu menu = new JMenu("More...");
		menuBar.add(menu);
		//drop down for instructions and leader board
		JMenuItem exitButton = new JMenuItem("EXIT GAME");
		exitButton.addActionListener(event -> System.exit(0));
		menu.add(exitButton);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		menu.add(instructionsItem);
		
		JMenuItem leaderBoardItem = new JMenuItem("Leader Board");
		leaderBoardItem.addActionListener(event -> new LeaderBoardDialog(this));
		menu.add(leaderBoardItem);
		
		
		JMenu difficulty = new JMenu("Difficulty");
		menuBar.add(difficulty);
		
		
		//added in some buttons to the menu section for difficulty. 
		//Will reset the wordle frame when you choose a new one
		JMenuItem easyMode = new JMenuItem("Easy");
		easyMode.addActionListener(event -> this.setDifficulty(0));
		difficulty.add(easyMode);
		
		JMenuItem mediumMode = new JMenuItem("Medium");
		mediumMode.addActionListener(event -> this.setDifficulty(1));
		difficulty.add(mediumMode);
		
		JMenuItem hardMode = new JMenuItem("Hard");
		hardMode.addActionListener(event -> this.setDifficulty(2));
		difficulty.add(hardMode);
		
		JMenu alphabet = new JMenu("Alphabet");
		menuBar.add(alphabet);
		JMenuItem symbol = new JMenuItem("Symbol Game Board");
		symbol.addActionListener(event -> this.setAlphabet(0));
		alphabet.add(symbol);
		JMenuItem latin = new JMenuItem("Latin Game Board");
		latin.addActionListener(event -> this.setAlphabet(1));
		alphabet.add(latin);
		JLabel turn = new JLabel("     "+"Turn: "+BoardGUI.getTurnCount()+"    ");
		turnLabel = turn;
		menuBar.add(turn);
		String curDif = "";
		if (board.getRows()==3)
			curDif="Easy";
		else if (board.getRows()==4)
			curDif = "Medium";
		else 
			curDif = "Hard";
		JLabel dif = new JLabel(curDif+" Mode       ");
		menuBar.add(dif);
		//add a submit JButton and JTextField to input name
		JButton submit = new JButton("Submit");
		JLabel nameInput = new JLabel("Enter your name: ");
		JTextField userName = new JTextField(20);
		//action listener for clicking the submit button
		submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//get the name
                String enteredName = userName.getText();
                if (!enteredName.isEmpty()) {
                	//set the name on-screen
                	name.setText("    User: "+enteredName);
                	//set the global variable so it can be retrieved later
                	setName(enteredName);
                	userName.setVisible(false);
                	submit.setVisible(false);
                    nameInput.setVisible(false); // Hide the panel
                    //if user forgets to input name until prompted at the end, we need to check for completion again
                    BoardGUI.checkCompletion();
                } else {
                	//user didn't input a name
                    JOptionPane.showMessageDialog(frame, "Please enter a name!");
                }
            }
        });
		menuBar.add(nameInput);
		menuBar.add(userName);
		menuBar.add(submit);
		menuBar.add(name);
		return menuBar;
	}
	
	//create title panel
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		JLabel label = new JLabel("Memory Game");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		return panel;
	}
	
	public void shutdown() {
		frame.dispose();
		System.exit(0);
	}
	
	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}
	
	public void setTurnCount(int i) {
		turnLabel.setText("     "+"Turn: "+i+"    ");
	}
	
	
}