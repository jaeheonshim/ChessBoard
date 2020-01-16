import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.piece.Rook;
import com.jaeheonshim.chessboard.Spot;
import org.junit.Assert;
import org.junit.Test;

public class RookTests {
    @Test
    public void rookShouldMoveVertically() {
        Board testBoard = new Board();
        testBoard.getSpot(0, 1).setPiece(null);

        Assert.assertTrue("Rook should move vertically", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 5)));
    }

    @Test
    public void rookShouldMoveHorizontally() {
        Board testBoard = new Board();
        testBoard.getSpot(0, 1).setPiece(null);

        testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 4));
        Assert.assertTrue("Rook should move horizontally", testBoard.move(testBoard.getSpot(0, 4), testBoard.getSpot(5, 4)));
    }

    @Test
    public void rookShouldNotMoveDiagonally() {
        Board testBoard = new Board();
        testBoard.getSpot(0, 1).setPiece(null);

        testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 4));
        Assert.assertTrue("Rook should not move diagonally", testBoard.move(testBoard.getSpot(0, 4), testBoard.getSpot(5, 6)));
    }

    @Test
    public void rookShouldNotJumpOverPieces() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for(int i = 0; i < newBoard.length; i++) {
            for(int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0] = new Spot(0, 0, new Rook(true));
        newBoard[3][0] = new Spot(0, 3, new Rook(false));
        newBoard[4][0] = new Spot(0, 4, new Rook(true));

        testBoard.setBoard(newBoard);

        Assert.assertFalse("A rook should not jump over pieces", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 5)));
    }

    @Test
    public void rookShouldKillOnLanding() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for(int i = 0; i < newBoard.length; i++) {
            for(int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = new Spot(j, i, null);
            }
        }

        newBoard[0][0] = new Spot(0, 0, new Rook(true));
        newBoard[3][0] = new Spot(0, 3, new Rook(false));

        testBoard.setBoard(newBoard);

        Assert.assertTrue("Rook should land on pieces of opposite color", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 3)));
        Assert.assertTrue("Rook should kill pieces of opposite color", testBoard.getSpot(0, 3).getPiece().isWhite());
    }
}
