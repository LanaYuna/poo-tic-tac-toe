package ticTacToe.model;

import ticTacToe.common.Mark;

public class HashTagModel {
	
	private Mark[][] table = new Mark[3][3];
	
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
	
}
