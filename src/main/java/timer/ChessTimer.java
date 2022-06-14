package timer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.piece.utils.Side;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class ChessTimer {
    private final Timer t = new java.util.Timer(true);
    private final TimerPlayer white;
    private final TimerPlayer black;
    @FXML
    private final Label whiteTimer;
    @FXML
    private final Label blackTimer;

    /**
     *
     * @param white
     * @param black
     * @param whiteTimer
     * @param blackTimer
     */
    public ChessTimer(final TimerPlayer white, final TimerPlayer black, final Label whiteTimer, final Label blackTimer) {
        this.white = white;
        this.black = black;
        this.whiteTimer = whiteTimer;
        this.blackTimer = blackTimer;
    }


    public void buildTimer() {
        // TimerTask task = () -> chessTimerTask();
        t.scheduleAtFixedRate(new TimerTask() {
            private Instant previousTime = Instant.now();
            @Override
            public void run() {
                var currentTime = Instant.now();
                final var delta = Duration.between(previousTime, currentTime).toMillis() / 1000.;

                final var currentPlayer = white.isCurrentPlayer() ? white : black;
                currentPlayer.subtractTime(delta);
                previousTime = currentTime;

                Platform.runLater(() -> setTimerText());

                if (currentPlayer.isTimerExpired()) {
                    t.cancel();
                }
            }
        }, 0, 100);
    }


    private void setTimerText() {
        whiteTimer.setText(white.getFormattedTime());
        blackTimer.setText(black.getFormattedTime());
    }
}
