package piece.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A standard enum class for the pieces names.
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
    },
    /**
     * Queen piece name.
     */
    QUEEN {
        @Override
        public List<Position> directions() {
            return List.of();
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
    }; 
    /**
     * 
     * @return the directions a piece can go to.
     */
    public abstract List<Position> directions();
}
