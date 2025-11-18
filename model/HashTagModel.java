package ticTacToe.model;

import ticTacToe.common.Mark;

public class HashTagModel {
	
	private Mark[][] table = new Mark[3][3];
	
	public HashTagModel(){
		reset();
	}
	
	public void reset() {
		for(int lin=0; lin < table.length; lin++) {
			for(int col=0; col < table[lin].length; col++) {
				table[lin][col] = Mark.BLANK;
			}
		}
	}
	
	public Mark getMark(int lin, int col) {
		if(lin < 0 || lin > 2 || col < 0 || col > 2)
			throw HashTagModelException.outOfBounds(lin, col);
		return table[lin][col];
	}
	
	public void setMark(Mark mark, int lin, int col) {
		if(lin < 0 || lin > 2 || col < 0 || col > 2)
			throw HashTagModelException.outOfBounds(lin, col);
		table[lin][col] = mark;
	}
	
	public Mark[] getMarksOfLine(int lin) {
		if (lin < 0 || lin > 2)
			throw HashTagModelException.lineOutOfBounds(lin);
		
		Mark[] line = new Mark[3];
		for(int col=0; col < 3; col++)
			line[col] = table[lin][col];
		
		return line;
	}
	
	public Mark[] getMarksOfColumn(int col) {
		if (col < 0 || col > 2)
			throw HashTagModelException.columnOutOfBounds(col);
		
		Mark[] column = new Mark[3];
		for(int lin=0; lin < 3; lin++)
			column[lin] = table[lin][col];
		
		return column;
	}
	
	public Mark[] getMarksOfMainDiagonal() {
		Mark[] mainDiagonal = new Mark[3];
		for(int col=0; col < 3; col++) {
			mainDiagonal[col] = table[col][col];
		}
		
		return mainDiagonal;
	}
	
	public Mark[] getMarksOfSecondDiagonal() {
		Mark[] secondDiagonal = new Mark[3];
		for(int lin=0; lin < 3; lin++) {
			secondDiagonal[lin] = table[lin][2-lin];
		}
		
		return secondDiagonal;
	}
	
	private boolean isInvalid(int index) {
		return (index < 0 || index > 2);
	}
	
	private void checkBounds(int lin, int col) {
			if(isInvalid(lin) || isInvalid(col))
				throw HashTagModelException.outOfBounds(lin, col);
	}
	
	public boolean isBlank(int lin, int col) {
		checkBounds(lin, col);
		return( table[lin][col] == Mark.BLANK);
	}
	
	public boolean hasBlank() {
		
		for(int lin=0; lin < 3; lin++) {
			for(int col=0; col < 3; col++) {
				if( isBlank(lin, col))
					return true;
			}
		}
		return false;
	}
	
	public boolean isAllBlank() {
		
		for(int lin=0; lin < 3; lin++) {
			for(int col=0; col < 3; col++) {
				if( !isBlank(lin, col))
					return false;
			}
		}
		return true;
	}
	
	public boolean allEquals(Mark[] array ,Mark mark) {
		return array[0] == mark && array[1] == mark && array[2] == mark;
	}
	
	public boolean isWinner(Mark mark) {
		
		for(int i=0; i < 3; i++) {
			if(allEquals(getMarksOfLine(i), mark))
				return true;
		}
		
		for(int i=0; i < 3; i++) {
			if(allEquals(getMarksOfColumn(i), mark))
				return true;
		}
		
		if(allEquals(getMarksOfMainDiagonal(), mark))
			return true;
		
		if(allEquals(getMarksOfSecondDiagonal(), mark))
			return true;
		
		return false;
	}
	
	public boolean isDraw() {
		 if (isWinner(Mark.O)) 
			 return false;
		 if (isWinner(Mark.X)) 
			 return false;
		 if (hasBlank())    
			 return false;

		 return true;
	}
	
}
