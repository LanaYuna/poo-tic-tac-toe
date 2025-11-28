package ticTacToe.player;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;

public abstract class AbstractPlayer implements Player{
	
	protected
	String name = "";
	
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
	
	public final void setName(String name) {
		this.name = name;
	}
	
	public final String getName() {
		return this.name;
	}
	
	public abstract void play();
}
