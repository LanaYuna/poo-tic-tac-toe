package ticTacToe.view;

import ticTacToe.model.ScoreModel;
import ticTacToe.util.Console;

public class ScoreView {
	
	private final ScoreModel scoreModel;
	
	public ScoreView(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;
	}
	
	static final String
		scoreFormat = """
				-------------------------
					   P L A C A R
				-------------------------
				___X___|___O___|___#___
				  %2d  |  %2d  |  %2d
				-------------------------
				""";
	
	public void printScore() {
		Console.printf(scoreFormat, scoreModel.scoreX(), scoreModel.scoreO(), scoreModel.hashTagTimes());
	}
}
