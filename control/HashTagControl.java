package ticTacToe.control;

import java.util.Optional;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.model.HashTagModelException;
import ticTacToe.model.ScoreModel;
import ticTacToe.player.Player;
import ticTacToe.view.HashTagView;
import ticTacToe.view.ScoreView;

public class HashTagControl {
	
	// DEPENDÊNCIAS
	private HashTagModel model;
	private HashTagView view;
	private ScoreModel scoreModel;
	private ScoreView scoreView;
	
	private Player playerA;
	private Player playerB;
	private Player startPlayer;
	
	public HashTagControl(HashTagModel model, HashTagView view, ScoreModel scoreModel, ScoreView scoreView) {
		this.model = model;
		this.view = view;
		
		this.scoreModel = scoreModel;
		this.scoreView = scoreView;
	}
	
	private Optional<Player> winner = Optional.empty();
	
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	
	public void go() {
		gameStart();
		Player currentPlayer = startPlayer;
		
		while(model.hasBlank()) {
			
			doPlay(currentPlayer);
			view.print();
			
			checkForWinner();
			
			if(winner.isPresent())
				break;
			
			currentPlayer = ((currentPlayer == playerA) ? playerB : playerA);
			
		}
		gameOver();
	}
	
	private void doPlay(Player player) {
		
		while(true) {
			try {
				player.play();
				return;
			}
			catch(HashTagModelException e) {
				view.printError(e.getMessage());
			}
		}
	}
	
	private Player getPlayer(Mark mark){
		
		if(playerA.getMark() == mark) {
			return playerA;
			
		} else if(playerB.getMark() == mark) {
			return playerB;
			
		} else {
			throw new RuntimeException("Jogador inexistente");
		}
		 
	}
	
	private void defineStartPlayer() {
		
		if(winner.isPresent()) {
			startPlayer = winner.get();
			return;
			
		} else if(startPlayer != null) {
			startPlayer = ((startPlayer == playerA) ? playerB : playerA);
			return;
			
		} else {
		    startPlayer = (Math.random() < 0.5 ? playerA : playerB);
		}
	}
	
	private void checkForWinner(Mark[] vMark) {
		
		if(winner.isPresent()) {
			return;
		} 
		
		for(Mark m : vMark) {
			if(m == Mark.BLANK) {
				return;
			}
		}

		Mark winningMark = vMark[0];
		Player winningPlayer = getPlayer(winningMark);
		winner = Optional.of(winningPlayer);
	}
	
	private void checkForWinner() {
		for(int i=0; i < 3; i++) {
			
			if(model.allEquals(model.getMarksOfLine(i), playerA.getMark())) {
				winner = Optional.of(playerA);
				return;
				
			} else if((model.allEquals(model.getMarksOfLine(i), playerB.getMark()))){
				winner = Optional.of(playerB);
				return;
			}
		}
		
		for(int i=0; i < 3; i++) {
			
			if(model.allEquals(model.getMarksOfColumn(i), playerA.getMark())) {
				winner = Optional.of(playerA);
				return;
				
			} else if((model.allEquals(model.getMarksOfColumn(i), playerB.getMark()))){
				winner = Optional.of(playerB);
				return;
			}
		}
		
		if(model.allEquals(model.getMarksOfMainDiagonal(), playerA.getMark())) {
			winner = Optional.of(playerA);
			return;
			
		} else if(model.allEquals(model.getMarksOfMainDiagonal(), playerB.getMark())) {
			winner = Optional.of(playerB);
			return;
		}
		
		if(model.allEquals(model.getMarksOfSecondDiagonal(), playerA.getMark())) {
	        winner = Optional.of(playerA);
	        return;
	    }
	    if(model.allEquals(model.getMarksOfSecondDiagonal(), playerB.getMark())) {
	        winner = Optional.of(playerB);
	        return;
	    }
	    
		winner = Optional.empty();
			
	}
	
	private void gameStart() {
		defineStartPlayer();
		model.reset();
		winner = Optional.empty();
		view.print();
	}
	
	private void gameOver() {
		Mark winnerMark = winner.isPresent()? winner.get().getMark() : Mark.BLANK;
		view.printGameOver(winnerMark);
		scoreModel.incScore(winnerMark);
		scoreView.printScore();
	}
}
