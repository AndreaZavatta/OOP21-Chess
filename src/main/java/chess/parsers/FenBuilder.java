package chess.parsers;
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
        StringBuilder res = new StringBuilder();
        List<Piece> rowPiece = chessboard.getAllPieces().stream()
                .filter(x -> x.getPosition().getY() == row)
                .sorted((Piece x, Piece y) -> Integer.compare(x.getPosition().getX(), y.getPosition().getX()))
                .collect(Collectors.toList());
        res.append(calculateInitRowPawn(rowPiece));
        res.append(pieceRapresentation(rowPiece.get(0)));
        for (int i = 1; i < chessboard.getxBorder() + 1; i++) {
            res.append(calculatePawnBetweenFromPieces(rowPiece.get(i), rowPiece.get(i - 1)));
            res.append(pieceRapresentation(rowPiece.get(i)));
        }

        return res.toString();
    }
    private String calculateInitRowPawn(final List<Piece> rowPiece) {
        return rowPiece.get(0).getPosition().getX() != 0 ? Integer.toString(rowPiece.get(0).getPosition().getX())  : "";
    }
    private String calculatePawnBetweenFromPieces(final Piece piece1, final Piece piece2) {
        String res = "";
        if (differenceX(piece1, piece2) != 1) {
            res = Integer.toString(differenceX(piece1, piece2));
        }
        return res;
    }
    private int differenceX(final Piece piece1, final Piece piece2) {
        return piece1.getPosition().getX() - piece2.getPosition().getX();
    }
    private String pieceRapresentation(final Piece piece) {
        var notation = piece.getName().notation();
        return piece.getColor().equals(Side.BLACK) ? notation.toLowerCase() : notation;
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
