import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.Bishop;
import com.jaeheonshim.chessboard.piece.King;
import com.jaeheonshim.chessboard.piece.Rook;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BishopTests {
    @Test
    public void bishopShouldOnlyMoveDiagonally() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[4][4] = new Spot(4, 4, new Bishop(true));
        testBoard.setBoard(newBoard);

        Assert.assertFalse("Bishop should only move diagonally", testBoard.move(testBoard.getSpot(4, 4), testBoard.getSpot(4, 6)));
    }

    @Test
    public void bishopShouldMoveDiagonally() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[4][3] = new Spot(3, 4, new Bishop(true));
        testBoard.setBoard(newBoard);

        Assert.assertTrue("Bishop should move diagonally", testBoard.move(testBoard.getSpot(3, 4), testBoard.getSpot(5, 6)));
    }

    @Test
    public void bishopShouldNotJumpPieces() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[4][4] = new Spot(4, 4, new Bishop(true));
        newBoard[5][5] = new Spot(5, 5, new Bishop(false));
        testBoard.setBoard(newBoard);

        Assert.assertFalse("Bishop should not jump pieces", testBoard.move(testBoard.getSpot(4, 4), testBoard.getSpot(6, 6)));
    }

    @Test
    public void bishopShouldKillOnLanding() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[4][4] = new Spot(4, 4, new Bishop(true));
        newBoard[6][6] = new Spot(6, 6, new Bishop(false));
        testBoard.setBoard(newBoard);

        testBoard.move(testBoard.getSpot(4, 4), testBoard.getSpot(6, 6));

        Assert.assertTrue("Bishop should kill piece it lands on", testBoard.getSpot(6, 6).getPiece().isWhite());
    }

    @Test
    public void bishopShouldNotLandOnPieceOfSameColor() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[4][4] = new Spot(4, 4, new Bishop(true));
        newBoard[6][6] = new Spot(6, 6, new Bishop(true));
        testBoard.setBoard(newBoard);

        Assert.assertFalse("Bishop should not land on a piece of same color", testBoard.move(testBoard.getSpot(4, 4), testBoard.getSpot(6, 6)));
    }

    @Test
    public void bishopShouldNotMoveWhenKingInCheck() {
    	Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0].setPiece(new King(true));
        newBoard[5][0].setPiece(new Rook(false));
        newBoard[3][3].setPiece(new Bishop(true));
        testBoard.setBoard(newBoard);

        Assert.assertTrue(testBoard.getKing(true).inCheck(testBoard));
        Assert.assertFalse("Bishop should not move while king is in check", testBoard.getSpot(3, 3).getPiece().canMove(testBoard, testBoard.getSpot(4, 4)));
	}
}
