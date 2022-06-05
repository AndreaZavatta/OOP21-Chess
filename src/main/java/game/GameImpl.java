package game;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import board.Chessboard;
import board.ChessboardFactoryImpl;
import board.ControlCheck;
import board.ControlCheckImpl;
import board.EndGame;
import board.EndGameImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pair.Pair;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import user.User;

/**
 * 
 * The main class of the game,
 * it manage a match user vs user.
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameImpl implements Game {

    private Pair<User, Side> winner;
    private final Instant startDate;
    @JsonProperty("gameFinished")
    private boolean isFinished;
    private final Chessboard chessboard;
    private final transient EndGame gameController;
    private final Turn turnManager;

    /**
     * 
     * @param player1
     * @param player2
     */

    public GameImpl(final Pair<User, Side> player1, final Pair<User, Side> player2) {
        this.isFinished = false; 
        this.winner = null;
        this.chessboard = new ChessboardFactoryImpl().createNormalCB();
        this.gameController = new EndGameImpl();
        this.turnManager = new TurnImpl(player1, player2);
        this.startDate = Instant.now();
    }

    @Override
    public void nextMove(final Position firstPos, final Position finalPos) {
        final Optional<Piece> attacker = chessboard.getPieceOnPosition(firstPos);
        if (chessboard.getPieceOnPosition(firstPos).isEmpty()) {
            throw new IllegalArgumentException("Empty position selected.");
        } else if (attacker.isPresent() && !attacker.get().getSide().equals(turnManager.getUserTurn())) {
            throw new IllegalArgumentException("Cannot select enemy pieces.");
        } else if (!chessboard.getAllPosition(attacker.get()).contains(finalPos)) {
            throw new IllegalArgumentException("Not valid position selected.");
        } 

        chessboard.move(firstPos, finalPos);
        turnManager.turnIncrement();
        if (gameController.isCheckmate(turnManager.getUserTurn(), chessboard)) {
            winner = turnManager.getPairByColor(turnManager.getOppositeColor(turnManager.getUserTurn()));
            isFinished = true;
        } else if (gameController.isDraw(turnManager.getUserTurn(), chessboard)) {
            isFinished = true;
        }
    }

    @Override
    public Optional<Pair<User, Side>> getWinner() {
        return Optional.ofNullable(winner);
    }

    @Override
    public boolean isGameFinished() {
        return this.isFinished;
    }

    @JsonIgnore
    @Override
    public List<Piece> getPiecesList() {
        return this.chessboard.getAllPieces();
    }

    @Override
    public List<Position> getPossiblePiecePositions(final Piece piece) {
        return Collections.unmodifiableList(chessboard.getAllPosition(piece));
    }

    @Override
    public Side getUserSideTurn() {
        return turnManager.getUserTurn();
    }

    @Override
    public boolean isInCheck() {
        final ControlCheck control = new ControlCheckImpl();
        return control.isInCheck(chessboard, getUserSideTurn());
    }

    @Override
    public boolean isCastling(final Piece piece, final Position targetPos) {
        return chessboard.isCastling(piece, targetPos);
    }

    @Override
    public Pair<User, User> getUsers() {
        return turnManager.getUsers();
    }

    @Override
    public String getStartDate() {
        return startDate.toString();
    }
}
