package guimemorygame;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		visible = true;
	}

	public Character getSymbol() {
		return symbol;
	}
	
	public void setVisible(boolean v) {
		visible = v;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(GamePiece other) {
		return this.symbol.equals(other.getSymbol());
	}
}
