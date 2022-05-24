package chess.parsers;
import board.Chessboard;
import piece.utils.Side;
import pieces.Piece;

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
    public Fen side(final Side side) {
        this.side = side;
        return this;
    }
    @Override
    public Fen castlingQueenside() {
        castlingQueenside = true;
        return this;
    }
    @Override
    public Fen castlingKingside() {
        castlingKingside = true;
        return this;
    }
    @Override
    public Fen enpassant(final String str) {
        enPassant = str;
        return this;
    }
    @Override
    public Fen halfMove(final int halfMove) {
        this.halfMove = halfMove;
        return this;
    }
    private String fromRowToString(final int row, final Chessboard chessboard) {
        return "";
    }
    private String pieceRapresentation(final Piece piece) {
        var temp = piece.getName().notation();
        return piece.getColor().equals(Side.BLACK) ? temp : temp.toUpperCase();
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
