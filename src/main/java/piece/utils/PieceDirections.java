package piece.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This enum contains the standard directions a piece can go to.
 *
 */
public enum PieceDirections {
    /**
     * The pawn directions.
     */
    PAWN_DIR {
        @Override
        public List<Position> directions() {
            return List.of(new Position(+1, +1), new Position(-1, +1));
        }
    },
    /**
     * The king directions.
     */
    KING_DIR {
        @Override
        public List<Position> directions() {
            return Stream.concat(ROOK_DIR.directions().stream(),
                    BISHOP_DIR.directions().stream()).collect(Collectors.toList());
        }
    },
    /**
     * The rook directions.
     */
    ROOK_DIR {
        @Override
        public List<Position> directions() {
            return List.of(new Position(+1, 0), new Position(-1, 0), 
                    new Position(0, +1), new Position(0, -1));
        }
    },
    /**
     * The bishop directions.
     */
    BISHOP_DIR {
        @Override
        public List<Position> directions() {
            return List.of(new Position(+1, +1), new Position(-1, -1),
                    new Position(+1, -1), new Position(-1, +1));
        }
    },
    /**
     * The knight directions.
     */
    KNIGHT_DIR {
        @Override
        public List<Position> directions() {
            return List.of(new Position(-1, MTWO), new Position(+1, MTWO),
                    new Position(+2, -1), new Position(+2, +1),
                    new Position(-1, +2), new Position(+1, +2),
                    new Position(MTWO, -1), new Position(MTWO, +1));
        }
    };

    private static final int MTWO = -2;
    /**
     * 
     * @return the directions a piece can go to.
     */
    public abstract List<Position> directions();
}
