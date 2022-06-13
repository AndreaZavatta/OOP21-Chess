package game;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import board.Chessboard;
import board.ChessboardFactoryImpl;
import board.ControlCheck;
import board.ControlCheckImpl;
import board.EndGame;
import board.EndGameImpl;
import io.JsonUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.promotion.Promotion;
import model.promotion.PromotionImpl;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" ,timezone="CEST")
    private final LocalDate startDate;
    @JsonProperty("gameFinished")
    private boolean isFinished;
    private final Chessboard chessboard;
    private final transient EndGame gameController;
    private final Turn turnManager;
    @JsonIgnore
    private final Promotion promotion;

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
        this.promotion = new PromotionImpl();
        startDate = LocalDate.now();
    }

    @Override
    public void nextMove(final Position firstPos, final Position finalPos) throws IOException {
        if (isFinished) {
            return;
        }
        final Optional<Piece> attacker = chessboard.getPieceOnPosition(firstPos);
        if (checkIllegalArgument(attacker, firstPos, finalPos)) {
            throw new IllegalArgumentException();
        }
        chessboard.move(firstPos, finalPos);
        turnManager.turnIncrement();
        if (gameController.isCheckmate(turnManager.getUserTurn(), chessboard)) {
            winner = turnManager.getPairByColor(turnManager.getOppositeColor(turnManager.getUserTurn()));
            matchEnded(); 
        } else if (gameController.isDraw(turnManager.getUserTurn(), chessboard)) {
            matchEnded();
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

    @JsonIgnore
    @Override
    public Side getUserSideTurn() {
        return turnManager.getUserTurn();
    }

    @JsonIgnore
    @Override
    public boolean isInCheck() {
        final ControlCheck control = new ControlCheckImpl();
        return control.isInCheck(chessboard, getUserSideTurn());
    }

    @Override
    public boolean isCastling(final Piece piece, final Position targetPos) {
        return chessboard.isCastling(piece, targetPos);
    }

    @JsonIgnore
    @Override
    public Pair<User, User> getUsers() {
        return turnManager.getUsers();
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void promotion(final Name namePiece) {
        chessboard.promotion(namePiece);
    }

    @Override
    public void setDraw() throws IOException {
        matchEnded();
    }

    private boolean checkIllegalArgument(final Optional<Piece> attacker, final Position firstPos, final Position finalPos) {
        return chessboard.getPieceOnPosition(firstPos).isEmpty()
                || attacker.isPresent() && !attacker.get().getSide().equals(turnManager.getUserTurn())
                || !chessboard.getAllPosition(attacker.get()).contains(finalPos)
                || promotion.checkForPromotion(getPiecesList()).isPresent();
    }

    private void matchEnded() throws IOException {
        isFinished = true;
        JsonUtils.addToDatabase(this);
    }
}
