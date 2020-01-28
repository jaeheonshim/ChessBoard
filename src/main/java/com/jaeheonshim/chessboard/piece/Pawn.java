package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(end.getX() > 8 || end.getX() < 0 || end.getY() > 8 || end.getY() < 0) {
            return false;
        } else if (checkKingInCheck && board.getKing(isWhite()) != null && board.getKing(isWhite()).inCheck(board)) {
            return false;
        }

        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            //Can't kill or move over piece of same color
            return false;
        }
        if (end.getPiece() != null && start.getX() == end.getX()) {
            // can't kill piece moving forwards
            return false;
        }

        if (!isMoved() && start.getX() == end.getX() && Math.abs(end.getY() - start.getY()) <= 2) {
            if(end.getY() > start.getY() && isWhite()) {
                return true;
            } else
                return end.getY() < start.getY() && !isWhite();
        } else if (start.getX() == end.getX() && Math.abs(start.getY() - end.getY()) < 2) {
            if(end.getY() > start.getY() && isWhite()) {
                return true;
            } else
                return end.getY() < start.getY() && !isWhite();
        } else if (end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) {
            return isWhite() ? end.getY() == start.getY() + 1 : end.getY() == start.getY() - 1;
        }

        return false;
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return "P";
        } else {
            return "p";
        }
    }
}
