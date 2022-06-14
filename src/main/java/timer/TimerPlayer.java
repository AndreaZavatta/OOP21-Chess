package timer;

import game.Game;
import javafx.scene.image.Image;
import model.piece.utils.Side;
import user.UserControllerImpl;

/**
 *
 */
public class TimerPlayer extends UserControllerImpl {
    private static final int SECONDS_IN_ONE_MINUTE = 60;
    private double timeLeft;
    private final Game match;
    private final Side currentPlayer;
    private boolean timerExpired;

    /**
     * @param name
     * @param img
     * @param timeLeft
     * @param match
     */
    public TimerPlayer(final String name, final Image img, final double timeLeft, final Game match, final Side s) {
        super(name, img);
        this.timeLeft = timeLeft;
        this.match = match;
        this.currentPlayer = s;
        this.timerExpired = false;
    }

    /**
     *
     * @return
     */
    public boolean isCurrentPlayer() {
        return match.getUserSideTurn().equals(this.currentPlayer);
    }

    /**
     *
     * @return
     */
    public double getTimeLeft() {
        return timeLeft;
    }

    /**
     *
     * @param timeLeft
     */
    private void setTimeLeft(final double timeLeft) {
        this.timeLeft = timeLeft;
        if (this.timeLeft <= 0.) {
            this.timerExpired = true;
            this.timeLeft = 0.;
        }
    }

    /**
     *
     * @param delta
     */
    public void subtractTime(final double delta) {
        this.setTimeLeft(this.getTimeLeft() - delta);
    }

    /**
     *
     * @return
     */
    public boolean isTimerExpired() {
        return this.timerExpired;
    }

    /**
     *
     * @return
     */
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
}
