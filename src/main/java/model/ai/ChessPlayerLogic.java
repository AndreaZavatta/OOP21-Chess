package model.ai;

import java.util.concurrent.CompletableFuture;

/**
 * Interface representing the core asynchronous logic of an AI chess player.
 */
public interface ChessPlayerLogic {

	/**
	 * Computes the next move asynchronously based on the provided board state.
	 * 
	 * @param board The current state of the board.
	 * @return A CompletableFuture that will complete with the chosen move string.
	 */
	CompletableFuture<String> computeMove(Board board);
}
