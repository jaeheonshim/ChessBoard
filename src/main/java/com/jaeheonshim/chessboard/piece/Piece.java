package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.Square;

/**
 * Represents a chess piece.
 *
 * @author jaeheonshim
 */
public abstract class Piece {
    private boolean killed = false;
    private boolean moved = false;
    private boolean white;
    protected boolean checkKingInCheck = true;

    public Piece(boolean white) {
        this.white = white;
    }

    /**
     * Returns whether the current piece is white.
     *
     * @return true if the current piece is white, false if it is black
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Returns a boolean based on whether a certain move on the board is possible
     *
     * @param board            Board piece is located within
     * @param start            Starting spot of piece
     * @param end              Spot to move piece to
     * @param checkKingInCheck Whether to check if the king is in check and disallow move.
     * @return true if move is possible, false if move is forbidden
     */
    public boolean canMove(Board board, Spot start, Spot end, boolean checkKingInCheck) {
        this.checkKingInCheck = checkKingInCheck;
        return canMove(board, start, end);
    }

    /**
     * Returns a boolean based on whether a certain move on the board is possible
     *
     * @param board Board piece is located within
     * @param start Starting spot of piece
     * @param end   Spot to move piece to
     * @return true if move is possible, false if move is forbidden
     */
    public abstract boolean canMove(Board board, Spot start, Spot end);

    /**
     * Returns a boolean based on whether a certain move on the board is possible
     *
     * @param board Board piece is located within
     * @param end   Spot to move piece to
     * @return true if move is possible, false if move is forbidden
     */
    public boolean canMove(Board board, Spot end) {
        return canMove(board, getSpot(board), end);
    }

    /**
     * Returns a boolean based on whether a certain move on the board is possible
     *
     * @param board Board piece is located within
     * @param start Square enumeration of starting position of piece
     * @param end   Square enumeration of spot to move piece to
     * @return true if move is possible, false if move is forbidden
     */
    public boolean canMove(Board board, Square start, Square end) {
        return canMove(board, board.getSpot(start), board.getSpot(end));
    }

    /**
     * Sets the piece to have been moved. Necessary for pawns as they can move two spots forward on the first move.
     */
    public void setMoved() {
        moved = true;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    /**
     * Returns whether piece has been previously moved
     *
     * @return true if piece has been moved, false if piece is in default position
     */
    public boolean isMoved() {
        return moved;
    }

    /**
     * Returns the spot of the piece on the board
     *
     * @param board Board piece is located within
     * @return Spot corresponding with position within board
     */
    public Spot getSpot(Board board) {
        for (Spot[] spots : board.getBoardSpots()) {
            for (Spot spot : spots) {
                if (spot.getPiece() == this) {
                    return spot;
                }
            }
        }

        return null;
    }

    /**
     * Returns the string representation of a piece. Should be capitalized for white pieces, lowercase for black pieces.
     *
     * @return
     */
    @Override
    public abstract String toString();
}
