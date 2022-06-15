package timer;

/**
 *
 * This Enum contains all possible stopwatch choices for the game.
 *
 */
public enum MatchDuration {
    /**
     * A match in which each player has five minutes total.
     */
    FIVE_MINUTES_MATCH {
        @Override
        public double getTime() {
            return THREE_HUNDRED;
        }
    },
    /**
     *
     * A match in which each player has ten minutes total.
     *
     */
    TEN_MINUTES_MATCH {
        @Override
        public double getTime() {
            return SIX_HUNDRED;
        }
    },
    /**
     *
     * A match in which each player has twenty minutes total.
     *
     */
    TWENTY_MINUTES_MATCH {
        @Override
        public double getTime() {
            return ONE_THOUSAND_TWO_HUNDRED;
        }
    },
    /**
     * 
     */
    THIRTY_MINUTES_MATCH {
        @Override
        public double getTime() {
            return ONE_THOUSAND_EIGHT_HUNDRED;
        }
    };

    private static final double THREE_HUNDRED = 300.;
    private static final double SIX_HUNDRED = 600.;
    private static final double ONE_THOUSAND_TWO_HUNDRED = 1200.;
    private static final double ONE_THOUSAND_EIGHT_HUNDRED = 1800.;
    /**
     * A getter for the selected stopwatch.
     * @return the number of seconds of the stopwatch.
     */
    public abstract double getTime();
}
