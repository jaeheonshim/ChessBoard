package com.jaeheonshim.chessboard;

import java.util.Objects;

public class Move {
    private Spot start;
    private Spot end;

    public Move(Spot start, Spot end) {
        this.start = start;
        this.end = end;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public boolean isWhiteMove() {
        return start.getPiece().isWhite();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return start.equals(move.start) &&
                end.equals(move.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
