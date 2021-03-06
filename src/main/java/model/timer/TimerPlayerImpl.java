package model.timer;

import controller.user.UserControllerImpl;
import javafx.scene.image.Image;
import model.game.Game;
import model.piece.utils.Side;

/**
 *
 * Implementation of the TimerPlayer Interface's method.
 *
 */
public class TimerPlayerImpl extends UserControllerImpl implements TimerPlayer {
    private static final int SECONDS_IN_ONE_MINUTE = 60;
    /**
     * The seconds left before the timer expires.
     */
    private double timeLeft;
    /**
     * The current match.
     */
    private final transient Game match;
    /**
     * The current player.
     */
    private final Side currentPlayer;
    /**
     * Returns true if the timer is expired.
     */
    private boolean timerExpired;
    /**
     * The TimerPlayer constructor.
     *
     * @param name player's name.
     * @param img player's selected image.
     * @param timeLeft time left before the time limit expires.
     * @param match the current match.
     * @param side the player's color.
     */
    public TimerPlayerImpl(final String name, final Image img, final double timeLeft, final Game match, final Side side) {
        super(name, img);
        this.timeLeft = timeLeft;
        this.match = match;
        this.currentPlayer = side;
        this.timerExpired = false;
    }
    @Override
    public boolean isCurrentPlayer() {
        return match.getUserSideTurn()
                    .equals(this.currentPlayer);
    }
    @Override
    public double getTimeLeft() {
        return timeLeft;
    }
    @Override
    public void subtractTime(final double delta) {
        this.setTimeLeft(this.getTimeLeft() - delta);
    }
    @Override
    public boolean isTimerExpired() {
        return this.timerExpired;
    }
    @Override
    public String getFormattedTime() {
        String minutes = String.valueOf((int) (this.timeLeft / SECONDS_IN_ONE_MINUTE));
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        String seconds = String.valueOf((int) (this.timeLeft % SECONDS_IN_ONE_MINUTE));
        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        return String.format("%s:%s", minutes, seconds);
    }
    // A private setter for the timeleft variable.
    private void setTimeLeft(final double timeLeft) {
        this.timeLeft = timeLeft;
        if (this.timeLeft <= 0.) {
            this.timerExpired = true;
            this.timeLeft = 0.;
        }
    }
}
