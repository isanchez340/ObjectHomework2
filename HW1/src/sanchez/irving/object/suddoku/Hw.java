package sanchez.irving.object.suddoku;

import java.io.IOException;

public class Hw {
	private ConsoleUI ui = new ConsoleUI();
	private Board board;
	
	public static void main (String[] args) throws IOException {
		new Hw().play();
	}
	private void play() throws IOException {
		ui.welcome();
		board = new Board();
		while(!board.isSolved()) {
			board.fillBoard();
		}
		ui.showMessage("Solved!");
	}
}
