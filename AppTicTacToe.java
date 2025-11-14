package ticTacToe;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.player.UserPlayer;
import ticTacToe.view.HashTagView;

public class AppTicTacToe {

	public static void main(String[] args) {
		
		HashTagModel model = new HashTagModel();
		HashTagView view = new HashTagView(model);
		UserPlayer player = new UserPlayer(model);
		player.setMark(Mark.X);
		
		for(int i=0; i < 5; i++) {
			player.play();
			view.print();
		}
	}
}
