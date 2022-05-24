package chess.parsers;

import static piece.utils.Name.KING;
import static piece.utils.Name.QUEEN;
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
    private int fullMoveNumber = 0;

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
        List<Piece> rowPiece = chessboard.getAllPieces().stream()
                .filter(x -> x.getPosition().getY() == row)
                .sorted((Piece x, Piece y) -> Integer.compare(x.getPosition().getX(), y.getPosition().getX()))
                .collect(Collectors.toList());
        if (rowPiece.isEmpty()) {
            return Integer.toString(chessboard.getyBorder() + 1);
        }
        res.append(calculateInitRowPawn(rowPiece));
        res.append(pieceRapresentation(rowPiece.get(0)));
        int temp2 = 1;
        int pawnInteger = 0;
        for (int i = 1; temp2 <= chessboard.getxBorder(); i++) {
            if (isLastPieceOfRow(chessboard, rowPiece, i-1) && i != chessboard.getxBorder()) {
                pawnInteger = chessboard.getxBorder() - rowPiece.get(i - 1).getPosition().getX();
                String pawnString = pawnInteger != 0 ? Integer.toString(pawnInteger) : ""; 
                res.append(pawnString);
                break;
            } else {
                pawnInteger = calculatePawnBetweenFromPieces(rowPiece.get(i), rowPiece.get(i - 1));
                String pawnString = pawnInteger != 0 ? Integer.toString(pawnInteger) : ""; 
                res.append(pawnString);
            }
            res.append(pieceRapresentation(rowPiece.get(i)));
            temp2 += pawnInteger + 1;
        }

        return res.toString();
    }
    
    
    private boolean isLastPieceOfRow(final Chessboard chessboard, List<Piece> rowPiece, int i) {
        return rowPiece.get(i) == rowPiece.get(rowPiece.size() - 1) ;
    }
    private String calculateInitRowPawn(final List<Piece> rowPiece) {
        return rowPiece.get(0).getPosition().getX() != 0 ? Integer.toString(rowPiece.get(0).getPosition().getX())  : "";
    }
    private int calculatePawnBetweenFromPieces(final Piece piece1, final Piece piece2) {
        int res = 0;
        if (differenceX(piece1, piece2) != 1) {
            res = differenceX(piece1, piece2) - 1;
        }
        return res;
    }
    private int differenceX(final Piece piece1, final Piece piece2) {
        return piece1.getPosition().getX() - piece2.getPosition().getX();
    }
    private String pieceRapresentation(final Piece piece) {
        var notation = piece.getName().notation();
        return piece.getSide().equals(Side.BLACK) ? notation.toLowerCase() : notation;
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
                .append(Integer.toString(halfMoveClock))
                .append(" ")
                .append(Integer.toString(fullMoveNumber))
                .toString();
    }
}
