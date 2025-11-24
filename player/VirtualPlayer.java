package ticTacToe.player;

import ticTacToe.common.Mark;
import ticTacToe.model.HashTagModel;
import ticTacToe.util.Delay;

public class VirtualPlayer extends AbstractPlayer {

	public VirtualPlayer(HashTagModel hashTag) {
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
		int bestScore = Integer.MIN_VALUE;
		int bestLin = -1;
		int bestCol = -1;
		
		for(int lin=0; lin < 3; lin++) {
			for(int col=0; col < 3; col++) {
				if(hashTag.isBlank(lin, col)) {
					
					hashTag.setMark(myMark, lin, col);
					int score = minimax(false);
					hashTag.setMark(Mark.BLANK, lin, col);
					if(score > bestScore) {
						bestScore = score;
						bestLin = lin;
						bestCol = col;
					}
				}
			
			}
		}
		
		hashTag.setMark(myMark, bestLin, bestCol);
		return true;		
	}
	
	private int minimax(boolean isMaximizing) {
		if(hashTag.isWinner(myMark)) {
			return +1;
		}
		
		if(hashTag.isWinner(opponentMark)) {
			return -1;
		}
		
		if(hashTag.isDraw()) {
			return 0;
		}
		
		if(isMaximizing) {
			int bestScore = Integer.MIN_VALUE;
			for(int lin=0; lin < 3; lin++) {
				for(int col=0; col < 3; col++) {
					
					if(hashTag.isBlank(lin, col)) {
						hashTag.setMark(myMark, lin, col);
						int score = minimax(false);
						hashTag.setMark(Mark.BLANK, lin, col);
						bestScore = Math.max(score, bestScore);
					}
				}	
			}
			return bestScore;
		}
		else {
			int bestScore = Integer.MAX_VALUE;
			for(int lin=0; lin < 3; lin++) {
				for(int col=0; col < 3; col++) {
					
					if(hashTag.isBlank(lin, col)) {
						hashTag.setMark(opponentMark, lin, col);
						int score = minimax(true);
						hashTag.setMark(Mark.BLANK, lin, col);
						bestScore = Math.min(score, bestScore);
					}
				}
			}
			return bestScore;
		}	
	}

	private boolean playToNotLose() { 
	    for (int lin = 0; lin < 3; lin++) {
	        if (checkAndBlock(hashTag.getMarksOfLine(lin), lin, true))
	            return true;
	    }

	    for (int col = 0; col < 3; col++) {
	        if (checkAndBlock(hashTag.getMarksOfColumn(col), col, false))
	            return true;
	    }

	    if (checkAndBlockDiagonal(hashTag.getMarksOfMainDiagonal(), true))
	        return true;

	    if (checkAndBlockDiagonal(hashTag.getMarksOfSecondDiagonal(), false))
	        return true;

	    return false;
	}
	
	private boolean checkAndBlock(Mark[] marks, int index, boolean isLine) {
		
		int opponentCount = 0;
		int blankSpace = -1;
		
		for(int i=0; i < 3; i++) {
			if(marks[i] == opponentMark) {
				opponentCount++;
			} else if(marks[i] == Mark.BLANK) {
				blankSpace = i;
			}
		}
		
		 if (opponentCount == 2 && blankSpace != -1) {
		
		        if (isLine)
		            hashTag.setMark(myMark, index, blankSpace);
		        else
		            hashTag.setMark(myMark, blankSpace, index);

		        return true;
		    }
		 
		return false;
	}
	
	private boolean checkAndBlockDiagonal(Mark[] diagonal, boolean isMain) {
		
		int opponentCount = 0;
		int blankSpace = -1;
		
		for (int i = 0; i < 3; i++) {
		        if (diagonal[i] == opponentMark)
		            opponentCount++;
		        else if (diagonal[i] == Mark.BLANK)
		            blankSpace = i;
		    }

		    if (opponentCount == 2 && blankSpace != -1) {
		        
		        if (isMain) {
		            hashTag.setMark(myMark, blankSpace, blankSpace);
		        } else {
		            int lin = blankSpace;
		            int col = 2 - blankSpace;
		            hashTag.setMark(myMark, lin, col);
		        }

		        return true;
		    }
		return false;
	}

	private boolean playToWin() {
		for (int lin = 0; lin < 3; lin++) {
	        for (int col = 0; col < 3; col++) {
	            if (hashTag.isBlank(lin, col)) {
	            	if (iCanWin(lin, col)) {
	                    hashTag.setMark(myMark, lin, col);
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	private boolean iCanWin(int lin, int col) {
	    return canWin(myMark, lin, col);
	}
	
	private boolean canWin(Mark mark, int lin, int col) {

	    if (!hashTag.isBlank(lin, col)) {
	        return false;
	    }

	    Mark[] line = hashTag.getMarksOfLine(lin);
	    if (line[0] == mark && line[1] == mark && line[2] == Mark.BLANK && col == 2) return true;
	    if (line[0] == mark && line[2] == mark && line[1] == Mark.BLANK && col == 1) return true;
	    if (line[1] == mark && line[2] == mark && line[0] == Mark.BLANK && col == 0) return true;

	    Mark[] column = hashTag.getMarksOfColumn(col);
	    if (column[0] == mark && column[1] == mark && column[2] == Mark.BLANK && lin == 2) return true;
	    if (column[0] == mark && column[2] == mark && column[1] == Mark.BLANK && lin == 1) return true;
	    if (column[1] == mark && column[2] == mark && column[0] == Mark.BLANK && lin == 0) return true;

	    if (lin == col) {
	        Mark[] diag = hashTag.getMarksOfMainDiagonal();
	        if (diag[0] == mark && diag[1] == mark && diag[2] == Mark.BLANK && lin == 2) return true;
	        if (diag[0] == mark && diag[2] == mark && diag[1] == Mark.BLANK && lin == 1) return true;
	        if (diag[1] == mark && diag[2] == mark && diag[0] == Mark.BLANK && lin == 0) return true;
	    }

	    if (lin + col == 2) {
	        Mark[] diag2 = hashTag.getMarksOfSecondDiagonal();
	        if (diag2[0] == mark && diag2[1] == mark && diag2[2] == Mark.BLANK && lin == 2 && col == 0) return true;
	        if (diag2[0] == mark && diag2[2] == mark && diag2[1] == Mark.BLANK && lin == 1 && col == 1) return true;
	        if (diag2[1] == mark && diag2[2] == mark && diag2[0] == Mark.BLANK && lin == 0 && col == 2) return true;
	    }

	    return false;
	}
}

