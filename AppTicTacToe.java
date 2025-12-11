package ticTacToe;

import ticTacToe.common.Mark;
import ticTacToe.control.HashTagControl;
import ticTacToe.model.BestPlayersModel;
import ticTacToe.model.HashTagModel;
import ticTacToe.model.ScoreModel;
import ticTacToe.player.Player;
import ticTacToe.player.UserPlayer;
import ticTacToe.player.VirtualPlayer;
import ticTacToe.util.Console;
import ticTacToe.view.AppView;
import ticTacToe.view.BestPlayersView;
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
	
	private static final
	String HISTORY_FILENAME = "TicTacToeHistory.obj";
	
	private BestPlayersModel bestPlayersModel;
	private BestPlayersView bestPlayersView;
	
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
		
		readOrCreateHistory();
	}
	
	private void readOrCreateHistory() {
		
		try {
			bestPlayersModel = BestPlayersModel.readFromFile(HISTORY_FILENAME);
		}
		catch(Exception e) {
			bestPlayersModel = new BestPlayersModel(10);
		}
		finally {
			bestPlayersView = new BestPlayersView(bestPlayersModel);
		}
	}
	
	private void updateHistory() {
		
		Player playerA = vPlayer[0];
		Player playerB = vPlayer[1];
		
		int scoreA = scoreModel.scoreOf(playerA.getMark());
		int scoreB = scoreModel.scoreOf(playerB.getMark());
		int scoreDraw = scoreModel.scoreOf(Mark.BLANK);
		
		if(scoreA >= scoreB) 
			bestPlayersModel.addBestPlayer(playerA.getName(),
											scoreA, scoreB, scoreDraw);
		
		if(scoreB >= scoreA)
			bestPlayersModel.addBestPlayer(playerB.getName(),
											scoreB, scoreA, scoreDraw);
	}
	
	private void writeHistory() {
		
		try {
			bestPlayersModel.writeToFile(HISTORY_FILENAME);
		}
		catch(Exception e) {
			hashTagView.printError(e.getMessage());
		}
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
		
		updateHistory();
		bestPlayersView.print();
		appView.showGoodbyeMessage();
		Console.readLine("ENTER");
		writeHistory();
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
