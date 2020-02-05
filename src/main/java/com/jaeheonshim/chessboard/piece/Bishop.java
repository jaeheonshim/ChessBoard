package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        } else if (checkKingInCheck && board.getKing(isWhite()) != null && board.getKing(isWhite()).inCheck(board)) {
            return false;
        }

        if (Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY())) {
            int xMutator;
            int yMutator;

            if(start.getX() < end.getX()) {
                // Moving to the right
                xMutator = 1;
            } else {
                xMutator = -1;
            }

            if(start.getY() < end.getY()) {
                // if moving up
                yMutator = 1;
            } else {
                yMutator = -1;
            }


            int xIndex = start.getX() + xMutator;
            int yIndex = start.getY() + yMutator;

            while (xIndex != end.getX() && yIndex != end.getY()) {
                if (board.getSpot(xIndex, yIndex).getPiece() != null && xIndex != end.getX() && yIndex != end.getY()) {
                    return false;
                }

                xIndex += xMutator;
                yIndex += yMutator;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (isWhite()) {
            return "B";
        } else {
            return "b";
        }
    }
}
