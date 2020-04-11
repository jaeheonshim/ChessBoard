package com.jaeheonshim.chessboard.exceptions;

public class InvalidFenException extends Exception {
    public InvalidFenException(String cause) {
        super("Invalid FEN: " + cause);
    }
}
