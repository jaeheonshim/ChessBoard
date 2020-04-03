package com.jaeheonshim.chessboard;

import com.jaeheonshim.chessboard.piece.*;

import java.util.Arrays;

/**
 * Represents the chessboard and methods for manipulating entities of the board
 *
 * @author jaeheonshim
 */
public class Board {
	 private Spot[][] board = new Spot[8][8];

	 public Board () {
		  resetBoard();
	 }

	 /**
	  * Resets the board to the state of a classic chess game
	  */
	 public void resetBoard () {
		  Spot[][] blankBoard = new Spot[8][8];
		  for (int i = 0; i < blankBoard.length; i++) {
				for (int j = 0; j < blankBoard.length; j++) {
					 blankBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  setBoard(blankBoard);

		  setBoard(true);
		  setBoard(false);

		  for (int i = 2; i < 6; i++) {
				for (int j = 0; j < 8; j++) {
					 board[i][j] = new Spot(j, i, null);
				}
		  }
	 }

	 /**
	  * Helper method for setting the board to a classic chess state
	  *
	  * @param white Whether you are setting white pieces
	  */
	 private void setBoard (boolean white) {
		  int pawnRow = white ? 1 : 6;
		  int pieceRow = white ? 0 : 7;

		  for (int i = 0; i < 8; i++) {
				board[pawnRow][i] = new Spot(i, pawnRow, new Pawn(white));
		  }

		  board[pieceRow][0] = new Spot(0, pieceRow, new Rook(white));
		  board[pieceRow][7] = new Spot(7, pieceRow, new Rook(white));

		  board[pieceRow][1] = new Spot(1, pieceRow, new Knight(white));
		  board[pieceRow][6] = new Spot(6, pieceRow, new Knight(white));

		  board[pieceRow][2] = new Spot(2, pieceRow, new Bishop(white));
		  board[pieceRow][5] = new Spot(5, pieceRow, new Bishop(white));

		  board[pieceRow][3] = new Spot(3, pieceRow, new Queen(white));
		  board[pieceRow][4] = new Spot(4, pieceRow, new King(white));
	 }

	 /**
	  * Returns a Spot on the board based on given coordinates
	  * (0, 0) is defined as the bottom left corner
	  *
	  * @param x x coordinate of spot
	  * @param y y coordinate of spot
	  * @return Spot at x and y coordinate of board.
	  */
	 public Spot getSpot (int x, int y) {
		  try {
				return board[y][x];
		  } catch (ArrayIndexOutOfBoundsException e) {
				throw new RuntimeException("Spot out of bounds", e);
		  }
	 }

	 /**
	  * Returns a spot given a Square enumeration type
	  *
	  * @param square Square to return
	  * @return Spot retrieved from board using square parameter
	  */
	 public Spot getSpot (Square square) {
		  return board[square.getY()][square.getX()];
	 }

	 /**
	  * Returns the king from the board with the specified color. Returns null if no king is available.
	  *
	  * @param white Whether to return the white king
	  * @return Piece King from the Board.
	  */
	 public King getKing (boolean white) {
		  for (Spot[] spots : board) {
				for (Spot spot : spots) {
					 if (spot.getPiece() instanceof King && spot.getPiece().isWhite() == white) {
						  return (King)spot.getPiece();
					 }
				}
		  }

		  return null;
	 }

	 /**
	  * Sets the current state of the board from a two-dimensional array of type Spot.
	  *
	  * @param board Two-dimensional array of spot with pieces.
	  */
	 public void setBoard (Spot[][] board) {
		  this.board = board;
	 }

	 /**
	  * Performs a piece move on the current board, if that move is allowed.
	  *
	  * @param begin Spot in board of the piece to move
	  * @param end   Spot to attempt moving piece to
	  * @return returns true if move is valid and move has been executed, false if move is invalid.
	  */
	 public boolean move (Spot begin, Spot end) {
		  if (begin.getPiece() instanceof King && ((King)begin.getPiece()).canCastle(this) && (end.getX() == 2 ^ end.getX() == 6)) {
				if (begin.getPiece().canMove(this, begin, end)) {
					 if (end.getX() == 2) {
						  end.setPiece(begin.getPiece());
						  begin.setPiece(null);
						  Piece rook = this.getSpot(0, end.getY()).getPiece();
						  this.getSpot(0, end.getY()).setPiece(null);
						  this.getSpot(end.getX() + 1, end.getY()).setPiece(rook);
						  return true;
					 } else if (end.getX() == 6) {
						  end.setPiece(begin.getPiece());
						  begin.getPiece().setMoved();
						  begin.setPiece(null);
						  Piece rook = this.getSpot(7, end.getY()).getPiece();
						  this.getSpot(7, end.getY()).setPiece(null);
						  this.getSpot(end.getX() - 1, end.getY()).setPiece(rook);
						  rook.setMoved();
						  return true;
					 } else {
						  return false;
					 }
				} else {
					 return false;
				}
		  } else if (begin.getPiece() != null && begin.getPiece().canMove(this, begin, end)) {
				if (end.getPiece() != null) {
					 end.getPiece().setKilled(true);
					 end.setPiece(null);
				}
				end.setPiece(begin.getPiece());
				begin.getPiece().setMoved();
				begin.setPiece(null);

				if(end.getPiece() instanceof Pawn && end.getPiece().isWhite() ? end.getY() == 7 : end.getY() == 0) {
					end.setPiece(new Queen(end.getPiece().isWhite()));
				}
				return true;
		  } else {
				return false;
		  }
	 }

	 /**
	  * Performs a piece move on the current board, if that move is allowed.
	  *
	  * @param begin Square piece to move is located
	  * @param end   Square to move piece to
	  * @return returns true if move is valid and move has been executed, false if move is invalid.
	  */
	 public boolean move (Square begin, Square end) {
		  if (this.getSpot(begin.getX(), begin.getY()).getPiece() != null) {
				return this.move(this.getSpot(begin.getX(), begin.getY()), this.getSpot(end.getX(), end.getY()));
		  } else {
				return false;
		  }
	 }

	 /**
	  * Check if a piece can be moved, without actually making the move.
	  *
	  * @param begin Spot containing piece to move
	  * @param end   Spot containing destination
	  * @return returns true if move is valid, false if move is invalid.
	  */
	 public boolean canMove (Spot begin, Spot end) {
		  if (begin.getPiece() != null) {
				return begin.getPiece().canMove(this, begin, end);
		  } else {
				return false;
		  }
	 }

	 /**
	  * Check if a piece can be moved, without actually making the move.
	  *
	  * @param begin Square containing piece to move
	  * @param end   Square containing destination
	  * @return returns true if move is valid, false if move is invalid.
	  */
	 public boolean canMove (Square begin, Square end) {
		  if (this.getSpot(begin.getX(), begin.getY()).getPiece() != null) {
				return this.canMove(this.getSpot(begin.getX(), begin.getY()), this.getSpot(end.getX(), end.getY()));
		  } else {
				return false;
		  }
	 }

	 /**
	  * Checks if either king on the board is in checkmate
	  *
	  * @return true if a king is in checkmate, false if king is not in checkmate.
	  */
	 public boolean kingInCheckmate () {
		  return getKing(true).inCheckmate(this) ^ getKing(false).inCheckmate(this);
	 }

	 /**
	  * Get a visual string representation of the board in its current state
	  *
	  * @return String visualizing the board. White pieces are capitalized and black pieces are lowercase.
	  */
	 @Override public String toString () {
		  StringBuilder stringBuilder = new StringBuilder(64);
		  for (int i = board.length - 1; i >= 0; i--) {
				for (int j = 0; j < board[i].length; j++) {
					 if (board[i][j].getPiece() != null) {
						  stringBuilder.append(board[i][j].getPiece().toString());
					 } else {
						  stringBuilder.append(" ");
					 }
				}
				stringBuilder.append("\n");
		  }

		  return stringBuilder.toString();
	 }

	 /**
	  * Removes all pieces from the board
	  */
	 public void clear () {
		  Arrays.asList(board).forEach(m -> Arrays.asList(m).forEach(e -> e.setPiece(null)));
	 }

	 /**
	  * Returns the board as a two-dimensional array of Spots
	  *
	  * @return Two dimensional array of type Spot
	  */
	 public Spot[][] getBoard () {
		  return board;
	 }
}
