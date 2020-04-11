package com.jaeheonshim.chessboard;

import com.jaeheonshim.chessboard.exceptions.InvalidFenException;
import com.jaeheonshim.chessboard.piece.Bishop;
import com.jaeheonshim.chessboard.piece.King;
import com.jaeheonshim.chessboard.piece.Knight;
import com.jaeheonshim.chessboard.piece.Pawn;
import com.jaeheonshim.chessboard.piece.Queen;
import com.jaeheonshim.chessboard.piece.Rook;

public class FenParser {

    private String fen;
    private boolean fenIsValid = false;

    private String[] ranks;
    private String turn;
    private String castlingAvailability;
    private String enPassantTarget;
    private String halfMoveClock;
    private String moveNumber;

    public FenParser(String fen) {
        this.fen = fen;
    }

    public void checkFenValidity() throws InvalidFenException {
        String[] fenParts = fen.split(" ");

        if (fenParts.length != 6) {
            throw new InvalidFenException("Number of fields must be 6!");
        }

        // 'uv' stands for "unvalidated".
        final String uvPosition = fenParts[0];
        final String uvTurn = fenParts[1];
        final String uvCastlingAvailability = fenParts[2];
        final String uvEnPassantTarget = fenParts[3];
        final String uvHalfMoveClock = fenParts[4];
        final String uvMoveNumber = fenParts[5];

        String[] uvRanks = uvPosition.split("/");
        if (uvRanks.length != 8) {
            throw new InvalidFenException("Number of ranks must be 8!");
        }

        checkRanksValidity(uvRanks);
        checkTurnValidity(uvTurn);
        checkAvailableCastlesValidity(uvCastlingAvailability);
        checkEnPassantTargetValidity(uvEnPassantTarget);
        checkHalfMoveClockValidity(uvHalfMoveClock);
        checkMoveNumberValidity(uvMoveNumber);

        this.ranks = uvRanks;
        this.turn = uvTurn;
        this.castlingAvailability = uvCastlingAvailability;
        this.enPassantTarget = uvEnPassantTarget;
        this.halfMoveClock = uvHalfMoveClock;
        this.moveNumber = uvMoveNumber;

        fenIsValid = true;
    }

    private void checkRanksValidity(String[] ranks) throws InvalidFenException {
        final String VALID_PIECE_NOTATIONS = "KkQqRrBbNnPp";
        for (int rankIndex = 0; rankIndex < ranks.length; rankIndex++) {
            int squares = 0;
            for (char character : ranks[rankIndex].toCharArray()) {
                String charToString = (String.valueOf(character));
                if (Character.isDigit(character)) {
                    int digit = Integer.parseInt(charToString);
                    if (digit > 8 || digit < 1) {
                        throw new InvalidFenException("Rank " + (8 - rankIndex) + " contains an invalid number of empty spaces!");
                    }
                    squares += digit;
                } else if (VALID_PIECE_NOTATIONS.contains(charToString)) {
                    squares++;
                } else {
                    throw new InvalidFenException("In rank " + (8 - rankIndex) + ", '" + charToString + "' is an invalid piece notation!");
                }
            }
            if (squares != 8)
                throw new InvalidFenException("Rank " + (8 - rankIndex) + " contains an invalid number of squares!");
        }
    }

    private void checkTurnValidity(String turn) throws InvalidFenException {
        if (!(turn.equals("w") || turn.equals("b"))) {
            throw new InvalidFenException("Turn must be either 'w' or 'b'!");
        }
    }

    private void checkAvailableCastlesValidity(String castlingAvailability) throws InvalidFenException {
        int[] validCharactersCount = {0, 0, 0, 0};
        for (char character : castlingAvailability.toCharArray()) {
            switch (character) {
                case 'K':
                    validCharactersCount[0]++;
                    break;
                case 'k':
                    validCharactersCount[1]++;
                    break;
                case 'Q':
                    validCharactersCount[2]++;
                    break;
                case 'q':
                    validCharactersCount[3]++;
                    break;
                case '-':
                    if (castlingAvailability.length() > 1) {
                        throw new InvalidFenException("'-' must be used only when no castles are available!");
                    }
                    break;
                default:
                    throw new InvalidFenException("'" + character + "' is an invalid castling availability character!");
            }
            for (int integer : validCharactersCount) {
                if (integer > 1) {
                    throw new InvalidFenException("Castling availability characters must not have duplicates!");
                }
            }
        }
    }

    private void checkEnPassantTargetValidity(String enPassantTarget) throws InvalidFenException {
        final String VALID_FILE_CHARACTERS = "abcdefgh";
        if (enPassantTarget.length() != 2 && !enPassantTarget.equals("-")) {
            throw new InvalidFenException("Invalid en passant target square!");
        }

        if (enPassantTarget.equals("-")) {
            return;
        }

        if (!Character.isDigit(enPassantTarget.toCharArray()[1])) {
            throw new InvalidFenException("Invalid en passant target square!");
        }

        int rank = Integer.parseInt(String.valueOf(enPassantTarget.toCharArray()[1]));
        if (!VALID_FILE_CHARACTERS.contains(String.valueOf(enPassantTarget.toCharArray()[0])) || rank < 1 || rank > 8) {
            throw new InvalidFenException("Invalid en passant target square!");
        }
    }

    private void checkHalfMoveClockValidity(String halfMoveClock) throws InvalidFenException {
        try {
            Integer.parseInt(halfMoveClock);
        } catch (NumberFormatException e) {
            throw new InvalidFenException("Halfmove clock must be an integer!");
        }

        if (Integer.parseInt(halfMoveClock) < 0) {
            throw new InvalidFenException("Halfmove clock must start from 0!");
        }
    }

    private void checkMoveNumberValidity(String moveNumber) throws InvalidFenException {
        try {
            Integer.parseInt(moveNumber);
        } catch (NumberFormatException e) {
            throw new InvalidFenException("Move number must be an integer!");
        }

        if (Integer.parseInt(moveNumber) < 1) {
            throw new InvalidFenException("Move number start from 1!");
        }
    }

    public void setupBoard(Board board) {
        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            int fileIndex = 0;
            for (char character : ranks[rankIndex].toCharArray()) {
                switch (character) {
                    case 'K':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new King(true));
                        fileIndex++;
                        break;
                    case 'Q':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Queen(true));
                        fileIndex++;
                        break;
                    case 'R':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Rook(true));
                        fileIndex++;
                        break;
                    case 'B':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Bishop(true));
                        fileIndex++;
                        break;
                    case 'N':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Knight(true));
                        fileIndex++;
                        break;
                    case 'P':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Pawn(true));
                        fileIndex++;
                        break;
                    case 'k':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new King(false));
                        fileIndex++;
                        break;
                    case 'q':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Queen(false));
                        fileIndex++;
                        break;
                    case 'r':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Rook(false));
                        fileIndex++;
                        break;
                    case 'b':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Bishop(false));
                        fileIndex++;
                        break;
                    case 'n':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Knight(false));
                        fileIndex++;
                        break;
                    case 'p':
                        board.getSpot(fileIndex, (7 - rankIndex)).setPiece(new Pawn(false));
                        fileIndex++;
                        break;
                    default:
                        fileIndex += Integer.parseInt(String.valueOf(character));
                }
            }
        }

        // Set white turn if turn.equals("w") else set black turn (pull request #6)

        // Parse castling square and add it to the board

        // Integer.parseInt halfMoveClock and set it to the board

        // Integer.parseInt moveNumber and set it to the board
    }

    public boolean isFenValid() {
        try {
            checkFenValidity();
        } catch (InvalidFenException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

}
