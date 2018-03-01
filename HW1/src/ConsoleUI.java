
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI {
	private Scanner in;
	private OutputStream out;
	
	public ConsoleUI() {
		in = new Scanner(System.in);
		out = System.out;
	}
	
	@SuppressWarnings("resource")
	public ConsoleUI(Scanner in, OutputStream out) { //input and output initialization
		in = new Scanner(System.in);
		out = System.out;
	}
	
	public void welcome() {
        showMessage("Welcome");
    }
	
    public int askSize() throws IOException { //ask user for desired size of board and checks that it is a valid size for a sudoku board.
    	boolean flag = true;
    	String n = "";
    	int usersize = 0;
        showMessage("Enter the board size (4, 9, 16, ...) or -1 to quit?");
        n = in.nextLine();
        String[] temp = new String[] {"1", "2", "3"};
        String[] temp1 = n.split("\\s+");
        temp[0] = temp1[0];
        flag = isNumber(temp);
        while(flag == false) {
        	showMessage("Enter the board size (4, 9, 16, ...) or -1 to quit?");
            n = in.nextLine();
            temp[0] = n;
            flag = isNumber(temp);
        }
        usersize = Integer.parseInt(n);
        if(usersize % Math.sqrt(usersize) != 0 || usersize == 1 || usersize == 0) {
        	return askSize();
        }
        return usersize;
    }
    
    public String[] boardInput() { // takes board input using a scanner and returns array of string containing x y and the number the user wants to input.
    	showMessage("Enter a number (x y v) or -1 to quit?");
    	String coordinates = in.nextLine();
    	if(coordinates.equals("-1")) {
    		System.exit(0);
    	}
    	String[] split = coordinates.split("\\s+");
    	return split;
    }
    
	public void showMessagesl(String msg) {// prints message and stays in same print line
		((PrintStream) out).print(msg);
	}
    
	public void showMessage(String msg) { //prints message and moves to next print line
		((PrintStream) out).println(msg);
	}
	
	public boolean isNumber(String[] coordinates){ //checks that the user input is an int for error handling
		int[] coor = new int[coordinates.length];
		try {
			for(int i = 0; i < coordinates.length; ++i) {
				coor[i] = Integer.parseInt(coordinates[i]);
				if(coor[i] == -1) {
					showMessage("Bye!");
					System.exit(0);
				}
			}
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		if(coor.length != 3) {
			this.showMessage("Invalid input");
			return false;
		}
		return true;
	}
	
	public void topdown(int n) { // prints  +-----------+-----------+-----------+ portion of board
		int m = (int) Math.sqrt(n);
		showMessagesl("   ");
		while(m != 0) {
			int o = (int) Math.sqrt(n) - 2;
			showMessagesl("+----");
			while( o != 0) {
				showMessagesl("----");
				o -= 1;
			}
			showMessagesl("---");
			m -= 1;
		}
		showMessage("+");
	}
	
	public void printB(int[][] board) { //prints board using array and helper methods
		showMessagesl(" y\\x ");
		int n = 1;
		while(n != (board.length + 1)) {
			if(n < 10) {
				showMessagesl(n + "   ");
				n += 1;
			}
			else if(n < 100) {
				showMessagesl(n + "  ");
				n += 1;
			}
			else {
				showMessagesl(n + " ");
				n += 1;
			}
		}
		showMessage("");
		topdown(board.length);
		for(int i = 0; i < board.length; ++i) {
			if(i + 1 < 10) {
				showMessagesl(" " + (i + 1) + " ");
			}
			else if(i + 1 < 100) {
				showMessagesl((i + 1) + " ");
			}
			else {
				showMessagesl((i + 1) + "");
			}
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != 0) {
					if(board[i][j] < 10) {
						showMessagesl("| " + board[i][j] + " ");
					}
					else if(board[i][j] < 100) {
						showMessagesl("| " + board[i][j]);
					}
					else {
						showMessagesl("|" + board[i][j]);
					}
				}
				else {
					showMessagesl("|   ");
				}
			}
			showMessage("|");
			if((i + 1) % Math.sqrt(board.length) == 0) {
				topdown(board.length);
			}
		}
	}
}
