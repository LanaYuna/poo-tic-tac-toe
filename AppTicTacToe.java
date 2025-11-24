package ticTacToe;

import ticTacToe.common.Mark;
import ticTacToe.control.HashTagControl;
import ticTacToe.model.HashTagModel;
import ticTacToe.player.Player;
import ticTacToe.player.UserPlayer;
import ticTacToe.player.VirtualPlayer;
import ticTacToe.view.HashTagView;

public class AppTicTacToe {

	public static void main(String[] args) {
		
		HashTagModel model = new HashTagModel();
		HashTagView view = new HashTagView(model);
		HashTagControl control = new HashTagControl(model, view);
		
		Player user = new UserPlayer(model);
		user.setMark(Mark.X);
		control.setPlayerA(user);
		
		Player robot = new VirtualPlayer(model);
		robot.setMark(Mark.O);
		control.setPlayerB(robot);
		
		control.go();
		
	}
}
