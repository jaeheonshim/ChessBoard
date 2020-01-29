package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.Square;

public abstract class Piece {
    private boolean killed = false;
    private boolean moved = false;
    private boolean white;
    protected boolean checkKingInCheck = true;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean canMove(Board board, Spot start, Spot end, boolean checkKingInCheck) {
        this.checkKingInCheck = checkKingInCheck;
        return canMove(board, start, end);
    }

    public abstract boolean canMove(Board board, Spot start, Spot end);

    public boolean canMove(Board board, Spot end) {
        return canMove(board, getSpot(board), end);
    }

    public boolean canMove(Board board, Square start, Square end) {
        return canMove(board, board.getSpot(start), board.getSpot(end));
    }

    public void setMoved() {
        moved = true;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isMoved() {
        return moved;
    }

    public Spot getSpot(Board board) {
        for (Spot[] spots : board.getBoard()) {
            for (Spot spot : spots) {
                if (spot.getPiece() == this) {
                    return spot;
                }
            }
        }

        return null;
    }

    @Override
    public abstract String toString();
}
