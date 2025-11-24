package ticTacToe.player;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;

public abstract class AbstractPlayer implements Player{
	
	protected
	Mark myMark = null;
	
	protected
	Mark opponentMark = null;
	
	protected final
	HashTagModel hashTag;
	
	protected AbstractPlayer(HashTagModel hashTag) {
		this.hashTag = hashTag;
	}
	
	public final
	void setMark(Mark mark) {
		this.myMark = mark;
		opponentMark = (myMark == Mark.X) ? Mark.O : Mark.X;
	}
	
	public final
	Mark getMark() {
		return myMark;
	}
	
	public abstract void play();
}
