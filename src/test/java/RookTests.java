import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Rook;
import com.jaeheonshim.chessboard.Spot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RookTests {
    @Test
    public void rookShouldMoveVertically() {
        Board testBoard = new Board();
        testBoard.getSpot(0, 1).setPiece(null);

        Assert.assertTrue("Rook should move", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 5)));
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
}
