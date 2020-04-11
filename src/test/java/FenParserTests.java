import com.jaeheonshim.chessboard.Board;
import com.jaeheonshim.chessboard.FenParser;
import org.junit.Assert;
import org.junit.Test;

public class FenParserTests {

    @Test
    public void testFenValidity() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        Assert.assertTrue(fenParser.isFenValid());
    }

    @Test
    public void testInvalidNumberOfFields() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidNumberOfRanks() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP b KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidPieceNotation() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RUBQKB1R b KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidNumberOfEmptySpaces() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp0ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp9ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidNumberOfSquares() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPPP/RNBQKB1R w KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppp/8/2p5/4P3/5N2/PPPP1PPPP/RNBQKB1R w KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidTurn() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R m KQkq - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidCastlingAvailability() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkR - 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkk - 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R w Kq- - 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidEnPassantTargetSquare() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq d3d 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq l3 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq c0 1 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq zz 1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidHalfMoveClock() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - r 2");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - -1 2");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testInvalidMoveNumber() {
        FenParser fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 0 r");
        Assert.assertFalse(fenParser.isFenValid());

        fenParser = new FenParser("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 0 0");
        Assert.assertFalse(fenParser.isFenValid());
    }

    @Test
    public void testLoadBoardFromFen() {
        Board testBoard = new Board();
        testBoard.loadFromFen("rn1qk1nr/ppp1bppp/4p3/3p1bB1/3P4/2N2N2/PPP1PPPP/R2QKB1R w KQkq - 2 5");
        System.out.println(testBoard.toString());
        Assert.assertEquals("rn qk nr\nppp bppp\n    p   \n   p bB \n   P    \n  N  N  \nPPP PPPP\nR  QKB R\n",
                testBoard.toString());
    }
}
