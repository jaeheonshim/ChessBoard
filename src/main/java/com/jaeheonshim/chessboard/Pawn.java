package com.jaeheonshim.chessboard;

public class Pawn extends Piece {
	 public Pawn(boolean white) {
	 	 super(white);
	 }

	 @Override
	 public boolean canMove(Board board, Spot start, Spot end) {
	 	 if(end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
	 	 	 //Can't kill or move over piece of same color
			  return false;
		 }
	 	 if(!isMoved()) {
	 	 	 if(start.getX() == end.getX() && Math.abs(start.getY() - end.getY()) <= 2) {
	 	 	 	 return true;
			 }
		 } else {
			  if(start.getX() == end.getX() && Math.abs(start.getY() - end.getY()) < 2) {
					return true;
			  }
		 }

	 	 if(end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) {
			  return end.getY() == start.getY() + 1;
		 }

	 	 return false;
	 }
}
