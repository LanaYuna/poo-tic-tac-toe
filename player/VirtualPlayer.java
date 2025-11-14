package ticTacToe.player;

import ticTacToe.model.HashTagModel;
import ticTacToe.util.Delay;

public class VirtualPlayer extends AbstractPlayer {

	protected VirtualPlayer(HashTagModel hashTag) {
		super(hashTag);
	}

	@Override
	public void play() {
		
		Delay.pauseSeconds(3);
		
		if(playToWin())
			return;
		
		if(playToNotLose())
			return;
		
		if(playStrategy())
			return;
		
		playRandom();
	}

	private void playRandom() {
		int lin = ((int)(Math.random() * 10)) % 3;
		for(int i=0; i < 3; i++, lin=(++lin % 3)) {
			int col = ((int)(Math.random() * 10)) % 3;
			for(int j=0; j < 3; j++, col=(++col % 3)) {
				if(hashTag.isBlank(lin, col)) {
					hashTag.setMark(myMark, lin, col);
					return;
				}
			}
		}
		
	}

	private boolean playStrategy() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean playToNotLose() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean playToWin() {
		return false;
	}

}
