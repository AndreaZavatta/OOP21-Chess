package chess.parsers;
import board.Chessboard;
import piece.utils.Side;

/**
 * 
 *
 */
public class FenBuilder implements Fen {
    private Side side = null;
    private boolean castlingQueenside = false;
    private boolean castlingKingside = false;
    private String enPassant = "";
    private int halfMove = 0;

    @Override
    public Fen setSide(final Side side) {
        this.side = side;
        return this;
    }
    @Override
    public Fen setCastlingQueenside() {
        castlingQueenside = true;
        return this;
    }
    @Override
    public Fen setCastlingKingside() {
        castlingKingside = true;
        return this;
    }
    @Override
    public Fen setEnpassant(final String str) {
        enPassant = str;
        return this;
    }
    @Override
    public Fen setHalfMove(final int halfMove) {
        this.halfMove = halfMove;
        return this;
    }
    private String fromRowToString(final int row, final Chessboard chessboard) {
        return "";
    }
    private String fenPiece(final Chessboard chessboard) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < chessboard.getyBorder(); i++) {
            str.append(fromRowToString(i, chessboard)).append("/");
        }
        str.deleteCharAt(str.length());
        return str.toString();
    }
    private String getActiveColor() {
        return side.equals(Side.BLACK) ? "b" : "w";
    }

    private String getCastling() {
        if (!castlingKingside && !castlingQueenside) {
            return "-";
        }
        return castlingKingside ? "k" : "q";
    }
    @Override
    public String build(final Chessboard chessboard) {
        StringBuilder res = new StringBuilder();
        return res.append(fenPiece(chessboard))
                .append(getActiveColor())
                .append(getCastling())
                .append(enPassant)
                .append(halfMove).toString();
    }
}
