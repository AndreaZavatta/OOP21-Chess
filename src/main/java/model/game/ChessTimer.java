package model.game;

import java.util.function.Consumer;

/**
 *
 * This Interface builds a chess timer.
 *
 */
public interface ChessTimer {
    /**
     *  This method creates a chess timer.
     */
    void buildTimer();
    /**
     * This method notify the game when the timer is expired.
     * @param cons consumer that identifies the current player.
     */
    void setGameOverListener(Consumer<TimerPlayer> cons);
    /**
     * This method cancels the Timer Thread.
     */
    void closeTimer();
}
