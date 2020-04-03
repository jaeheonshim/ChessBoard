import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Move;
import com.jaeheonshim.chessboard.Square;
import org.junit.Assert;
import org.junit.Test;

public class BoardTests {
    @Test
    public void boardShouldRecordMoves() {
        Board testBoard = new Board();
        testBoard.move(Square.A2, Square.A4);

        Move correctMove = new Move(Square.A2.getSpot(testBoard), Square.A4.getSpot(testBoard));

        Assert.assertEquals(testBoard.getMoves().get(0), correctMove);
    }

    @Test
    public void boardShouldReturnCorrectTurn() {
        Board testBoard = new Board();
        Assert.assertTrue(testBoard.isWhiteTurn());

        testBoard.move(Square.A2, Square.A4);
        Assert.assertFalse(testBoard.isWhiteTurn());

        testBoard.move(Square.A7, Square.A6);
        Assert.assertTrue(testBoard.isWhiteTurn());
    }
}
