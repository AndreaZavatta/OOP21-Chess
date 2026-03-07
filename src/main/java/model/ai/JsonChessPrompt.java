package model.ai;

/**
 * Implementation of ChessPrompt that generates a strict prompt demanding a JSON
 * response.
 */
public class JsonChessPrompt implements ChessPrompt {

	@Override
	public String toPromptString(final Board board, final String aiColor) {
		final StringBuilder prompt = new StringBuilder();
		prompt.append("You are a chess master playing as ").append(aiColor).append(".\n");
		prompt.append("The current board state in FEN notation is:\n");
		prompt.append(board.getFen()).append("\n\n");
		prompt.append("The following is the exhaustive list of all legal moves you can make this turn: \n");
		prompt.append(board.getLegalMoves().toString()).append("\n\n");
		prompt.append("You MUST choose exactly ONE move from the list of legal moves provided above.\n");
		prompt.append(
				"IMPORTANT INSTRUCTION: You MUST respond ONLY with a valid JSON object in the following format:\n");
		prompt.append("{\"move\": \"<your_chosen_move>\"}\n");
		prompt.append(
				"Do not add any additional text, markdown formatting, explanations, or comments. Just the raw JSON object.");

		return prompt.toString();
	}
}
