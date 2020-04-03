package com.jaeheonshim.chessboard.piece;

import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    public boolean canCastleQueenside(Board board) {
        if (canCastle(board)) {
            if(isWhite()) {
                return !board.getSpot(2, 0).getPiece().isMoved();
            } else {
                return !board.getSpot(2, 7).getPiece().isMoved();
            }
        } else {
            return false;
        }
    }

    public boolean canCastleKingside(Board board) {
        if (canCastle(board)) {
            if(isWhite()) {
                return !board.getSpot(6, 0).getPiece().isMoved();
            } else {
                return !board.getSpot(6, 7).getPiece().isMoved();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (canCastle(board)) {
            if (end.getX() == 2) {
                for (int i = getSpot(board).getX() - 1; i > 0; i--) {
                    if (board.getSpot(i, end.getY()).getPiece() != null) {
                        return false;
                    }
                }

                for (int i = getSpot(board).getX() - 1; i > 0; i--) {
                    Spot tempSpot = this.getSpot(board);

                    Piece tempPiece = board.getSpot(i, end.getY()).getPiece();

                    board.getSpot(i, end.getY()).setPiece(this);
                    tempSpot.setPiece(null);

                    if (this.inCheck(board)) {
                        board.getSpot(i, end.getY()).setPiece(tempPiece);
                        tempSpot.setPiece(this);
                        return false;
                    }

                    board.getSpot(i, end.getY()).setPiece(tempPiece);
                    tempSpot.setPiece(this);
                }

                return true;
            } else if (end.getX() == 6) {
                for (int i = getSpot(board).getX() + 1; i < 7; i++) {
                    if (board.getSpot(i, end.getY()).getPiece() != null) {
                        return false;
                    }
                }

                for (int i = getSpot(board).getX() + 1; i < 7; i++) {
                    Spot tempSpot = this.getSpot(board);

                    Piece tempPiece = board.getSpot(i, end.getY()).getPiece();

                    board.getSpot(i, end.getY()).setPiece(this);
                    tempSpot.setPiece(null);

                    if (this.inCheck(board)) {
                        board.getSpot(i, end.getY()).setPiece(tempPiece);
                        tempSpot.setPiece(this);
                        return false;
                    }

                    board.getSpot(i, end.getY()).setPiece(tempPiece);
                    tempSpot.setPiece(this);
                }

                return true;
            }
        }

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
        } else if (Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()) > 2) {
            return false;
        }

        return true;
    }

    public boolean canCastle(Board board) {
        if (!isMoved() && !inCheck(board)) {
            for (Spot[] spots : board.getBoard()) {
                for (Spot spot : spots) {
                    if (spot.getPiece() instanceof Rook && spot.getPiece().isWhite() == isWhite() && !spot.getPiece().isMoved()) {
                        return true;
                    }
                }
            }
        }

        return false;
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
        if (this.inCheck(board)) {
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
