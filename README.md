# ChessBoard
A Java library for working with a chess game.   
[
![CodeFactor](https://camo.githubusercontent.com/500763765d885dfcbcf9ccd196bd5a19591e4643/68747470733a2f2f7777772e636f6465666163746f722e696f2f7265706f7369746f72792f6769746875622f6a616568656f6e7368696d2f6368657373626f6172642f62616467653f733d37396231393561616436383864653439636364616334366362396262376536393837316538656234)](https://www.codefactor.io/repository/github/jaeheonshim/chessboard)

ChessBoard is a Java library for determining whether a chess move is valid given a starting location, an ending location, and the state of a chess board. This library could be used to create a chess game, create a chess AI, etc. 

Note: ChessBoard does not keep track of the current game, such as whose turn it is or what move has been made. It is designed so that you can build upon the basic functionality it gives you, allowing you to create a customized application.

## Installing
The ChessBoard dependency can be added from the maven repository. 

    <dependency>
        <groupId>com.jaeheonshim</groupId>
        <artifactId>chessboard</artifactId>
        <version>0.1.0</version>
    </dependency>

## Testing
The unit test suite is stored under /src/test/java. Tests can be run with the following command:  

    mvn test

## API Documentation
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

### Check if a move on the board is valid
    Board board = new Board();
    System.out.println(board.canMove(Square.E2, Square.E4));
Result:

    true

### Execute a move on the board if it is valid
    Board board = new Board();
    System.out.println(board.move(Square.E2, Square.E4));
    System.out.println(board.toString());
Result:

    true
    rnbqkbnr
    pppppppp
        
        
        P   
        
    PPPP PPP
    RNBQKBNR
## Developmental Goals
The ChessBoard library at this current time is by no means complete. It has yet to implement features such as fen notation parsing and output, and en passant. 
Here is a non-exhaustive list of tasks that must be completed before production release:
- Implement En Passant movement
- Implement FEN notation parsing and input
- Return all moves possible from board
- Check for stalemate
- Check for draws
- Get number of points for each side
- Event listeners

Phew! There is a lot of work to be done.


## Contributing
We would love any and all contributions from the community improving our code base. Your help allows `ChessBoard` to become even better.   
 
To contribute, fork this repository and make all necessary changes on that fork. A pull request can be made to merge your changes with this repository.

Again, thank you for your consideration on becoming a developer for `ChessBoard`.

## Support
If you would like to say **thank you** and support the development of `ChessBoard`,    
- Add a GitHub star to this project
- Tweet about this project on your Twitter
- Write a review or tutorial on your personal blog.

Thank you for all of your support on the active development of `ChessBoard`.

## Licensing
`ChessBoard` is published under the MIT License. More information about this license can be found in the LICENSE file.
 
In a nutshell, 

**You are permitted to:**
- Use ChessBoard for commercial purposes
- Modify ChessBoard
- Distribute and sublicense ChessBoard
- Use ChessBoard for private (non open-source) projects

**Under the assumption that:**  
The copyright notice is included in all copies or substantial portions of ChessBoard.

## Versioning
The ChessBoard library uses [semantic versioning](https://semver.org/) to define version numbers.
