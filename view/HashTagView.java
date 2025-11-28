package ticTacToe.view;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.util.Console;

public class HashTagView {
	
	private final 
	HashTagModel model;
	
	static final
	String alertFormat = "\nERRI - %s\nPreste mais atenção!\n";
	
	public HashTagView(HashTagModel model) {
		this.model = model;
	}
	
	private String mark(int lin, int col) {
		
		Mark mark = model.getMark(lin, col);
		return (mark != Mark.BLANK ? mark.toString() : " ");
	}
	
	public void print() {
		
		Console.printf(hashFormat, mark(0,0), mark(0,1), mark(0,2),
			       mark(1,0), mark(1,1), mark(1,2),
			       mark(2,0), mark(2,1), mark(2,2));
		
		Console.println();
	}
	
	public void printError(String message) {
		Console.printf(alertFormat, message);
	}
	
	public void printGameOver(Mark winner) {
		Console.println();
		if(winner != Mark.BLANK) 
			Console.printf("'%s' Venceu.", winner);
		else 
			Console.println("DEU VÉIA");
	} 
	
	static final 
	String hashFormat = """
		     |     |    
		  %s  |  %s  |  %s       
		_____|_____|______
		     |     |     
		  %s  |  %s  |  %s  
		_____|_____|______
		     |     |    
		  %s  |  %s  |  %s  
		     |     |      """;
}
