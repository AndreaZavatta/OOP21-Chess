package timer;

import application.Start;
import game.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 */
public class Timer implements Runnable {

    private final Game match;

    private TimerPlayer whitePlayer;

    private TimerPlayer blackPlayer;

    @FXML
    private final Label time1;

    @FXML
    private final Label time2;


    public Timer(final TimerPlayer whitePlayer, final TimerPlayer blackPlayer, final Label time1, final Label time2, final Game match) {
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

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {

            synchronized (this) {
                if (this.whitePlayer.isCurrentPlayer()) {
                    this.whitePlayer.setTimeLeft(this.whitePlayer.getTimeLeft() - 1);
                } else {
                    this.blackPlayer.setTimeLeft(this.blackPlayer.getTimeLeft() - 1);
                }
                Platform.runLater(() -> {
                    time1.setText(whitePlayer.getFormattedTime());
                    time2.setText(blackPlayer.getFormattedTime());
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.whitePlayer.getTimeLeft() == 0 | this.blackPlayer.getTimeLeft() == 0) {
                stop = true;
            }
        }

        Platform.runLater(Start::launch);
    }

}
