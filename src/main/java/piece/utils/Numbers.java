/**
 * 
 */
package piece.utils;

/**
 * This enum is only needed to remove magic numbers.
 */
public enum Numbers {
    /**
     * 
     */
    A {
        @Override
        public int getValue() {
            return SEVENS;
        }
    },
    /**
     * 
     */
    B {
        @Override
        public int getValue() {
            return SIXES;
        }
    },
    /**
     * 
     */
    C {
        @Override
        public int getValue() {
            return FIVES;
        }
    },
    /**
     * 
     */
    D {
        @Override
        public int getValue() {
            return 4;
        }
    },
    /**
     * 
     */
    E {
        @Override
        public int getValue() {
            return 3;
        }
    },
    /**
     * 
     */
    F {
        @Override
        public int getValue() {
            return 2;
        }
    },
    /**
     * 
     */
    G {
        @Override
        public int getValue() {
            return 1;
        }
    },
    /**
     * 
     */
    H {
        @Override
        public int getValue() {
            return 0;
        }
    },
    /**
     * 
     */
    ONE {
        @Override
        public int getValue() {
            return 0;
        }
    },
    /**
     * 
     */
    TWO {
        @Override
        public int getValue() {
            return 1;
        }
    },
    /**
     * 
     */
    THREE {
        @Override
        public int getValue() {
            return 2;
        }
    },
    /**
     * 
     */
    FOUR {
        @Override
        public int getValue() {
            return 3;
        }
    },
    /**
     * 
     */
    FIVE {
        @Override
        public int getValue() {
            return 4;
        }
    },
    /**
     * 
     */
    SIX {
        @Override
        public int getValue() {
            return FIVES;
        }
    },
    /**
     * 
     */
    SEVEN {
        @Override
        public int getValue() {
            return SIXES;
        }
    },
    /**
     * 
     */
    EIGHT {
        @Override
        public int getValue() {
            return SEVENS;
        }
    };

    private static final int SEVENS = 7;
    private static final int SIXES = 6;
    private static final int FIVES = 5;
    /**
     * @return the associate value
     */
    public abstract int getValue();
}
