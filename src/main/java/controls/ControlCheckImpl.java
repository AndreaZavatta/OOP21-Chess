package controls;

import piece.utils.Color;
import piece.utils.Name;

import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;
import piece.utils.Position;
import board.Chessboard;
import board.ChessboardFactory;
import board.ChessboardFactoryImpl;
import pieces.Piece;

/**
 * 
 *
 */

public class ControlCheckImpl implements ControlCheck {

    private ChessboardFactory chessboardFact = new ChessboardFactoryImpl();
    @Override
    public List<Position> removeMoveInCheck(final Chessboard chessboard, final Piece piece) {

        final List<Position> avaliableMoves = new ArrayList<>(piece.getAllPossiblePositions(chessboard));
        final Iterator<Position> itPos = avaliableMoves.iterator();
        while (itPos.hasNext()) {
            final Position pos = itPos.next();
            final Chessboard chessboardCopy = chessboardFact.createTestCB(chessboard.getAllPieces());
            setPosition(piece, pos, chessboardCopy);
            if (isInCheck(chessboardCopy, piece.getColor())) {
                itPos.remove();
            }
        }
        return avaliableMoves;
    }

    private void setPosition(final Piece piece, final Position pos, final Chessboard chessboardCopy) {
        chessboardCopy.getAllPieces().stream()
        .filter(x -> x.getPosition()
        .equals(piece.getPosition()))
        .findFirst()
        .get()
        .setPosition(pos);
    }

    @Override
    public boolean isInCheck(final Chessboard chessboard, final Color color) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(color))
                .filter(x -> canEatKing(chessboard, x))
                .findFirst().isPresent();
    }
    private boolean canEatKing(final Chessboard chessboard, final Piece piece) {
        return piece.getAllPossiblePositions(chessboard).contains(getEnemyKingPosition(chessboard, piece));
    }
    private Position getEnemyKingPosition(final Chessboard chessboard, final Piece piece) {
        return chessboard.getAllPieces().stream()
                .filter(x -> !x.getColor().equals(piece.getColor()))
                .filter(x -> x.getName().equals(Name.KING))
                .findFirst()
                .get().getPosition();
    }



}
