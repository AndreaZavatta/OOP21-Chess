package model.ai;

/**
 * Interface responsible for translating the board state into a string prompt
 * for the LLM.
 */
public interface ChessPrompt {

	/**
	 * Generates the prompt string for the LLM based on the current board state and
	 * AI assigned color.
	 * 
	 * @param board   The current state of the board.
	 * @param aiColor The color assigned to the AI player (e.g., "WHITE" or
	 *                "BLACK").
	 * @return The formatted prompt string.
	 */
	String toPromptString(Board board, String aiColor);
}
