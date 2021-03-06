package model.board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.parsers.FenConverter;
import model.parsers.FenConverterImpl;
import model.piece.utils.Side;
import model.piece.utils.Name;
import model.piece.utils.Numbers;
import model.piece.utils.Position;
import model.pieces.Piece;
import model.pieces.PieceFactory;
import model.pieces.PieceFactoryImpl;

/**
     * 
     * This class create different types of chessboard
     * for different types of game modes.
     *
     */
public class ChessboardFactoryImpl implements ChessboardFactory {

    private final FenConverter fenConverter = new FenConverterImpl();

    @Override
    public Chessboard createNormalCB() {
        final List<Piece> chessOnBoard = this.createPawns(Numbers.SIX, Side.WHITE);
        chessOnBoard.addAll(this.createBackLine(Numbers.SEVEN, Side.WHITE));
        chessOnBoard.addAll(this.createPawns(Numbers.ONE, Side.BLACK));
        chessOnBoard.addAll(this.createBackLine(Numbers.ZERO, Side.BLACK));
        return new ChessboardImpl(chessOnBoard, Numbers.SEVEN, Numbers.SEVEN);
    }

    @Override
    public Chessboard createTestCB(final List<Piece> piecesOnBoard) {
        return new ChessboardImpl(this.createCopyOf(piecesOnBoard), Numbers.SEVEN, Numbers.SEVEN);
    }

    @Override
    public Chessboard createCBToFen(final String fenString) {
        return fenConverter.getBoard(fenString);
    }

    private List<Piece> createPawns(final int row, final Side color) {
        final PieceFactory pieceCreator = new PieceFactoryImpl();
        return Stream.iterate(0, n -> n + 1)
                .limit(8)
                .<Piece>map(n -> pieceCreator.createPiece(Name.PAWN, Position.createNumericPosition(n, row), color))
                .collect(Collectors.toList());
    }

    private List<Piece> createBackLine(final int row, final Side color) {
        final PieceFactory pieceCreator = new PieceFactoryImpl();
        final List<Piece> backLine = new LinkedList<>();
        int n = 0;
        backLine.add(pieceCreator.createPiece(Name.ROOK, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.KNIGHT, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.BISHOP, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.QUEEN, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.KING, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.BISHOP, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.KNIGHT, Position.createNumericPosition(n++, row), color));
        backLine.add(pieceCreator.createPiece(Name.ROOK, Position.createNumericPosition(n, row), color));
        return backLine;
    }

    private List<Piece> createCopyOf(final List<Piece> originalList) {
        final List<Piece> copy = new ArrayList<>();
        final PieceFactory piece = new PieceFactoryImpl();
        originalList.stream()
            .forEach(x -> copy.add(piece.createPiece(x.getName(), x.getPosition(), x.getSide())));
        return copy;
    }
}
