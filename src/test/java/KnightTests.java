import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.King;
import com.jaeheonshim.chessboard.piece.Knight;
import com.jaeheonshim.chessboard.piece.Rook;
import org.junit.Assert;
import org.junit.Test;

public class KnightTests {
    @Test
    public void knightShouldJumpOverPieces() {
        Board testBoard = new Board();
        Assert.assertTrue("Knight should jump over pieces", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
    }

    @Test
    public void knightShouldMove() {
        Board testBoard = new Board();
        Assert.assertTrue("Knight should move two forward and one to right",
                testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
        testBoard.resetBoard();
        Assert.assertTrue("Knight should move two forward and one to left",
                testBoard.moveIgnoreTurn(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));
        testBoard.resetBoard();
        Assert.assertFalse("Knight should not move two forward only",
                testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(1, 2)));
    }

    @Test
    public void knightShouldNotLandOnSameColor() {
        Board testBoard = new Board();

        testBoard.getBoardSpots()[2][0] = new Spot(0, 2, new Knight(true));

        Assert.assertFalse("Knight should not land on a piece of the same color", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));
    }

    @Test
    public void knightShouldKillOnLanding() {
        Board testBoard = new Board();

        testBoard.getBoardSpots()[2][0] = new Spot(0, 2, new Knight(false));

        Assert.assertTrue("Knight should land on a piece of the opposite color", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));

        Assert.assertTrue("Knight should kill piece on landing", testBoard.getSpot(0, 2).getPiece().isWhite());
    }

    @Test
    public void knightShouldNotMoveWhenKingInCheck() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0].setPiece(new King(true));
        newBoard[5][0].setPiece(new Rook(false));
        newBoard[3][3].setPiece(new Knight(true));
        testBoard.setBoard(newBoard);

        Assert.assertTrue(testBoard.getKing(true).inCheck(testBoard));
        Assert.assertFalse("Knight should not move when king is in check", testBoard.getSpot(3, 3).getPiece().canMove(testBoard, testBoard.getSpot(1, 5)));
    }
}
