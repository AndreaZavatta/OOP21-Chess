package timer;

/**
 *
 */
public enum MatchDuration {
    /**
     * 
     */
    FIVE_MINUTES_MATCH {
        @Override
        public double getTime() {
            return THREE_HUNDRED;
        }
    },
    /**
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
     *
     * @return
     */
    public abstract double getTime();
}
