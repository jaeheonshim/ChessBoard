import com.jaeheonshim.chessboard.Board;
import org.junit.Assert;
import org.junit.Test;

public class KnightTests {
	 @Test public void knightShouldJumpOverPieces () {
		  Board testBoard = new Board();
		  Assert.assertTrue("Knight should jump over pieces", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
	 }

	 @Test public void knightShouldMove () {
		  Board testBoard = new Board();
		  Assert.assertTrue("Knight should move two forward and one to right", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
		  testBoard.resetBoard();
		  Assert.assertTrue("Knight should move two forward and one to left", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));
		  testBoard.resetBoard();
		  Assert.assertFalse("Knight should not move two forward only", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(1, 2)));
	 }
}
