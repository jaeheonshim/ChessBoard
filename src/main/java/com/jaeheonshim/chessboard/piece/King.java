package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        Piece endTemp = end.getPiece();
        end.setPiece(this);
        for (Spot[] spots : board.getBoard()) {
            for (Spot spot : spots) {
                if (spot.getPiece() != null && !(spot.getPiece() instanceof King) && spot.getPiece().canMove(board, spot, end)) {
                    return false; //if any piece on the board can kill the king after it has made its move, you will be unable to make the move.
                }
            }
        }
        end.setPiece(endTemp);

        if (start.getX() == end.getX()) {
            if(Math.abs(start.getY() - end.getY()) > 1) {
                return false;
            }
        } else if (start.getY() == end.getY()) {
           if(Math.abs(start.getX() - end.getX()) > 1) {
               return false;
           }
        } else if(Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()) > 2) {
            return false;
        }

        return true;
    }

    public boolean inCheck(Board board) {
        for (Spot[] spots : board.getBoard()) {
            for (Spot spot : spots) {
                if (spot.getPiece() != null && !(spot.getPiece() instanceof King) && spot.getPiece() != this && spot.getPiece().canMove(board, spot, this.getSpot(board))) {
                    return true; //if any piece on the board can kill the king after it has made its move, you will be unable to make the move.
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return "K";
        } else {
            return "k";
        }
    }
}
