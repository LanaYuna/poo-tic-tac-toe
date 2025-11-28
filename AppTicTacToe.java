package ticTacToe;

import ticTacToe.common.Mark;
import ticTacToe.control.HashTagControl;
import ticTacToe.model.HashTagModel;
import ticTacToe.model.ScoreModel;
import ticTacToe.player.Player;
import ticTacToe.player.UserPlayer;
import ticTacToe.player.VirtualPlayer;
import ticTacToe.util.Console;
import ticTacToe.view.AppView;
import ticTacToe.view.HashTagView;
import ticTacToe.view.ScoreView;

public class AppTicTacToe {
	
	public static enum GameType {HUMAN_HUMAN, HUMAN_COMPUTER};
	
	private AppView appView = new AppView();
	
	private HashTagModel hashTagModel;
	private HashTagView hashTagView;
	private HashTagControl hashTagControl;
	
	private ScoreModel scoreModel;
	private ScoreView scoreView;
	
	private Player[] vPlayer = new Player[2];
	
	private AppTicTacToe() {
		createGame();
	}
	
	private void createGame() {
		
		hashTagModel = new HashTagModel();
		hashTagView = new HashTagView(hashTagModel);
		
		scoreModel = new ScoreModel();
		scoreView = new ScoreView(scoreModel);
		
		hashTagControl = new HashTagControl(hashTagModel, hashTagView,
											scoreModel, scoreView);
		
	}
	
	private void createPlayers(GameType gameType) {
		
		vPlayer[0] = createHumanPlayer();
		char charMark = Console.readChar("Marca [X, O]:", 'X', 'x', 'O', 'o');
		vPlayer[0].setMark(Mark.valueOf((""+charMark).toUpperCase()));
		
		vPlayer[1] = (gameType == GameType.HUMAN_HUMAN)?
				createHumanPlayer() : new VirtualPlayer(hashTagModel);
		vPlayer[1].setMark(vPlayer[0].getMark() == Mark.X ? Mark.O : Mark.X);
		
		hashTagControl.setPlayerA(vPlayer[0]);
		hashTagControl.setPlayerB(vPlayer[1]);
	}
	
	private Player createHumanPlayer() {
		
		Player player = new UserPlayer(hashTagModel);
		String name = Console.readLine("Nome: ");
		player.setName(name);
		
		return player;
	}
	
	private void go() {
		
		appView.showWelcomeMessage();
		
		GameType gameType = appView.askForGameType();
		createPlayers(gameType);
		
		do {
			
			hashTagControl.go();
		} while(appView.askForNewGame());
		
		appView.showGoodbyeMessage();
	}

	public static void main(String[] args) {
		
		AppTicTacToe app = new AppTicTacToe();
		app.go();
		
		HashTagModel model = new HashTagModel();
		HashTagView view = new HashTagView(model);
		ScoreModel scoreModel = new ScoreModel();
		ScoreView scoreView = new ScoreView(scoreModel);
		
		HashTagControl control = new HashTagControl(model, view, scoreModel, scoreView);
		
		Player user = new UserPlayer(model);
		user.setMark(Mark.X);
		control.setPlayerA(user);
		
		Player robot = new VirtualPlayer(model);
		robot.setMark(Mark.O);
		control.setPlayerB(robot);
		
		control.go();
		
	}
}
