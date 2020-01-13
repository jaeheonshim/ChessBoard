package com.jaeheonshim.chessboard;

public class Spot {
	 private Piece piece;
	 private int x, y;

	 public Spot(int x, int y, Piece piece) {
	 	 this.x = x;
	 	 this.y = y;
	 	 this.piece = piece;
	 }

	 public Piece getPiece () {
		  return piece;
	 }

	 public void setPiece (Piece piece) {
		  this.piece = piece;
	 }

	 public int getX () {
		  return x;
	 }

	 public int getY () {
		  return y;
	 }
}
