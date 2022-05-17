package piece.utils;

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
        public String notation() {
            return "";
        }
    },
    /**
     * Queen piece name.
     */
    QUEEN {
        @Override
        public String notation() {
            return "Q";
        }
    },
    /**
     * King piece name.
     */
    KING {
  @Override
        public String notation() {
            return "K";
        }
    },
    /**
     * Knight piece name.
     */
    KNIGHT {
        @Override
        public String notation() {
            return "N";
        }
    },
    /**
     * Bishop piece name.
     */
    BISHOP {
        @Override
        public String notation() {
            return "B";
        }
    },
    /**
     * Rook piece name.
     */
    ROOK {
        @Override
        public String notation() {
            return "R";
        }
    };
    /**
     * 
     * @return the letter representing the piece.
     */
    public abstract String notation();
}
