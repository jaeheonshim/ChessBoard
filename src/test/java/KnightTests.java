import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.Knight;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class KnightTests {
	 @Test public void knightShouldJumpOverPieces () {
		  Board testBoard = new Board();
		  Assert.assertTrue("Knight should jump over pieces", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
	 }

	 @Test public void knightShouldMove () {
		  Board testBoard = new Board();
		  Assert.assertTrue("Knight should move two forward and one to right",
			  testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(2, 2)));
		  testBoard.resetBoard();
		  Assert.assertTrue("Knight should move two forward and one to left",
			  testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));
		  testBoard.resetBoard();
		  Assert.assertFalse("Knight should not move two forward only",
			  testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(1, 2)));
	 }

	 @Test
	 public void knightShouldNotLandOnSameColor() {
		  Board testBoard = new Board();

		  testBoard.getBoard()[2][0] = new Spot(0, 2, new Knight(true));

		  Assert.assertFalse("Knight should not land on a piece of the same color", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));
	 }

	 @Test
	 public void knightShouldKillOnLanding() {
		  Board testBoard = new Board();

		  testBoard.getBoard()[2][0] = new Spot(0, 2, new Knight(false));

		  Assert.assertTrue("Knight should land on a piece of the opposite color", testBoard.move(testBoard.getSpot(1, 0), testBoard.getSpot(0, 2)));

		  Assert.assertTrue("Knight should kill piece on landing", testBoard.getSpot(0, 2).getPiece().isWhite());
	 }
}
