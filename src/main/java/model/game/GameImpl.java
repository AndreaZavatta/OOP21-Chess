package model.game;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import model.board.Chessboard;
import model.board.ChessboardFactoryImpl;
import model.board.ControlCheck;
import model.board.ControlCheckImpl;
import model.board.EndGame;
import model.board.EndGameImpl;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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
     * @param player1 white player
     * @param player2 black player
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
        if (gameController.isCheckmate(chessboard, turnManager.getUserTurn())) {
            winner = turnManager.getPairByColor(turnManager.getOppositeColor(turnManager.getUserTurn()));
            matchEnded(); 
        } else if (gameController.isDraw(chessboard, turnManager.getUserTurn())) {
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
    public Piece promotion(final Name namePiece) {
        return chessboard.promotion(namePiece);
    }

    @Override
    public void setDraw() throws IOException {
        matchEnded();
    }

    @Override
    public Optional<Piece> checkPromotion() {
        return promotion.checkForPromotion(getPiecesList());
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
