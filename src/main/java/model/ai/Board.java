package model.ai;

import java.util.List;

/**
 * Support interface to decouple the AI Player from the internal chess engine at
 * first.
 */
public interface Board {

	/**
	 * @return the current board state in FEN notation.
	 */
	String getFen();

	/**
	 * @return the list of legal moves available in the current turn.
	 */
	List<String> getLegalMoves();

	/**
	 * Checks if a move is legal.
	 * 
	 * @param move the move to check
	 * @return true if legal, false otherwise
	 */
	boolean isLegal(String move);
}
