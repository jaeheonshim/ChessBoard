package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class King extends Piece {
	 public King (boolean white) {
		  super(white);
	 }

	 @Override public boolean canMove (Board board, Spot start, Spot end) {
	 	 for(Spot[] spots : board.getBoard()) {
	 	 	 for(Spot spot : spots) {
	 	 	 	 if(spot.getPiece().canMove(board, spot, end)) {
	 	 	 	 	 return false; //if any piece on the board can kill the king after it has made its move, you will be unable to make the move.
				 }
			 }
		 }


	 }

	 @Override public String toString () {
		  if(isWhite()) {
		  	 return "K";
		  } else {
		  	 return "k";
		  }
	 }
}
