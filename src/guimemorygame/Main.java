package guimemorygame;
import javax.swing.SwingUtilities;

public class Main {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InstructionsDialog(new GUI(new GameBoard(4,7,new SymbolAlphabet())));
	}
	*/
	public static void main(String[] args) {
    try {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	System.out.println("MAIN");
                new InstructionsDialog(new GUI(new GameBoard(4,7,new SymbolAlphabet())));  // Initialize your GUI class here
            }
        });
    } catch (Exception e) {
        e.printStackTrace();  // Print any errors to help with debugging
    }
}

}
