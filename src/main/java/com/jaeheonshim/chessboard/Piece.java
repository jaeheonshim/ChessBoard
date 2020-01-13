package com.jaeheonshim.chessboard;

public abstract class Piece {
	 private boolean killed = false;
	 private boolean white;

	 public Piece (boolean white) {
		  this.white = white;
	 }

	 public boolean isWhite() {
	 	 return white;
	 }

	 public abstract boolean canMove(Board board, Spot start, Spot end);
}
