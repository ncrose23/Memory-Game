package guimemorygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class InstructionsDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;

	
	private final CancelAction cancelAction;

	public InstructionsDialog(GUI view) {

		super(view.getFrame(), "Instructions", true);
		System.out.println("Instructions");
		this.cancelAction = new CancelAction();
		
		add(createMainPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(view.getFrame());
		setVisible(true);
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		Font titleFont = AppFonts.getTitleFont();
		Font textFont = AppFonts.getTextFont();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 5, 30);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Instructions for the Memory Game");
		label.setFont(titleFont);
		label.setHorizontalAlignment(JLabel.CENTER);
		Color backgroundColor = label.getBackground();
		panel.add(label, gbc);
		
		gbc.gridy++;
		String text = "Welcome to the memory game! The program is based on a real-world memory game"
				+ " consisting of some number of tiles. The tiles each have a symbol on their face, and tiles come in "
				+ "pairs so that there are exactly two of every symbol. The tiles cannot be seen "
				+ "when they're face down. To play the game, the tiles all start face down, and then are shuffled randomly. "
				+ "The player chooses two tiles and flips them over. If their symbols match, "
				+ "the tiles are left face up and the players turn continues. "
				+ "If the tiles don't match the tiles are returned to the face down positon and the turn is over. "
				+ "The player should try to remember where the mismatched tiles are for future turns. "
				+ "The player tries to minimize the number of turns required to reveal all the tiles."
				+ "A turn continues after a match, so the game can theoretically be won in one turn.";
		JTextArea textArea = new JTextArea(10, 60);
		textArea.setBackground(backgroundColor);
		textArea.setEditable(false);
		textArea.setFont(textFont);
		textArea.setLineWrap(true);
		textArea.setText(text);
		textArea.setWrapStyleWord(true);
		panel.add(textArea, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy++;
		label = new JLabel("Programmer:");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("Nicholas Rose");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Date Created:");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("December 2023");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Version:");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		label = new JLabel("1.2");
		label.setFont(textFont);
		panel.add(label, gbc);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", cancelAction);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(cancelAction);
		panel.add(button);
		
		return panel;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			dispose();
		}
		
	}

}
