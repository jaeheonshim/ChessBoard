package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class Knight extends Piece {
	 private final int[] movesX = new int[] {-1, -2, -2, -1, +1, +2, +2, +1};
	 private final int[] movesY = new int[] {+2, +1, -1, -2, -2, -1, +1, +2};

	 public Knight (boolean white) {
		  super(white);
	 }

	 @Override public boolean canMove (Board board, Spot start, Spot end) {
	 	 if(start.getX() > 8 || start.getX() < 0 || start.getY() > 8 || start.getY() < 0) {
	 	 	 return false;
		 }

	 	 for(int i = 0; i <= 8; i++) {
	 	 	 if(start.getX() + movesX[i] == end.getX() && start.getY() + movesY[i] == end.getY()) {
	 	 	 	 return true;
			 }
		 }

	 	 return false;
	 }

	 public String toString () {
		  return null;
	 }
}
