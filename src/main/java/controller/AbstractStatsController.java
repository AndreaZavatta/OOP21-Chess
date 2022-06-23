package controller;

import io.JsonFileReader;
import io.JsonFileReaderImpl;
import model.database.DatabaseFilters;
import model.game.Game;
import model.user.User;
import tuple.Triple;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * The generic controller for Stats, you must extend this class in order to create your personalized controller based on view.
 */
public class AbstractStatsController {
    private final JsonFileReader fr = new JsonFileReaderImpl("database.txt");
    private DatabaseFilters database;
    String helpMessage() {
        return "You can use the text field above the table to filter "
                + "matches by player's name.\n"
                + "The search also allows you to see information such as percentages of "
                + "games won, lost, and drawn of a given player.\n"
                + "You can select a match to see the statistics associated with it";
    }
    DatabaseFilters getDatabase() {
        return database;
    }
    void setDatabase(final DatabaseFilters databaseFilters) {
        database = databaseFilters;
    }

    Optional<User> getUserFromString(final String input) {
        final Optional<User> user = database.getUser(input);
        return user.isEmpty() ? database.getFirstOccurrenceUser(input) : user;
    }

    private String getStatsSupport(final User user) {
        final long gameWon = database.getNumberGameWon(user);
        final long gamePlayed = database.getNumberGamePlayed(user);
        final long gameDraw = database.getNumberGameDrawn(user);
        return  "Name: " + user.getName() + "\n"
                + user.getName() + " won " + (gameWon * 100 / gamePlayed) + "% of game played\n"
                + user.getName() + " draw " + (gameDraw * 100 / gamePlayed) + "% of game played\n"
                + user.getName() + " lose " + ((gamePlayed - gameDraw - gameWon) * 100 / gamePlayed)
                + "% of game played";
    }

    String getStats(final String str) {
        return getUserFromString(str)
                .map(this::getStatsSupport).orElse("name not found");
    }

    String statsGamePresent(final String winner) {
        return winner.isEmpty() ? "the game ended in a draw" : "the winner is: " + winner;
    }
    String getWinner(final Triple<User, User, LocalDate> newSelection) {
        return database.getGame(newSelection).isPresent() ? database.getWinner(newSelection) : "error! game not found";
    }
    List<Game> handleRead(final Runnable runnable) {
        List<Game> games = null;
        try {
            games = fr.readFile();
        } catch (IOException e) {
            runnable.run();

        }
        return games;
    }
}
