package model.ai;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessAIPlayerTest {

	private ChatLanguageModel mockModel;
	private ChessPrompt promptGenerator;
	private ChessMoveParser moveParser;
	private Board mockBoard;
	private List<String> legalMoves;

	@BeforeEach
	void setUp() {
		promptGenerator = new JsonChessPrompt();
		moveParser = new GsonChessMoveParser();
		legalMoves = Arrays.asList("e2e4", "e2e3", "g1f3");

		mockBoard = new Board() {
			@Override
			public String getFen() {
				return "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
			}

			@Override
			public List<String> getLegalMoves() {
				return legalMoves;
			}

			@Override
			public boolean isLegal(final String move) {
				return legalMoves.contains(move);
			}
		};
	}

	@Test
	void testSuccessfulParsingFirstAttempt() throws ExecutionException, InterruptedException {
		mockModel = new ChatLanguageModel() {
			@Override
			public String generate(final String userMessage) {
				return "{\"move\": \"e2e4\"}";
			}

			@Override
			public dev.langchain4j.model.output.Response<dev.langchain4j.data.message.AiMessage> generate(
					List<dev.langchain4j.data.message.ChatMessage> messages) {
				return dev.langchain4j.model.output.Response
						.from(dev.langchain4j.data.message.AiMessage.from("{\"move\": \"e2e4\"}"));
			}
		};

		final ChessAIPlayer aiPlayer = new ChessAIPlayer(mockModel, promptGenerator, moveParser, "WHITE");
		final String computedMove = aiPlayer.computeMove(mockBoard).get();
		assertEquals("e2e4", computedMove);
	}

	@Test
	void testSuccessfulParsingSecondAttemptAfterHallucination() throws ExecutionException, InterruptedException {
		// Mock model creates a bad JSON on the 1st request, then good on the 2nd
		// request.
		mockModel = new ChatLanguageModel() {
			private int callCount = 0;

			@Override
			public String generate(final String userMessage) {
				callCount++;
				if (callCount == 1) {
					return "Here is my move: e2e4!";
				}
				return "{\"move\": \"g1f3\"}";
			}

			@Override
			public dev.langchain4j.model.output.Response<dev.langchain4j.data.message.AiMessage> generate(
					List<dev.langchain4j.data.message.ChatMessage> messages) {
				callCount++;
				if (callCount == 1) {
					return dev.langchain4j.model.output.Response
							.from(dev.langchain4j.data.message.AiMessage.from("Here is my move: e2e4!"));
				}
				return dev.langchain4j.model.output.Response
						.from(dev.langchain4j.data.message.AiMessage.from("{\"move\": \"g1f3\"}"));
			}
		};

		final ChessAIPlayer aiPlayer = new ChessAIPlayer(mockModel, promptGenerator, moveParser, "WHITE");
		final String computedMove = aiPlayer.computeMove(mockBoard).get();
		assertEquals("g1f3", computedMove);
	}

	@Test
	void testFallbackAfterExhaustingRetries() throws ExecutionException, InterruptedException {
		// Mock model that only hallucinated illegally.
		mockModel = new ChatLanguageModel() {
			@Override
			public String generate(final String userMessage) {
				return "{\"move\": \"illegalMove99\"}";
			}

			@Override
			public dev.langchain4j.model.output.Response<dev.langchain4j.data.message.AiMessage> generate(
					List<dev.langchain4j.data.message.ChatMessage> messages) {
				return dev.langchain4j.model.output.Response
						.from(dev.langchain4j.data.message.AiMessage.from("{\"move\": \"illegalMove99\"}"));
			}
		};

		final ChessAIPlayer aiPlayer = new ChessAIPlayer(mockModel, promptGenerator, moveParser, "WHITE");
		final String computedMove = aiPlayer.computeMove(mockBoard).get();
		// Since all retries fail, it should fall back to a random move from the legal
		// move list
		assertTrue(legalMoves.contains(computedMove));
	}
}
