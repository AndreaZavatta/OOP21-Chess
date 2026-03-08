package model.ai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementation of an AI Chess Player using LangChain4j. Orchestrates the
 * prompt generation, LLM call, parsing, and move validation with a retry and
 * fallback mechanism.
 */
public class ChessAIPlayer implements ChessPlayerLogic {

	private static final Logger LOGGER = Logger.getLogger(ChessAIPlayer.class.getName());
	private static final int MAX_RETRIES = 5;

	private final ChatLanguageModel chatModel;
	private final ChessPrompt promptGenerator;
	private final ChessMoveParser moveParser;
	private final String aiColor;
	private final ExecutorService executor;
	private final Random random;

	/**
	 * Constructs the AI Player with injected dependencies.
	 * 
	 * @param chatModel       The LLM Client.
	 * @param promptGenerator The prompt generator.
	 * @param moveParser      The response parser.
	 * @param aiColor         The color of the AI (e.g., "WHITE" or "BLACK").
	 */
	public ChessAIPlayer(final ChatLanguageModel chatModel, final ChessPrompt promptGenerator,
			final ChessMoveParser moveParser, final String aiColor) {
		this.chatModel = chatModel;
		this.promptGenerator = promptGenerator;
		this.moveParser = moveParser;
		this.aiColor = aiColor;
		this.random = new Random();

		// Using a cached thread pool if Java versions prior to 21. For Java 21+,
		// Virtual Threads are preferred.
		// Assuming Java 15 as per build.gradle.kts settings
		this.executor = Executors.newCachedThreadPool();
	}

	@Override
	public CompletableFuture<String> computeMove(final Board board) {
		return CompletableFuture.supplyAsync(() -> performMoveComputation(board), executor);
	}

	private String performMoveComputation(final Board board) {
		final List<String> legalMoves = board.getLegalMoves();
		if (legalMoves == null || legalMoves.isEmpty()) {
			throw new IllegalStateException("Game is already over (checkmate or stalemate). No legal moves available.");
		}

		final String promptString = promptGenerator.toPromptString(board, aiColor);
		logInteraction("============== AI REQUEST ["
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] ==============\n"
				+ promptString + "\n");

		for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
			LOGGER.info(String.format("AI attempt %d/%d for color %s", attempt, MAX_RETRIES, aiColor));

			try {
				// 1. Call LLM
				final String llmResponse = chatModel.generate(promptString);
				LOGGER.fine("LLM raw response: " + llmResponse);
				logInteraction(
						"============== AI RESPONSE [Attempt " + attempt + "] ==============\n" + llmResponse + "\n");

				// 2. Parse Response
				final Optional<String> parsedMoveOpt = moveParser.parse(llmResponse);

				if (parsedMoveOpt.isPresent()) {
					final String parsedMove = parsedMoveOpt.get();

					// 3. Verify move legality
					if (board.isLegal(parsedMove)) {
						LOGGER.info("AI accepted move: " + parsedMove);
						return parsedMove;
					} else {
						LOGGER.warning("LLM hallucinated an illegal move: " + parsedMove);
					}
				} else {
					LOGGER.warning("LLM response could not be parsed as valid JSON. Retrying...");
				}

			} catch (Exception e) {
				LOGGER.severe("Exception during LLM processing on attempt " + attempt + ": " + e.getMessage());
			}
		}

		LOGGER.warning("AI exhausted all retries. Executing deterministic fallback (random move).");
		return executeFallback(legalMoves);
	}

	private String executeFallback(final List<String> legalMoves) {
		final int randomIndex = random.nextInt(legalMoves.size());
		final String fallbackMove = legalMoves.get(randomIndex);
		LOGGER.info("AI executed fallback move: " + fallbackMove);
		return fallbackMove;
	}

	private void logInteraction(final String text) {
		try (FileWriter fw = new FileWriter("ai_interaction_logs.txt", true); PrintWriter pw = new PrintWriter(fw)) {
			pw.println(text);
		} catch (IOException e) {
			LOGGER.warning("Could not write to AI log file: " + e.getMessage());
		}
	}
}
