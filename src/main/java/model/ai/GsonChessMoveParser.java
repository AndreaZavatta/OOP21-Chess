package model.ai;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Optional;

/**
 * Implementation of ChessMoveParser that extracts the move from a JSON response
 * using Gson.
 */
public class GsonChessMoveParser implements ChessMoveParser {

	private final Gson gson;

	public GsonChessMoveParser() {
		this.gson = new Gson();
	}

	private static class ChessMoveResponse {
		private String move;

		public String getMove() {
			return move;
		}

		public void setMove(final String move) {
			this.move = move;
		}
	}

	@Override
	public Optional<String> parse(final String llmResponse) {
		if (llmResponse == null || llmResponse.isBlank()) {
			return Optional.empty();
		}

		try {
			final ChessMoveResponse response = gson.fromJson(llmResponse, ChessMoveResponse.class);
			if (response != null && response.getMove() != null && !response.getMove().isBlank()) {
				return Optional.of(response.getMove());
			}
		} catch (JsonSyntaxException e) {
			// Log warning internally or just return empty as defined by the contract
		}

		return Optional.empty();
	}
}
