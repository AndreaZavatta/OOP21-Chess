package model.game;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.tuple.Pair;
import model.piece.utils.Name;
import model.piece.utils.Position;
import model.piece.utils.Side;
import model.pieces.Piece;
import model.user.User;

/**
 * 
 * interface for GameImpl.
 *
 */
@JsonDeserialize(as = GameImpl.class)
public interface Game {

    /**
     * The method move a piece from a position to an another one.
     * @param firstPos initial position
     * @param finalPos position where the piece want to move
     * @throws IOException it can throw it when method try to 
     *                     store the game in database
     */
    void nextMove(Position firstPos, Position finalPos) throws IOException;

    /**
     * Method for getting the winner.
     * @return the winner if the game is finished
     */
    Optional<Pair<User, Side>> getWinner();

    /**
     * Check if the game is finished.
     * @return true if the game is finished
     */
    boolean isGameFinished();

    /**
     * Return all pieces in chessboard.
     * @return list of pieces in life
     */
    List<Piece> getPiecesList();

    /**
     * This method is taken by Chessboard class in order
     * to use it in the controller.
     * @param piece the chosen piece
     * @return list of possible position of the piece
     */
    List<Position> getPossiblePiecePositions(Piece piece);

    /**
     * Return a user turn of the game.
     * @return user side turn
     */
    Side getUserSideTurn();

    /**
     * Check if king is in check.
     * @return true if someone is in check
     */
    boolean isInCheck();

    /**
     * This method is taken by Chessboard class in order
     * to use it in the controller.
     * @param piece the king 
     * @param targetPos the position where the king want to move
     * @return true if king is castling
     */
    boolean isCastling(Piece piece, Position targetPos);

    /**
     * Get players of the game.
     * @return 2 players of the game
     */
    Pair<User, User> getUsers();

    /**
     * This method is useful for database. 
     * @return game start date
     */
    LocalDate getStartDate();

    /**
     * Pawn get promoted to the piece with the name 
     * taken in input.
     * @param namePiece name of the new piece
     * @return the new piece
     */
    Piece promotion(Name namePiece);

    /**
     * Method for set a draw.
     * @throws IOException It can throw and IOException when it 
     *                     try to store the game in the database.
     */
    void setDraw() throws IOException;

    /**
     * Check if a piece can be promoted.
     * @return a piece to get promoted if present
     */
    Optional<Piece> checkPromotion();

    /**
     * Invert the color in input.
     * @param color color to change
     * @return the opposite color 
     */
    Side getOppositeColor(Side color);

    /**
     * the method finish the game and give victory to the player
     * of the next turn.
     * @throws IOException it can throw it when add the game to database
     */
    void setWinner() throws IOException;
}
