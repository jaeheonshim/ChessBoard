package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class Queen extends Piece {
	 public Queen (boolean white) {
		  super(white);
	 }

	 @Override public boolean canMove (Board board, Spot start, Spot end) {
		  if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
				return false;
		  } else if (checkKingInCheck && board.getKing(isWhite()) != null && board.getKing(isWhite()).inCheck(board)) {
			  return false;
		  }

		  if (checkKingInCheck) {
				Spot tempSpot = this.getSpot(board);
				Piece tempPiece = end.getPiece();

				end.setPiece(this);
				start.setPiece(null);

				if (board.getKing(isWhite()) != null && board.getKing(isWhite()).inCheck(board)) {
					 end.setPiece(tempPiece);
					 tempSpot.setPiece(this);
					 return false;
				}

				end.setPiece(tempPiece);
				tempSpot.setPiece(this);
		  }

		  checkKingInCheck = true;

		  if (start.getX() == end.getX()) {
				for (int i = Math.min(start.getY(), end.getY()) + 1; i < Math.max(start.getY(), end.getY()); i++) {
					 if (board.getSpot(start.getX(), i).getPiece() != null) {
						  return false;
					 }
				}
				return true;
		  } else if (start.getY() == end.getY()) {
				for (int i = Math.min(start.getX(), end.getX()) + 1; i < Math.max(start.getX(), end.getX()); i++) {
					 if (board.getSpot(i, start.getY()).getPiece() != null) {
						  return false;
					 }
				}
				return true;
		  } else if (Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY())) {
				int lowerX = Math.min(start.getX(), end.getX());
				int upperX = Math.max(start.getX(), end.getX());

				int lowerY = Math.min(start.getY(), end.getY());
				int upperY = Math.max(start.getY(), end.getY());

				int xIndex = lowerX + 1;
				int yIndex = lowerY + 1;

				while (xIndex < upperX && yIndex < upperY) {
					 if (board.getSpot(xIndex, yIndex).getPiece() != null) {
						  return false;
					 }

					 xIndex++;
					 yIndex++;
				}
				return true;
		  } else {
				return false;
		  }
	 }

	 @Override public String toString () {
		  if (isWhite()) {
				return "Q";
		  } else {
				return "q";
		  }
	 }
}
