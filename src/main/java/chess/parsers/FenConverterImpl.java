package chess.parsers;

import model.board.Chessboard;
import model.board.ChessboardFactoryImpl;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Character.isDigit;
import static java.lang.Character.getNumericValue;
import static java.lang.Character.toUpperCase;
import static java.lang.Character.isUpperCase;
import static model.piece.utils.Name.ROOK;
import static model.piece.utils.Name.BISHOP;
import static model.piece.utils.Name.KNIGHT;
import static model.piece.utils.Name.QUEEN;
import static model.piece.utils.Name.KING;
import static model.piece.utils.Name.PAWN;
import static model.piece.utils.Side.BLACK;
import static model.piece.utils.Side.WHITE;
/**
 * An implementation of FenConverter.
 */
public class FenConverterImpl implements FenConverter {
    private final PieceFactory pieceFactory = new PieceFactoryImpl();
    /**
     * 
     * @param chessNotation
     * @return the Name of piece
     */
    private Name fromChessNotationToName(final char chessNotation) {
        Name ret = null;
        switch (chessNotation) {
            case 'R' : ret = ROOK; break;
            case 'B' : ret = BISHOP; break;
            case 'N' : ret = KNIGHT; break;
            case 'Q' : ret = QUEEN; break;
            case 'K' : ret = KING; break;
            case 'P' : ret = PAWN; break;
            default: throw new IllegalArgumentException();
        }
        return ret;
    }

    /**
     * {@inheritDoc}.
     */
    public Chessboard getBoard(final String fen) {
        final List<Piece> list = buildList(fen.split(" "));
        return new ChessboardFactoryImpl().createTestCB(list);
    }

    private List<Piece> buildList(final String[] fenArray) {
        final List<Piece> list = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (final Character c : fenArray[0].toCharArray()) {
            if (isDigit(c)) {
                x += getNumericValue(c);
            } else if (c == '/') {
                y++;
                x = 0;
            } else {
                addPiece(list, x, y, c);
                x++;
            }
        }
        return list;
    }

    private void addPiece(final List<Piece> list, final int x, final int y, final Character c) {
        list.add(
                pieceFactory.createPiece(
                fromChessNotationToName(toUpperCase(c)), Position.createNumericPosition(x, y), isUpperCase(c) ? WHITE : BLACK));
    }
}
