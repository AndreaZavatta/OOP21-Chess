package board;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import piece.utils.Color;
import piece.utils.Name;
import piece.utils.Position;
import pieces.Piece;
import pieces.PieceFactory;
import pieces.PieceFactoryImpl;

/**
     * 
     * This class create different types of chessboard
     * for different types of game modes.
     *
     */
public class ChessboardFactoryImpl implements ChessboardFactory {

    @Override
    public Chessboard createNormalCB() {
        List<Piece> chessOnBoard = this.createPawns(2, Color.WHITE);
        chessOnBoard.addAll(this.createBackLine(2, Color.WHITE));
        chessOnBoard.addAll(this.createPawns(7, Color.BLACK));
        chessOnBoard.addAll(this.createBackLine(7, Color.BLACK));
        return null;
    }

    @Override
    public Chessboard createTestCB(final List<Piece> piecesOnBoard) {
        return new ChessboardImpl(piecesOnBoard, 8, 8);
    }

    private List<Piece> createPawns(final int row, final Color color) {
        PieceFactory pieceCreator = new PieceFactoryImpl();
        return Stream.iterate(0, n -> n + 1)
                .limit(8)
                .<Piece>map(n -> pieceCreator.createPiece(Name.PAWN, new Position(row, n), color))
                .collect(Collectors.toList());
    }

    private List<Piece> createBackLine(final int row, final Color color) {
        PieceFactory pieceCreator = new PieceFactoryImpl();
        List<Piece> backLine = new LinkedList<>();
        int n = 1;
        backLine.add(pieceCreator.createPiece(Name.ROOK, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.KNIGHT, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.BISHOP, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.QUEEN, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.KING, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.BISHOP, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.KNIGHT, new Position(row, n++), color));
        backLine.add(pieceCreator.createPiece(Name.ROOK, new Position(row, n), color));
        return backLine;
    }

}
