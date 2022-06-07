package chess.parsers;

import static model.piece.utils.Name.KING;
import static model.piece.utils.Name.QUEEN;
import static model.piece.utils.Side.BLACK;

import board.Chessboard;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import model.piece.utils.Side;
import model.pieces.Piece;

/**
 * 
 *
 */
public class FenBuilder implements Fen {
    private Side side;
    private boolean blackCastlingQueenSide = true;
    private boolean blackCastlingKingSide = true;
    private boolean whiteCastlingQueenSide = true;
    private boolean whiteCastlingKingSide = true;
    private String enPassant = "-";
    private int halfMoveClock;
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
    public Fen enPassant(final String pos) {
        enPassant = pos;
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
        final StringBuilder res = new StringBuilder();
        final List<Piece> rowPiece = getPiecesByRow(row, chessboard);
        int previousPiece = 0;
        for (final Piece piece : rowPiece) {
           final int diff = piece.getPosition().getX() - previousPiece;
           if (diff > 0) {
               res.append(diff);
           }
           final String notation = piece.getName().getChessNotation();
           res.append(piece.getSide().equals(BLACK) ? notation.toLowerCase(Locale.ROOT) : notation);
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
                .sorted(Comparator.comparingInt((Piece x) -> x.getPosition().getX()))
                .collect(Collectors.toList());
    }

    private String fenPiece(final Chessboard chessboard) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < chessboard.getyBorder() + 1; i++) {
            str.append(fromRowToString(i, chessboard)).append('/');
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
    private Character getActiveColor() {
        return side.equals(BLACK) ? 'b' : 'w';
    }

    private String getCastling() {
        if (!blackCastlingQueenSide && !blackCastlingKingSide && !whiteCastlingQueenSide && !whiteCastlingKingSide) {
            return "-";
        }
       return getCastlingSupport();
    }
    private String getCastlingSupport() {
        return  (whiteCastlingKingSide ? KING.getChessNotation() : "")
                + (whiteCastlingQueenSide ? QUEEN.getChessNotation() : "")
                + (blackCastlingKingSide ? KING.getChessNotation().toLowerCase(Locale.ROOT) : "")
                + (blackCastlingQueenSide ? QUEEN.getChessNotation().toLowerCase(Locale.ROOT) : "");
    }
    @Override
    public String build(final Chessboard chessboard) {
        return fenPiece(chessboard)
                + " "
                + getActiveColor()
                + " "
                + getCastling()
                + " "
                + enPassant
                + " "
                + halfMoveClock
                + " "
                + fullMoveNumber;
    }
}
