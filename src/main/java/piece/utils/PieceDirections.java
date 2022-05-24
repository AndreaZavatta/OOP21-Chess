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
            return List.of(Position.createNumericPosition(+1, +1), Position.createNumericPosition(-1, +1));
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
            return List.of(Position.createNumericPosition(+1, 0), Position.createNumericPosition(-1, 0), 
                    Position.createNumericPosition(0, +1), Position.createNumericPosition(0, -1));
        }
    },
    /**
     * The bishop directions.
     */
    BISHOP_DIR {
        @Override
        public List<Position> directions() {
            return List.of(Position.createNumericPosition(+1, +1), Position.createNumericPosition(-1, -1),
                    Position.createNumericPosition(+1, -1), Position.createNumericPosition(-1, +1));
        }
    },
    /**
     * The knight directions.
     */
    KNIGHT_DIR {
        @Override
        public List<Position> directions() {
            return List.of(Position.createNumericPosition(-1, MTWO), Position.createNumericPosition(+1, MTWO),
                    Position.createNumericPosition(+2, -1), Position.createNumericPosition(+2, +1),
                    Position.createNumericPosition(-1, +2), Position.createNumericPosition(+1, +2),
                    Position.createNumericPosition(MTWO, -1), Position.createNumericPosition(MTWO, +1));
        }
    },
    /**
     * The queen directions.
     */
    QUEEN_DIR {
        @Override
        public List<Position> directions() {
            return Stream.concat(BISHOP_DIR.directions().stream(), ROOK_DIR.directions().stream())
                    .collect(Collectors.toUnmodifiableList());
        }
    };

    private static final int MTWO = -2;
    /**
     * 
     * @return the directions a piece can go to.
     */
    public abstract List<Position> directions();
}
