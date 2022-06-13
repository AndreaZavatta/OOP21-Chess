package controller.utils;

/**
 * 
 * Enum for piece image path.
 *
 */
public enum PieceImagePath {

    /**
     * Pawn piece path.
     */
    PAWN("blackPawn.png", "whitePawn.png"),

    /**
     * Queen piece path.
     */
    QUEEN("blackQueen.png", "whiteQueen.png"),

    /**
     * King piece path.
     */
    KING("blackKing.png", "whiteKing.png"),

    /**
     * Knight piece path.
     */
    KNIGHT("blackKnight.png", "whiteKnight.png"),

    /**
     * Bishop piece path.
     */
    BISHOP("blackBishop.png", "whiteBishop.png"),

    /**
     * Rook piece path.
     */
    ROOK("blackRook.png", "whiteRook.png");

    private static final String BASIC_PATH = "/pieces/images/";

    private final String black;
    private final String white;

    PieceImagePath(final String black, final String white) {
        this.black = black;
        this.white = white;
    }

    /**
     * 
     * @return get black image.
     */
    public String getBlackPath() {
        return BASIC_PATH + black;
    }

    /**
     * 
     * @return get white image.
     */
    public String getWhitePath() {
        return BASIC_PATH + white;
    }
}
