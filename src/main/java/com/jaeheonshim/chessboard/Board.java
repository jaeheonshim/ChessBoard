package com.jaeheonshim.chessboard;

import java.util.Arrays;

public class Board {
    private Spot[][] board = new Spot[8][8];

    public Board() {
        resetBoard();
    }

    public void resetBoard() {
        board[1][0] = new Spot(0, 1, new Pawn(true));
        board[1][1] = new Spot(1, 1, new Pawn(true));
        board[1][2] = new Spot(2, 1, new Pawn(true));
        board[1][3] = new Spot(3, 1, new Pawn(true));
        board[1][4] = new Spot(4, 1, new Pawn(true));
        board[1][5] = new Spot(5, 1, new Pawn(true));
        board[1][6] = new Spot(6, 1, new Pawn(true));
        board[1][7] = new Spot(7, 1, new Pawn(true));

        board[6][0] = new Spot(0, 6, new Pawn(false));
        board[6][1] = new Spot(1, 6, new Pawn(false));
        board[6][2] = new Spot(2, 6, new Pawn(false));
        board[6][3] = new Spot(3, 6, new Pawn(false));
        board[6][4] = new Spot(4, 6, new Pawn(false));
        board[6][5] = new Spot(5, 6, new Pawn(false));
        board[6][6] = new Spot(6, 6, new Pawn(false));
        board[6][7] = new Spot(7, 6, new Pawn(false));

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Spot(j, i, null);
            }
        }
    }

    public Spot getSpot(int x, int y) {
        return board[y][x];
    }

    public void setBoard(Spot[][] board) {
        this.board = board;
    }

    public boolean move(Spot begin, Spot end) {
        if (begin.getPiece().canMove(this, begin, end)) {
		  	if(end.getPiece() != null) {
		  		end.getPiece().setKilled(true);
		  		end.setPiece(null);
			}
            end.setPiece(begin.getPiece());
            begin.getPiece().setMoved();
            begin.setPiece(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        for(Spot[] row : board) {
            for(Spot spot : row) {
                if(spot != null) {
                    stringBuilder.append(spot.toString());
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.toString());
    }
}
