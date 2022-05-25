package chess.parsers;

import static piece.utils.Name.KING;
import static piece.utils.Name.QUEEN;
import static piece.utils.Side.BLACK;
import java.util.List;
import java.util.stream.Collectors;
import board.Chessboard;
import piece.utils.Side;
import pieces.Piece;

/**
 * 
 *
 */
public class FenBuilder implements Fen {
    private Side side = null;
    private boolean blackCastlingQueenSide = true;
    private boolean blackCastlingKingSide = true;
    private boolean whiteCastlingQueenSide = true;
    private boolean whiteCastlingKingSide = true;
    private String enPassant = "-";
    private int halfMoveClock = 0;
    private int fullMoveNumber = 1;

    @Override
    public Fen activeColor(final Side side) {
        this.side = side;
        return this;
    }
    @Override
    public Fen blackCastledKingSide() {
        blackCastlingKingSide = false;
        return this;
    }
    @Override
    public Fen blackCastledQueenSide() {
        blackCastlingQueenSide = false;
        return this;
    }
    @Override
    public Fen whiteCastledKingSide() {
        whiteCastlingKingSide = false;
        return this;
    }
    @Override
    public Fen whiteCastledQueenSide() {
        whiteCastlingQueenSide = false;
        return this;
    }
    @Override
    public Fen enPassant(final String str) {
        enPassant = str;
        return this;
    }
    @Override
    public Fen halfMoveClock(final int halfMove) {
        this.halfMoveClock = halfMove;
        return this;
    }
    @Override
    public Fen fullMoveNumber(final int fullMove) {
        this.fullMoveNumber = fullMove;
        return this;
    }

    private String fromRowToString(final int row, final Chessboard chessboard) {
        StringBuilder res = new StringBuilder();
        List<Piece> rowPiece = getPiecesByRow(row, chessboard);
        int previousPiece = 0;
        for (Piece piece : rowPiece) {
           int diff = piece.getPosition().getX() - previousPiece;
           if (diff > 0) {
               res.append(diff);
           }
           String notation = piece.getName().notation();
           res.append(piece.getSide().equals(BLACK) ? notation.toLowerCase() : notation);
           previousPiece = piece.getPosition().getX() + 1;
        }
        if (previousPiece < chessboard.getxBorder() + 1) {
            res.append(chessboard.getxBorder() + 1 - previousPiece);
        }
        return res.toString();
    }
    private List<Piece> getPiecesByRow(final int row, final Chessboard chessboard) {
        return chessboard.getAllPieces().stream()
                .filter(x -> x.getPosition().getY() == row)
                .sorted((Piece x, Piece y) -> Integer.compare(x.getPosition().getX(), y.getPosition().getX()))
                .collect(Collectors.toList());
    }

    private String fenPiece(final Chessboard chessboard) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chessboard.getyBorder() + 1; i++) {
            str.append(fromRowToString(i, chessboard)).append("/");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
    private String getActiveColor() {
        return side.equals(Side.BLACK) ? "b" : "w";
    }

    private String getCastling() {
        if (!blackCastlingQueenSide && !blackCastlingKingSide && !whiteCastlingQueenSide && !whiteCastlingKingSide) {
            return "-";
        }
       return getCastlingSupport();
    }
    private String getCastlingSupport() {
        return  (whiteCastlingKingSide ? KING.notation() : "") +
                (whiteCastlingQueenSide ? QUEEN.notation() : "") +
                (blackCastlingKingSide ? KING.notation().toLowerCase() : "") +
                (blackCastlingQueenSide ? QUEEN.notation().toLowerCase() : "");
    }
    @Override
    public String build(final Chessboard chessboard) {
        return fenPiece(chessboard) +
                " " +
                getActiveColor() +
                " " +
                getCastling() +
                " " +
                enPassant +
                " " +
                halfMoveClock +
                " " +
                fullMoveNumber;
    }
}
