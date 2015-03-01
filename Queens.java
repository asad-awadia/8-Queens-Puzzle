class Queens {
	//Global board variable - 2d boolean array - True represents queen at [column][row]
	static boolean[][] board = new boolean[8][8];

	//Utility print function to display the entire board
	private static void print(boolean[][] board){
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (board[j][i]) {
					System.out.print("Q ");
				}
				else{
					System.out.print("* ");
				};
			}
			System.out.println("");
		}
	}

	//Initialisation method to set the entire board to false
	private static void init(boolean[][] board){
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				board[i][j] = false;
			}
		}
	}

	//Check if column c, row r is a safe/valid position on the board
	private static boolean valid(int c, int r){
		//check columns
		for (int i = 0; i < 8; ++i) {
			if (i == c) {
				continue;
			}
			else if(board[i][r]){
				return false;
			}
		}
		//check rows
		for (int i = 0; i < 8; ++i) {
			if (i == r) {
				continue;
			}
			else if (board[c][i]){
				return false;
			}
		}
		//check diagnols
		boolean temp = false;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (i==c && j==r) {
					continue;
				}
				if (board[i][j]) {
					int deltaRow = r - j;
					int deltaCol = c - i;
					if (Math.abs(deltaCol) == Math.abs(deltaRow)) {
						temp = true;
						break;
					}
				}
			}
		}
		if (temp) {
			return false;
		}
		return true;
	}

	//Worker method
	private static void solve(int c, int r){
		if (c == 8) {
			return;	//c == 8 the board is done
		}
		if(r == 8){	//r == 8 means solution was not found - Must backtrack
			for (int i = 0; i < 8; ++i) {
				if (board[c-1][i]) {
					board[c-1][i] = false;
					solve(c-1, i+1);
					break;
				}
			}
		}
		else if(valid(c,r)){	//valid spot change board[c][r] to true and move to the next column - recurse
			board[c][r] = true;
			solve(c+1,0);
		}
		else solve(c, r+1);	//c,r wasn't valid - go to the next row - recurse
	}

	//Main
	public static void main(String[] args){
		init(board);	//Init board
		solve(0,0);		//Solve
		print(board);	//Display solution
	}
}





