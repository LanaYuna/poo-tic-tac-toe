package ticTacToe.player;

import ticTacToe.model.HashTagModel;

import ticTacToe.util.Console;

public class UserPlayer extends AbstractPlayer {

	public UserPlayer(HashTagModel hashTag) {
		super(hashTag); // TABULEIRO
	}

	@Override
	public void play() {
		Console.println("Sua vez");
		int lin = Console.readInt("Linha:");
		int col = Console.readInt("Coluna:");
		
		hashTag.setMark(myMark, lin, col);
	}

}
