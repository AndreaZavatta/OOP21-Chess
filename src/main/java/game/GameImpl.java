package game;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Optional;

import board.Chessboard;
import board.ChessboardFactoryImpl;
import board.EndGame;
import board.EndGameImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pair.Pair;
import piece.utils.Position;
import piece.utils.Side;
import pieces.Piece;
import user.User;

/**
 * 
 * The main class of the game,
 * it manage a match user vs user.
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameImpl implements Game, Serializable {

    private static final long serialVersionUID = -5387039618669465656L;


    private Pair<User, Side> winner;
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
    @ConstructorProperties({"player1", "player2"})
    public GameImpl(final Pair<User, Side> player1, final Pair<User, Side> player2) {
        this.isFinished = false; 
        this.winner = null;
        this.chessboard = new ChessboardFactoryImpl().createNormalCB();
        this.gameController = new EndGameImpl();
        this.turnManager = new TurnImpl(player1, player2);
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
        } //else if (gameController.isDraw(turnManager.getUserTurn(), chessboard)) {
            //isFinished = true;
        //}
    }

    @Override
    public Optional<Pair<User, Side>> getWinner() {
        return Optional.ofNullable(winner);
    }

    @Override
    public boolean isGameFinished() {
        return this.isFinished;
    }

}
