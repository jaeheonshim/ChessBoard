import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.King;
import com.jaeheonshim.chessboard.piece.Pawn;
import com.jaeheonshim.chessboard.piece.Rook;
import org.junit.Assert;
import org.junit.Test;

public class KingTests {
    @Test
    public void kingShouldNotMoveIntoDanger() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0].setPiece(new King(true));
        newBoard[2][1].setPiece(new Pawn(false));
        testBoard.setBoard(newBoard);

        Assert.assertFalse("King should not move into dangerous position", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot((0), 1)));
    }

    @Test
    public void kingShouldNotMoveOntoPieceOfSameColor() {
        Board testBoard = new Board();

        Assert.assertFalse("King should not move onto a piece of the same color", testBoard.move(testBoard.getSpot(4, 0), testBoard.getSpot(4, 1)));
    }

    @Test
    public void kingShouldNotMoveMoreThan1SpotForwards() {
        Board testBoard = initBoard();

        Assert.assertFalse("King should not move more than one spot forwards", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 2)));
    }

    @Test
    public void kingShouldNotMoveMoreThan1SpotSideways() {
        Board testBoard = initBoard();

        Assert.assertFalse("King should not move more than one spot sideways", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(2, 0)));
    }

    @Test
    public void kingShouldNotMoveMoreThan1SpotDiagonally() {
        Board testBoard = initBoard();

        Assert.assertFalse("King should not move more than one spot diagonally", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(2, 2)));
    }

    @Test
    public void kingShouldMove1SpotForwards() {
        Board testBoard = initBoard();

        Assert.assertTrue("King should move one spot forwards", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 1)));
    }

    @Test
    public void kingShouldMove1SpotSideways() {
        Board testBoard = initBoard();

        Assert.assertTrue("King should move one spot sideways", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(1, 0)));
    }

    @Test
    public void kingShouldMoveOneSpotDiagonally() {
        Board testBoard = initBoard();

        Assert.assertTrue("King should move one spot diagonally", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(1, 1)));
    }

    @Test
    public void kingShouldBeInCheckIfUnderThreat() {
        Board testBoard = initBoard();

        testBoard.getBoard()[1][0].setPiece(new Rook(false));
        Assert.assertTrue(testBoard.getKing(true).inCheck(testBoard));
    }

    @Test
    public void kingShouldBeInCheckmate() {
        Board testBoard = initBoard();

        testBoard.getBoard()[0][0].setPiece(new Rook(false));
        testBoard.getBoard()[1][0].setPiece(new Rook(false));
        testBoard.getBoard()[0][5].setPiece(new King(true));

        Assert.assertTrue("King should be in checkmate when in check and cannot move anywhere", testBoard.getKing(true).inCheckmate(testBoard));
    }

    @Test
    public void kingShouldBeAbleToCastleWhenNoPiecesHaveMoved() {
        Board testBoard = new Board();

        Assert.assertTrue("King should be able to castle when no pieces have moved", testBoard.getKing(true).canCastle(testBoard));
    }

    @Test
    public void kingShouldNotCastleWhenThereArePiecesBetweenKingAndRook() {
        Board testBoard = new Board();

        Assert.assertFalse("The king should not castle when there are pieces between it and the rook", testBoard.getKing(true).canMove(testBoard, testBoard.getSpot(6, 0)));
    }

    @Test
    public void kingShouldNotCastleThroughCheck() {
        Board testBoard = initBoard();

        testBoard.getSpot(4, 0).setPiece(new King(true));
        testBoard.getSpot(0, 0).setPiece(new Rook(true));
        testBoard.getSpot(1, 3).setPiece(new Rook(false));

        Assert.assertFalse("King should not castle through check", testBoard.getKing(true).canMove(testBoard, testBoard.getSpot(2, 0)));
    }

    @Test
    public void whiteKingShouldCastleLeft() {
        Board testBoard = new Board();

        testBoard.getSpot(1, 0).setPiece(null);
        testBoard.getSpot(2, 0).setPiece(null);
        testBoard.getSpot(3, 0).setPiece(null);

        Assert.assertTrue("King should castle left if all conditions are met", testBoard.move(testBoard.getKing(true).getSpot(testBoard), testBoard.getSpot(2, 0)));


        System.out.println(testBoard);
    }

    @Test
    public void whiteKingShouldCastleRight() {
        Board testBoard = new Board();

        testBoard.getSpot(5, 0).setPiece(null);
        testBoard.getSpot(6, 0).setPiece(null);

        Assert.assertTrue("King should castle right if all conditions are met", testBoard.move(testBoard.getKing(true).getSpot(testBoard), testBoard.getSpot(6, 0)));

        System.out.println(testBoard);
    }

    @Test
    public void blackKingShouldCastleLeft() {
        Board testBoard = new Board();

        testBoard.getSpot(1, 7).setPiece(null);
        testBoard.getSpot(2, 7).setPiece(null);
        testBoard.getSpot(3, 7).setPiece(null);

        Assert.assertTrue("King should castle left if all conditions are met", testBoard.move(testBoard.getKing(false).getSpot(testBoard), testBoard.getSpot(2, 7)));


        System.out.println(testBoard);
    }

    @Test
    public void blackKingShouldCastleRight() {
        Board testBoard = new Board();

        testBoard.getSpot(5, 7).setPiece(null);
        testBoard.getSpot(6, 7).setPiece(null);

        Assert.assertTrue("King should castle right if all conditions are met", testBoard.move(testBoard.getKing(false).getSpot(testBoard), testBoard.getSpot(6, 7)));

        System.out.println(testBoard);
    }

    private Board initBoard() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0].setPiece(new King(true));
        testBoard.setBoard(newBoard);

        return testBoard;
    }
}
