package model.database;

import model.game.Game;
import tuple.Pair;
import tuple.Triple;
import user.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * the class has methods for filtering data in the database.
 *
 */

public class DatabaseFilters {
    private final List<Game> games;
    /**
     * @param games that represent the database
     * constructor that takes as input the list of games representing the database.
     */
    public DatabaseFilters(final List<Game> games) {
        this.games = new ArrayList<>(games);
    }
    private Optional<User> getUserByPredicate(final Predicate<User> pred) {
        return games.stream().map(Game::getUsers)
                .flatMap(x -> Stream.of(x.getX(), x.getY()))
                .filter(pred).findFirst();
    }

    /**
     * Return the user whose name is equals to the string.
     *
     * @param str The string to search for.
     * @return Optional of {@link User User.class}
     */
    public Optional<User> getUser(final String str) {
        return getUserByPredicate(x -> x.getName().equals(str));
    }

    /**
     * Return the first user whose name contains the given string.
     *
     * @param str The string to search for.
     * @return Optional of {@link User User.class}
     */
    public Optional<User> getFirstOccurrenceUser(final String str) {
        return getUserByPredicate(x -> x.getName().contains(str));
    }


    /**
     * Return the number of games played by a user.
     *
     * @param user The user whose number of games played we want to know.
     * @return The number of games played by a user.
     */
    public long getNumberGamePlayed(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user)).count();
    }


    /**
     * Return the number of games won by the user.
     *
     * @param user The user whose number of games won we want to know.
     * @return The number of games won by the user.
     */
    public long getNumberGameWon(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isPresent())
                .filter(x -> x.getWinner().get().getX().equals(user))
                .count();
    }


    /**
     * Count the number of games that the user has played in that have not been won by either player.
     *
     * @param user The user whose statistics are being requested.
     * @return The number of games that the user has drawn.
     */
    public long getNumberGameDrawn(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isEmpty())
                .count();
    }

    /**
     * get a list of Triple from game filtered by a given predicate.
     * @param predicate used for filtering the games
     * @return List of Triple of {@link User User.class}, {@link User User.class}, {@link LocalDate LocalDate.class}
     */
    public List<Triple<User, User, LocalDate>> getTriple(final Predicate<Game> predicate) {
        return games.stream().filter(predicate)
                .map(x -> new Triple<>(x.getUsers().getX(), x.getUsers().getY(), x.getStartDate())).collect(Collectors.toUnmodifiableList());
    }
    /** 
     * get a game from a Triple. 
     * @param newSelection that represent a Triple
     * @return an Optional.of(User) or Optional.empty()
     */
    public Optional<Game> getGame(final Triple<User, User, LocalDate> newSelection) {
        return newSelection == null ? Optional.empty() : getGameSupport(newSelection);
    }

    private Optional<Game> getGameSupport(final Triple<User, User, LocalDate> newSelection) {
        return games.stream().filter(x -> x.getUsers().getX().equals(newSelection.getFirst()))
                .filter(x -> x.getUsers().getY().equals(newSelection.getSecond()))
                .filter(x -> x.getStartDate().equals(newSelection.getThird())).findFirst();
    }

    /**
     * get winner from a Triple that represent a game.
     * @param newSelection that represent a Triple
     * @return the string that represent the name of the winner or an empty string otherwise
     */
    public String getWinner(final Triple<User, User, LocalDate> newSelection) {
        return newSelection == null ? "" : getWinnerSupport(newSelection);
    }

    private String getWinnerSupport(final Triple<User, User, LocalDate> newSelection) {
        return getGame(newSelection)
                .flatMap(Game::getWinner)
                .map(Pair::getX)
                .map(User::getName)
                .orElse("");
    }
    /**
     * Return a predicate that returns true if the name of either user in the game contains the string s2.
     *
     * @param s2 The string to search for
     * @return A predicate that takes a Game and returns true if either of the users' names contains the string s2.
     */
    public Predicate<Game> filterByName(final String s2) {
        return x -> x.getUsers().getX().getName().contains(s2) || x.getUsers().getY().getName().contains(s2);
    }
}
