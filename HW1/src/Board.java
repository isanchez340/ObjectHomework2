

import java.io.IOException;
import java.util.Random;

public class Board {
	private int size;
	public int[][] boardarray;
	ConsoleUI ui = new ConsoleUI();
	private boolean[][] x;//placement checker for columns
	private boolean[][] y;//placement checker for rows
	
	public Board() throws IOException {
		size = ui.askSize();
		boardarray = new int[size][size];
		x = new boolean[size + 1][size + 1];
		y = new boolean[size + 1][size + 1];
	}
	
	public void fillBoard() { //asks user for coordinates and number desired and makes checks too see that the number and placement is valid and if it, the number gets
		String[] coordinates = ui.boardInput();//placed on the boardarray and the board gets printed out to the terminal
		int[] coor = new int[coordinates.length];
		if(ui.isNumber(coordinates)) {
			for(int i = 0; i < coor.length; ++i) {
				coor[i] = Integer.parseInt(coordinates[i]);
				if(coor[i] > size + 1 || coor[i] < 1) {
					ui.showMessage("Invalid input");
					return;				
				}
			}
		}
		else {
			ui.showMessage("Invalid input");
			return;
		}

		if(x[coor[0]][coor[2]] == true || y[coor[1]][coor[2]] == true) {//checks to see if number is not already placed in colunm and row
			ui.showMessage("Invalid input");
			return;
		}
		else {
			x[coor[0]][coor[2]] = true;
			y[coor[1]][coor[2]] = true;
		}
		coor[0] -= 1;//checks sub-grid to see if the number is not already present
		coor[1] -= 1;
		int sq = (int) Math.sqrt(size);
		int n = coor[1] / sq;
		int m = coor[0] / sq;
		n = (n * sq);
		m = (m * sq);
		for(int i = n; i < n + sq; ++i) {
			for(int j = m; j < m + sq; ++j) {
				if(boardarray[i][j] == coor[2]) {
					ui.showMessage("Invalid input");
					return;
				}
			}
		}
		boardarray[coor[1]][coor[0]] = coor[2];
		ui.printB(boardarray);
	}

	public static Random rand = new Random();
	
	public boolean isSolved() { //checks boardarray for missing entries and if the whole array is filled with non-zero numbers, true is returned
		for(int i = 0; i < size; ++i) {
			for(int j = 0; j < size; ++j) {
				if(boardarray[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
