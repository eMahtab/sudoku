# Sudoku
## https://leetcode.com/problems/sudoku-solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

1. Each of the digits 1-9 must occur exactly once in each row.
2. Each of the digits 1-9 must occur exactly once in each column.
3. Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.

Empty cells are indicated by the character '.'.

**Note:**

1. The given board contain only digits 1-9 and the character '.'.
2. You may assume that the given Sudoku puzzle will have a single unique solution.
3. The given board size is always 9x9.

# Implementation

```java
class Solution {
    private static final int BOARD_SIZE = 9;
    private static final int BOX_SIZE = 3;
    private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 9;
    
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int rowIndex, int columnIndex) {
		
		// If we filled all the rows of a column, Move to the next column
		if( rowIndex == BOARD_SIZE){
			columnIndex = columnIndex + 1;
			// Base case
			if(columnIndex == BOARD_SIZE) {
				return true;
			}
			// If not the base case, we still need to fill remaining columns
			// So set the rowIndex to zero, for the next column
			rowIndex=0;
		}
				
		// only fill the empty cells
		if ( board[rowIndex][columnIndex] != '.' ) { 
			return solve(board, rowIndex + 1, columnIndex);
		}

		for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
			if ( isValid(board, rowIndex, columnIndex, number) ) {
				board[rowIndex][columnIndex] = Character.forDigit(number, 10); 
				if ( solve(board, rowIndex + 1, columnIndex) ) {
					return true;
				}		
			}
		}
		
		//  BACKTRACK !!!
		board[rowIndex][columnIndex] = '.'; 
		return false;
	}

    
    private boolean isValid(char[][] board, int rowIndex, int columnIndex, int number) {
		
		// check if the same row alreday have that same digit
		for (int i = 0; i < BOARD_SIZE; i++) 
			if ( board[rowIndex][i] == Character.forDigit(number, 10) )
				return false;
		
		// check if the same column alreday have that same digit
		for (int j = 0; j < BOARD_SIZE; j++) 
			if ( board[j][columnIndex] == Character.forDigit(number, 10) )
				return false;

		// check if the same subgrid(box) alreday have that same digit
		int boxRowOffset = (rowIndex / 3) * BOX_SIZE;
		int boxColumnOffset = (columnIndex / 3) * BOX_SIZE;
		
		for (int i = 0; i < BOX_SIZE; i++)
			for (int j = 0; j < BOX_SIZE; j++)
				if (board[boxRowOffset + i][boxColumnOffset + j] == Character.forDigit(number, 10))
					return false;

		return true;
	}
}
```

# References :
https://www.youtube.com/watch?v=N2oG4cth1uE
