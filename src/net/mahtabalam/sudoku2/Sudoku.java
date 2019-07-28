package net.mahtabalam.sudoku2;


public class Sudoku {

	private int[][] sudokuMatrix;
	
	public static final int BOARD_SIZE = 9;
	public static final int BOX_SIZE = 3;
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 9;

	public Sudoku(int[][] sudokuMatrix) {
		this.sudokuMatrix = sudokuMatrix;
	}

	public void solveSudoku() {
		
		if (solve(0, 0)){
			printResult();
		} else {	
			System.out.println("No feasible solution found...");
		}
	}

	private boolean solve(int rowIndex, int columnIndex) {
		
		// Move to the first Row
		if( columnIndex == BOARD_SIZE){
			rowIndex = rowIndex + 1;
		}
				
	
		if( rowIndex == BOARD_SIZE && columnIndex == BOARD_SIZE ){
			return true;
		}
		
		// Move to the first Row
		if( columnIndex == BOARD_SIZE){
			columnIndex = 0;
		}
		
		
		if ( sudokuMatrix[rowIndex][columnIndex] != 0 ) { // skip filled cells
			return solve(rowIndex, columnIndex + 1);
		}

		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			
			if ( isValid(rowIndex, columnIndex, number) ) {
				
				sudokuMatrix[rowIndex][columnIndex] = number;
				
				if ( solve(rowIndex, columnIndex + 1) )
					return true;
			}
		}
		
	    //  BACKTRACK !!!
		sudokuMatrix[rowIndex][columnIndex] = 0; 
		
		return false;
	}

	private boolean isValid(int rowIndex, int columnIndex, int number) {
		
		// check if the same row alreday have that same digit
		for (int i = 0; i < BOARD_SIZE; i++) 
			if ( sudokuMatrix[rowIndex][i] == number )
				return false;
		
		// check if the same column alreday have that same digit
		for (int j = 0; j < BOARD_SIZE; j++) 
			if ( sudokuMatrix[j][columnIndex] == number )
				return false;

		// check if the same subgrid(box) alreday have that same digit
		int boxRowOffset = (rowIndex / 3) * BOX_SIZE;
		int boxColumnOffset = (columnIndex / 3) * BOX_SIZE;
		
		for (int i = 0; i < BOX_SIZE; i++)
			for (int j = 0; j < BOX_SIZE; j++)
				if (sudokuMatrix[boxRowOffset + i][boxColumnOffset + j] == number)
					return false;

		return true;
	}

	private void printResult() {
		
		for (int i = 0; i < BOARD_SIZE; ++i) {
			
			if(i % 3 == 0) System.out.println(" ");
			
			for (int j = 0; j < BOARD_SIZE; ++j) {
				
				if (j % 3 == 0) System.out.print(" ");
				System.out.print(sudokuMatrix[i][j]+" ");
			}
			
			System.out.println(" ");
		}	
	}
}