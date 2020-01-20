import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.Queen;
import org.junit.Assert;
import org.junit.Test;

public class QueenTests {
	 @Test public void queenShouldMoveForwards () {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[0][0] = new Spot(0, 0, new Queen(true));
		  testBoard.setBoard(newBoard);

		  Assert.assertTrue("Queen should move forwards", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 7)));
	 }

	 @Test public void queenShouldMoveSideways () {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[0][0] = new Spot(0, 0, new Queen(true));
		  testBoard.setBoard(newBoard);

		  Assert.assertTrue("Queen should move sideways", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(7, 0)));
	 }

	 @Test public void queenShouldMoveDiagonally () {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[0][0] = new Spot(0, 0, new Queen(true));
		  testBoard.setBoard(newBoard);

		  Assert.assertTrue("Queen should move diagonally", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(7, 7)));
	 }

	 @Test public void queenShouldNotJumpOverPieces () {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[0][0] = new Spot(0, 0, new Queen(true));
		  newBoard[5][0] = new Spot(0, 5, new Queen(false));
		  testBoard.setBoard(newBoard);

		  Assert.assertFalse("Queen should not jump over pieces", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 7)));
	 }

	 @Test public void queenShouldNotLandOnPieceOfSameColor () {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for (int i = 0; i < newBoard.length; i++) {
				for (int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[0][0] = new Spot(0, 0, new Queen(true));
		  newBoard[7][0] = new Spot(0, 7, new Queen(true));
		  testBoard.setBoard(newBoard);

		  Assert.assertFalse("Queen should not land on piece of same color", testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 7)));
	 }

	 @Test
	 public void queenShouldKillOnLanding() {
	 	 Board testBoard = new Board();
	 	 Spot[][] newBoard = new Spot[8][8];
	 	 for(int i = 0; i < newBoard.length; i++) {
	 	 	 for(int j = 0; j < newBoard[i].length; j++) {
	 	 	 	 newBoard[i][j] = new Spot(j, i, null);
			 }
		 }

	 	 newBoard[0][0] = new Spot(0, 0, new Queen(true));
	 	 newBoard[5][0] = new Spot(0, 5, new Queen(false));

	 	 testBoard.setBoard(newBoard);
	 	 testBoard.move(testBoard.getSpot(0, 0), testBoard.getSpot(0, 5));
	 	 Assert.assertTrue("Queen should kill pieces on landing", testBoard.getSpot(0, 5).getPiece().isWhite());
	 }
}
