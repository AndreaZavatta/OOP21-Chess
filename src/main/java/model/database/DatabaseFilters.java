package model.database;

import game.Game;
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
    private Optional<User> getUserByPredicate(final String str, final Predicate<User> pred){
        return games.stream().map(Game::getUsers)
                .flatMap(x -> Stream.of(x.getX(), x.getY()))
                .filter(pred).findFirst();
    }
    /**
     * function for finding the given user in the database.
     * @param str
     * @return Optional.of(User) or Optional.empty
     */
    public Optional<User> getUser(final String str) {
        return getUserByPredicate(str, x -> x.getName().equals(str));
    }
    /**
     * this function find the first occurrence of User who's name contains the given string.
     * @param str the string whose name we want to search for within the database
     * @return Optional.of(User) or Optional.empty
     */
    public Optional<User> getFirstOccurrenceUser(final String str) {
        return getUserByPredicate(str, x -> x.getName().contains(str));
    }

    /**
     * count the game played from a User.
     * @param user the user whose information we want
     * @return the number of game played
     */
    public long getNumberGamePlayed(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user)).count();
    }

    /**
     * count the game won from a User.
     * @param user the user whose information we want
     * @return the number of game won
     */
    public long getNumberGameWon(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isPresent())
                .filter(x -> x.getWinner().get().getX().equals(user))
                .count();
    }
    /**
     * count the game drawed from a User.
     * @param user the user whose information we want
     * @return the number of game played
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
     * @return List of Triple<User, User, LocalDate>
     */
    public List<Triple<User, User, LocalDate>> getTripleFromGame(final Predicate<Game> predicate) {
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

    private Optional<Game> getGameSupport(Triple<User, User, LocalDate> newSelection) {
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

    private String getWinnerSupport(Triple<User, User, LocalDate> newSelection) {
        return getGame(newSelection)
                .flatMap(Game::getWinner)
                .map(Pair::getX)
                .map(User::getName)
                .orElse("");
    }
}
