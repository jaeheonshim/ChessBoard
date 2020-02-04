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
        Spot tempSpot = this.getSpot(board);
        end.setPiece(this);
        this.getSpot(board).setPiece(null);
        for (Spot[] spots : board.getBoard()) {
            for (Spot spot : spots) {
                if (spot.getPiece() != null && !(spot.getPiece() instanceof King) && spot.getPiece().isWhite() != isWhite() && spot.getPiece().canMove(board, spot, end)) {
                    end.setPiece(endTemp);
                    tempSpot.setPiece(this);
                    return false; //if any piece on the board can kill the king after it has made its move, you will be unable to make the move.
                }
            }
        }
        end.setPiece(endTemp);
        tempSpot.setPiece(this);

        if (start.getX() == end.getX()) {
            return Math.abs(start.getY() - end.getY()) <= 1;
        } else if (start.getY() == end.getY()) {
            return Math.abs(start.getX() - end.getX()) <= 1;
        } else if(Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()) > 2) {
            return false;
        }

        return true;
    }

    public boolean inCheck(Board board) {
        for (Spot[] spots : board.getBoard()) {
            for (Spot spot : spots) {
                if (spot.getPiece() != null && !(spot.getPiece() instanceof King) && spot.getPiece() != this && spot.getPiece().isWhite() != isWhite() && spot.getPiece().canMove(board, spot, this.getSpot(board), false)) {
                    return true; //if any piece on the board can kill the king after it has made its move, you will be unable to make the move.
                }
            }
        }

        return false;
    }

    public boolean inCheckmate(Board board) {
        if(this.inCheck(board)) {
            for (Spot[] spots : board.getBoard()) {
                for (Spot spot : spots) {
                    if (this.canMove(board, spot)) {
                        Piece tempPiece = spot.getPiece();
                        Spot tempSpot = getSpot(board);

                        spot.setPiece(this);
                        getSpot(board).setPiece(null);
                        if (!this.inCheck(board)) {
                            spot.setPiece(tempPiece);
                            tempSpot.setPiece(this);
                            return false;
                        }
                        spot.setPiece(tempPiece);
                        tempSpot.setPiece(this);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
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
