package timer;

import game.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.Instant;

/**
 *
 */
public class TimerOld implements Runnable {

    private final Game match;

    private TimerPlayer whitePlayer;

    private TimerPlayer blackPlayer;

    @FXML
    private final Label time1;

    @FXML
    private final Label time2;


    public TimerOld(final TimerPlayer whitePlayer, final TimerPlayer blackPlayer, final Label time1, final Label time2, final Game match) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.time1 = time1;
        this.time2 = time2;
        this.match = match;
    }

    public TimerPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(final TimerPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public TimerPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(final TimerPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public TimerPlayer getCurrentPlayer() {
        return this.whitePlayer.isCurrentPlayer() ? this.whitePlayer : this.blackPlayer;
    }

    @Override
    public void run() {
        boolean stop = false;
        var previousTime = Instant.now();
        while (!stop) {
            var currentTime = Instant.now();
            final var delta = Duration.between(previousTime, currentTime).toMillis() / 1000.;

            final var currentPlayer = this.getCurrentPlayer();
            currentPlayer.subtractTime(delta);
            previousTime = currentTime;

            Platform.runLater(() -> {
                time1.setText(whitePlayer.getFormattedTime());
                time2.setText(blackPlayer.getFormattedTime());
            });

            if (this.whitePlayer.isTimerExpired() || this.blackPlayer.isTimerExpired()) {
                stop = true;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
