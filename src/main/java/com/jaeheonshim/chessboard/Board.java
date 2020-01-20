package com.jaeheonshim.chessboard;

import com.jaeheonshim.chessboard.piece.Bishop;
import com.jaeheonshim.chessboard.piece.Knight;
import com.jaeheonshim.chessboard.piece.Pawn;
import com.jaeheonshim.chessboard.piece.Rook;

public class Board {
    private Spot[][] board = new Spot[8][8];

    public Board() {
        resetBoard();
    }

    public void resetBoard() {
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

    private void setBoard(boolean white) {
        int pawnRow = white ? 1 : 6;
        int pieceRow = white ? 0: 7;

        for(int i = 0; i < 8; i++) {
            board[pawnRow][i] = new Spot(i, pawnRow, new Pawn(white));
        }

        board[pieceRow][0] = new Spot(0,  pieceRow, new Rook(white));
        board[pieceRow][7] = new Spot(7,  pieceRow, new Rook(white));

        board[pieceRow][1] = new Spot(1, pieceRow, new Knight(white));
        board[pieceRow][6] = new Spot(6, pieceRow, new Knight(white));

        board[pieceRow][2] = new Spot(1, pieceRow, new Bishop(white));
        board[pieceRow][5] = new Spot(6, pieceRow, new Bishop(white));
    }

    public Spot getSpot(int x, int y) {
        try {
             return board[y][x];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Spot out of bounds", e);
        }
    }

    public void setBoard(Spot[][] board) {
        this.board = board;
    }

    public boolean move(Spot begin, Spot end) {
        if (begin.getPiece().canMove(this, begin, end)) {
            if (end.getPiece() != null) {
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
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece() != null) {
                    stringBuilder.append(board[i][j].getPiece() .toString());
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public Spot[][] getBoard () {
        return board;
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.toString());
    }
}
