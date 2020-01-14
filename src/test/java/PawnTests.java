import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Pawn;
import com.jaeheonshim.chessboard.Spot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PawnTests {
    @Test
    public void pawnShouldMoveTwoFirstTime() {
        Board testBoard = new Board();
        Assert.assertTrue("Pawn should move two spots on first move",
                testBoard.getSpot(0, 1).getPiece().canMove(testBoard, testBoard.getSpot(0, 1), testBoard.getSpot(0, 3)));
    }

    @Test
    public void pawnShouldMoveOneSpotForward() {
        Board testBoard = new Board();
        Assert.assertTrue("Pawn should move two spots on first move",
                testBoard.move(testBoard.getSpot(0, 1), testBoard.getSpot(0, 2)));
    }

    @Test
    public void pawnShouldNotMoveTwoAfterFirstMove() {
        Board testBoard = new Board();
        testBoard.move(testBoard.getSpot(0, 1), testBoard.getSpot(0, 2));
        Assert.assertFalse("Pawn should not move two spots after the first move",
                testBoard.move(testBoard.getSpot(0, 2), testBoard.getSpot(0, 4)));
    }

    @Test
    public void pawnShouldKillDiagonally() {
    	Board testBoard = new Board();
    	Spot[][] newBoard = new Spot[8][8];
    	for(int i = 0; i < newBoard.length; i++) {
			Arrays.fill(newBoard[i], null);
		}
		newBoard[1][0] = new Spot(0, 1, new Pawn(true));
		newBoard[0][1] = new Spot(1, 0, new Pawn(false));

		testBoard.setBoard(newBoard);

		Assert.assertTrue("Pawn should kill piece of opposite color diagonally", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 1)));
		Assert.assertFalse(testBoard.getSpot(0, 1).getPiece().isWhite());
    }

    @Test
    public void pawnShouldNotKillForwards() {
		Board testBoard = new Board();
		Spot[][] newBoard = new Spot[8][8];
		for(int i = 0; i < newBoard.length; i++) {
			Arrays.fill(newBoard[i], null);
		}
		newBoard[1][1] = new Spot(1, 1, new Pawn(true));
		newBoard[0][1] = new Spot(1, 0, new Pawn(false));

		testBoard.setBoard(newBoard);

		Assert.assertFalse("Pawn should not kill going forwards", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(1, 1)));
	}
}
