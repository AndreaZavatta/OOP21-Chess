package piece.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        public List<Position> directions() {
            return List.of(new Position(+1, +1), new Position(-1, +1));
        }

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
        public List<Position> directions() {
            return List.of();
        }

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
        public List<Position> directions() {
            return Stream.concat(BISHOP.directions().stream(), 
                    ROOK.directions().stream()).collect(Collectors.toList());
        }

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
        public List<Position> directions() {
            return List.of(new Position(-1, -2), new Position(+1, -2),
                    new Position(+2, -1), new Position(+2, +1),
                    new Position(-1, +2), new Position(+1, +2),
                    new Position(-2, -1), new Position(-2, +1));
        }

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
        public List<Position> directions() {
            return List.of(new Position(+1, +1), new Position(-1, -1),
                    new Position(+1, -1), new Position(-1, +1));
        }

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
        public List<Position> directions() {
            return List.of(new Position(+1, 0), new Position(-1, 0), 
                    new Position(0, +1), new Position(0, -1));
        }

        @Override
        public String notation() {
            return "R";
        }
    }; 
    /**
     * 
     * @return the directions a piece can go to.
     */
    public abstract List<Position> directions();
    /**
     * 
     * @return the letter representing the piece.
     */
    public abstract String notation();
}
