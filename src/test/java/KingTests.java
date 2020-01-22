import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.King;
import com.jaeheonshim.chessboard.piece.Pawn;
import org.junit.Assert;
import org.junit.Test;

public class KingTests {
    @Test
    public void kingShouldNotMoveIntoDanger() {
        Board testBoard = new Board();
        Spot[][] newBoard = new Spot[8][8];
        for(int i = 0; i < newBoard.length; i++) {
            for(int j = 0; j < newBoard.length; j++) {
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
}
