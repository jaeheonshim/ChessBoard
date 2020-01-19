import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.Spot;
import com.jaeheonshim.chessboard.piece.Bishop;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BishopTests {
	 @Test
	 public void bishopShouldOnlyMoveDiagonally() {
		  Board testBoard = new Board();
		  Spot[][] newBoard = new Spot[8][8];
		  for(int i = 0; i < newBoard.length; i++) {
				for(int j = 0; j < newBoard.length; j++) {
					 newBoard[i][j] = new Spot(j, i, null);
				}
		  }

		  newBoard[4][4] = new Spot(4, 4, new Bishop(true));
		  testBoard.setBoard(newBoard);

		  Assert.assertFalse("Bishop should only move diagonally", testBoard.move(testBoard.getSpot(4, 4), testBoard.getSpot(4, 6)));
	 }
}
