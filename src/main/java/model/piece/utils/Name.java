package model.piece.utils;

/**
 * A standard enum class for the pieces names. It also has a function that returns
 * a string representing the piece.
 */
public enum Name {
    /**
     * Pawn piece name.
     */
    PAWN {
        @Override
        public String getChessNotation() {
            return "P";
        }
    },
    /**
     * Queen piece name.
     */
    QUEEN {
        @Override
        public String getChessNotation() {
            return "Q";
        }
    },
    /**
     * King piece name.
     */
    KING {
  @Override
        public String getChessNotation() {
            return "K";
        }
    },
    /**
     * Knight piece name.
     */
    KNIGHT {
        @Override
        public String getChessNotation() {
            return "N";
        }
    },
    /**
     * Bishop piece name.
     */
    BISHOP {
        @Override
        public String getChessNotation() {
            return "B";
        }
    },
    /**
     * Rook piece name.
     */
    ROOK {
        @Override
        public String getChessNotation() {
            return "R";
        }
    };
    /**
     * Returns the chess notation for the piece.
     *
     * @return The chess notation of the piece.
     */
    public abstract String getChessNotation();
}
