package ticTacToe.player;

import ticTacToe.common.Mark;

public interface Player {

	public void play();
	
	public void setMark(Mark mark);
	
	public Mark getMark();
	
}
