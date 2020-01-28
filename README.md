
# ChessBoard

A Java library for working with a chess game.   [
![CodeFactor](https://camo.githubusercontent.com/500763765d885dfcbcf9ccd196bd5a19591e4643/68747470733a2f2f7777772e636f6465666163746f722e696f2f7265706f7369746f72792f6769746875622f6a616568656f6e7368696d2f6368657373626f6172642f62616467653f733d37396231393561616436383864653439636364616334366362396262376536393837316538656234)](https://www.codefactor.io/repository/github/jaeheonshim/chessboard)

ChessBoard is a Java library for determining whether a chess move is valid given a starting location, an ending location, and the state of a chess board. This library could be used to create a chess game, create a chess AI, etc. 

Note: ChessBoard does not keep track of the current game, such as whose turn it is or what move has been made. It is designed so that you can build upon the basic functionality it gives you, allowing you to create a customized application.

## Usage
### Create a new chessboard
    Board board = new Board();  
    System.out.println(board.toString());
Result:

    rnbqkbnr
    pppppppp
            
            
            
            
    PPPPPPPP
    RNBQKBNR

### Create a new chessboard with custom locations of pieces

    Board board = new Board();  
    board.clear();  
    board.getSpot(4, 2).setPiece(new Pawn(true));  
    board.getSpot(3, 3).setPiece(new Rook(true));  
    System.out.println(board.toString());
Result:

        R
         P 

