package chess.parsers;

import static piece.utils.Name.KING;
import static piece.utils.Name.QUEEN;
import static piece.utils.Side.BLACK;
import static piece.utils.Side.WHITE;
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
    private boolean blackCastlingQueenside = true;
    private boolean blackCastlingKingside = true;
    private boolean whiteCastlingQueenside = true;
    private boolean whiteCastlingKingside = true;
    private String enPassant = "-";
    private int halfMoveClock = 0;
    private int fullMoveNumber = 1;

    @Override
    public Fen activeColor(final Side side) {
        this.side = side;
        return this;
    }
    @Override
    public Fen blackCastledKingside() {
        blackCastlingKingside = false;
        return this;
    }
    @Override
    public Fen blackCastledQueenside() {
        blackCastlingQueenside = false;
        return this;
    }
    @Override
    public Fen whiteCastledKingside() {
        whiteCastlingKingside = false;
        return this;
    }
    @Override
    public Fen whiteCastledQueenside() {
        whiteCastlingQueenside = false;
        return this;
    }
    @Override
    public Fen enpassant(final String str) {
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
        List<Piece> rowPiece = getPieceByRow(row, chessboard);
        int xPrec = 0;
        for (Piece piece : rowPiece) {
           int diff = piece.getPosition().getX() - xPrec;
           if (diff > 0) {
               res.append(diff);
           }
           String notation = piece.getName().notation();
           res.append(piece.getSide().equals(BLACK) ? notation.toLowerCase() : notation);
           xPrec = piece.getPosition().getX() + 1;
        }
        if (xPrec < chessboard.getxBorder() + 1) {
            res.append(chessboard.getxBorder() + 1 - xPrec);
        }

        return res.toString();
    }
    private List<Piece> getPieceByRow(final int row, final Chessboard chessboard) {
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
        if (!blackCastlingQueenside && !blackCastlingKingside && !whiteCastlingQueenside && !whiteCastlingKingside) {
            return "-";
        }
       return getCastlingSupport();
    }
    private String getCastlingSupport() {
        StringBuilder res = new StringBuilder();
        res.append(whiteCastlingKingside ? KING.notation() : "");
        res.append(whiteCastlingQueenside ? QUEEN.notation() : "");
        res.append(blackCastlingKingside ? KING.notation().toLowerCase() : "");
        res.append(blackCastlingQueenside ? QUEEN.notation().toLowerCase() : "");
        return res.toString();
    }
    @Override
    public String build(final Chessboard chessboard) {
        StringBuilder res = new StringBuilder();
        return res.append(fenPiece(chessboard))
                .append(" ")
                .append(getActiveColor())
                .append(" ")
                .append(getCastling())
                .append(" ")
                .append(enPassant)
                .append(" ")
                .append(halfMoveClock)
                .append(" ")
                .append(fullMoveNumber)
                .toString();
    }
}
