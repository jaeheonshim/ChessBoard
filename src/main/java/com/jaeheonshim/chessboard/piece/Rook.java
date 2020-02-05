package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.Piece;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if(end.getPiece() != null) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                // can't kill piece of same color
                return false;
            }
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

        if(start.getX() == end.getX()) {
            //moving vertically
            if(start.getY() > end.getY()) {
                for(int i = start.getY() - 1; i > end.getY(); i--) {
                    if(board.getSpot(start.getX(), i).getPiece() != null) {
                        //if there are pieces in the way
                        return false;
                    }
                }
            } else {
                for(int i = start.getY() + 1; i < end.getY(); i++) {
                    if(board.getSpot(start.getX(), i).getPiece() != null) {
                        //if there are pieces in the way
                        return false;
                    }
                }
            }
        } else if(start.getY() == end.getY()) {
            //moving horizontally
            if(start.getX() > end.getX()) {
                for(int i = start.getX() - 1; i < end.getX(); i--) {
                    if(board.getSpot(i, start.getY()) != null) {
                        //if there are pieces in the way
                        return false;
                    }
                }
            } else {
                for(int i = start.getX() + 1; i < end.getX(); i++) {
                    if(board.getSpot(i, start.getY()).getPiece() != null) {
                        //if there are pieces in the way
                        return false;
                    }
                }
            }
        } else if(start.getY() != end.getY() || start.getX() != end.getX()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        if(isWhite()) {
            return "R";
        } else {
            return "r";
        }
    }
}
