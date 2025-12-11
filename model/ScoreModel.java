package ticTacToe.model;

import ticTacToe.common.Mark;

public class ScoreModel {

	private int scoreX = 0;
	private int scoreO = 0;
	private int hashTagTimes = 0; // EMPATE
	
	public void reset() {
		this.scoreX = 0;
		this.scoreO = 0;
		this.hashTagTimes = 0;
	}
	
	public void incScore(Mark mark) {
		
		switch(mark) {
			case X -> this.scoreX++;
			case O -> this.scoreO++;
			case BLANK -> this.hashTagTimes++;
		}
	}
	
	public int scoreX() {
		return this.scoreX;
	}
	
	public int scoreO() {
		return this.scoreO;
	}
	
	public int hashTagTimes() {
		return this.hashTagTimes;
	}
	
	public int scoreOf(Mark mark) {
		return switch(mark) {
			case O -> this.scoreO;
			case X -> this.scoreX;
			case BLANK -> this.hashTagTimes;
		};
	}
	
}
