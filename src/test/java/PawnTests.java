import com.jaeheonshim.chessboard.Board;
import org.junit.Assert;
import org.junit.Test;

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
}
