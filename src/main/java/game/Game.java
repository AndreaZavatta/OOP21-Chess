package game;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import user.User;

/**
 * 
 * interface for GameImpl.
 *
 */
public interface Game {

    /**
     * 
     * @param firstPos
     * @param finalPos
     * @throws IOException 
     */
    void nextMove(Position firstPos, Position finalPos) throws IOException;

    /**
     * 
     * @return the winner one
     */
    Optional<Pair<User, Side>> getWinner();

    /**
     * 
     * @return if the game is finished
     */
    boolean isGameFinished();

    /**
     * 
     * @return list of pieces
     */
    List<Piece> getPiecesList();

    /**
     * 
     * @param piece
     * @return list of possible position of a piece
     */
    List<Position> getPossiblePiecePositions(Piece piece);

    /**
     * 
     * @return user side turn
     */
    Side getUserSideTurn();

    /**
     * 
     * @return true if someone is in check
     */
    boolean isInCheck();

    /**
     * 
     * @param piece
     * @param targetPos
     * @return true if king is castling
     */
    boolean isCastling(Piece piece, Position targetPos);

    /**
     * 
     * @return 2 players of the game
     */
    Pair<User, User> getUsers();

    /**
     * 
     * @return game start date
     */
    LocalDate getStartDate();

    /**
     * 
     * @param namePiece
     */
    void promotion(Name namePiece);

    /**
     * method for set a draw.
     * @throws IOException 
     */
    void setDraw() throws IOException;
}
