package model.ai;

import java.util.Optional;

/**
 * Interface responsible for parsing the response from the LLM.
 */
public interface ChessMoveParser {

	/**
	 * Parses the LLM's raw response to extract the chosen move.
	 * 
	 * @param llmResponse The raw string response from the LLM.
	 * @return An Optional containing the parsed move string if successful, or an
	 *         empty Optional if parsing fails.
	 */
	Optional<String> parse(String llmResponse);
}
