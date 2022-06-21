package timer;

/**
 *
 * This interface models a Player with its own Timer.
 *
 */
public interface TimerPlayer {
    /**
     * Method that compares the user playing is the same for which time is flowing.
     *
     * @return true if the comparison was successful.
     */
    boolean isCurrentPlayer();

    /**
     * Getter for the timeleft variable.
     *
     * @return the number of seconds left.
     */
    double getTimeLeft();

    /**
     * A method that decrements the timer.
     *
     * @param delta the interval of time elapsed.
     */
    void subtractTime(double delta);

    /**
     * This method checks if the time limit has been reached.
     *
     * @return true if the timer is expired.
     */
    boolean isTimerExpired();

    /**
     * A method that formats the time in a string.
     *
     * @return the formatted time.
     */
    String getFormattedTime();
}
