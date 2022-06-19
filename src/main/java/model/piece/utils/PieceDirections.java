package model.piece.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This enum contains the standard directions a piece can go to.
 */
public enum PieceDirections {
    /**
     * The pawn directions.
     */
    PAWN_DIR {
        @Override
        public List<Position> getDirections() {
            return List.of(Position.createNumericPosition(+1, +1),
                    Position.createNumericPosition(-1, +1));
        }
    },
    /**
     * The king directions.
     */
    KING_DIR {
        @Override
        public List<Position> getDirections() {
            return Stream.concat(ROOK_DIR.getDirections().stream(),
                    BISHOP_DIR.getDirections().stream()).collect(Collectors.toList());
        }
    },
    /**
     * The rook directions.
     */
    ROOK_DIR {
        @Override
        public List<Position> getDirections() {
            return List.of(Position.createNumericPosition(+1, 0),
                    Position.createNumericPosition(-1, 0),
                    Position.createNumericPosition(0, +1),
                    Position.createNumericPosition(0, -1));
        }
    },
    /**
     * The bishop directions.
     */
    BISHOP_DIR {
        @Override
        public List<Position> getDirections() {
            return List.of(Position.createNumericPosition(+1, +1),
                    Position.createNumericPosition(-1, -1),
                    Position.createNumericPosition(+1, -1),
                    Position.createNumericPosition(-1, +1));
        }
    },
    /**
     * The knight directions.
     */
    KNIGHT_DIR {
        @Override
        public List<Position> getDirections() {
            return List.of(Position.createNumericPosition(-1, MINUS_TWO),
                    Position.createNumericPosition(+1, MINUS_TWO),
                    Position.createNumericPosition(+2, -1),
                    Position.createNumericPosition(+2, +1),
                    Position.createNumericPosition(-1, +2),
                    Position.createNumericPosition(+1, +2),
                    Position.createNumericPosition(MINUS_TWO, -1),
                    Position.createNumericPosition(MINUS_TWO, +1));
        }
    },
    /**
     * The queen directions.
     */
    QUEEN_DIR {
        @Override
        public List<Position> getDirections() {
            return Stream.concat(BISHOP_DIR.getDirections().stream(), ROOK_DIR.getDirections().stream())
                    .collect(Collectors.toUnmodifiableList());
        }
    };

    private static final int MINUS_TWO = -2;
    /**
     * Returns a list of all the directions that this piece can move in.
     *
     * @return A list of positions.
     */
    public abstract List<Position> getDirections();
}
