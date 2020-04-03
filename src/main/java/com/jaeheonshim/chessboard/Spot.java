package com.jaeheonshim.chessboard;

import com.jaeheonshim.chessboard.piece.Piece;

import java.util.Objects;

/**
 * Represents a spot within a Board.
 *
 * @author jaeheonshim
 */
public class Spot {
    private Piece piece;
    private int x, y;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    /**
     * Returns the piece stored in this spot
     *
     * @return Piece object stored in spot
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the piece stored in the spot
     *
     * @param piece piece to set field piece to
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Returns the x coordinate of the spot where (0, 0) is the lower left corner of the board
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the spot where (0, 0) is the lower left corner of the board
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the square enumeration associated with the spot on the board
     *
     * @return Square corresponding to position of this spot on the board
     */
    public Square getSquare() {
        for (Square square : Square.values()) {
            if (square.getX() == this.x && square.getY() == this.y) {
                return square;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return x == spot.x &&
                y == spot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        if (getPiece() != null) {
            return getPiece().toString();
        } else {
            return null;
        }
    }
}
