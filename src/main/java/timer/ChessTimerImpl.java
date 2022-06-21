package timer;

import javafx.application.Platform;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.Duration;
import java.time.Instant;
import java.util.TimerTask;
import java.util.function.Consumer;

/**
 *
 * Implementation of ChessTimer Interface's methods.
 *
 */
public class ChessTimerImpl implements ChessTimer {
    private final Timer timer = new Timer(true);
    private final TimerPlayer white;
    private final TimerPlayer black;
    @FXML
    private final Label whiteTimer;
    @FXML
    private final Label blackTimer;
    private Consumer<TimerPlayer> consumer;

    /**
     * The Timer's constructor.
     *
     * @param white the white player.
     * @param black the black player.
     * @param whiteTimer the white player's timer.
     * @param blackTimer the black player's timer.
     */
    public ChessTimerImpl(final TimerPlayer white, final TimerPlayer black, final Label whiteTimer,
                          final Label blackTimer) {
        this.white = white;
        this.black = black;
        this.whiteTimer = whiteTimer;
        this.blackTimer = blackTimer;
    }
    @Override
    public void setGameOverListener(final Consumer<TimerPlayer> cons) {
        this.consumer = cons;
    }
    @Override
    public void buildTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            private Instant previousTime = Instant.now();
            @Override
            public void run() {
                final var currentTime = Instant.now();
                final var delta = Duration.between(previousTime, currentTime).toMillis() / 1000.;
                final var currentPlayer = white.isCurrentPlayer() ? white : black;
                currentPlayer.subtractTime(delta);
                previousTime = currentTime;

                Platform.runLater(() -> setTimerText());

                if (currentPlayer.isTimerExpired()) {
                    closeTimer();
                    if (consumer != null) {
                        Platform.runLater(() -> consumer.accept(currentPlayer));
                    }
                }
            }
        }, 0, 100);
    }
    private void setTimerText() {
        whiteTimer.setText(white.getFormattedTime());
        blackTimer.setText(black.getFormattedTime());
    }

    @Override
    public void closeTimer() {
        timer.cancel();
    }
}
