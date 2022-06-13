package timer;

import game.Game;
import javafx.scene.image.Image;
import model.piece.utils.Side;
import user.UserControllerImpl;

/**
 *
 */
public class TimerPlayer extends UserControllerImpl {
    private int timeLeft;
    private final Game match;
    private Side currentPlayer;

    /**
     * @param name
     * @param img
     * @param timeLeft
     * @param match
     */
    public TimerPlayer(final String name, final Image img, final int timeLeft, final Game match, final Side s) {
        super(name, img);
        this.timeLeft = timeLeft;
        this.match = match;
        this.currentPlayer = s;
    }

    /**
     *
     * @param game
     */
    public void setCurrentPlayer(final Game game) {
        this.currentPlayer = game.getUserSideTurn();
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
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     *
     * @param timeLeft
     */
    public void setTimeLeft(final int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     *
     * @return
     */
    public String getFormattedTime() {
        String minutes = String.valueOf(this.timeLeft / 60);
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        String seconds = String.valueOf(this.timeLeft % 60);
        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        return String.format("%s:%s", minutes, seconds);
    }

}
