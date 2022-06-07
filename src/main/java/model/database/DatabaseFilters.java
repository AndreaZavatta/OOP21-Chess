package model.database;

import game.Game;
import io.JsonFileReader;
import io.JsonFileReaderImpl;
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

public class DatabaseFilters {
    private final JsonFileReader fr = new JsonFileReaderImpl("database.txt");
    List<Game> games;
    public DatabaseFilters(List<Game> games){
        this.games = new ArrayList<>(games);
    }
    public Optional<User> getFirstOccurrenceUser(String str){
        return games.stream().map(Game::getUsers)
                .flatMap(x -> Stream.of(x.getX(), x.getY()))
                .filter(x -> x.getName().contains(str)).findFirst();
    }

    public long getNumberGamePlayed(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user)).count();
    }

    public long getNumberGameWon(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isPresent())
                .filter(x -> x.getWinner().get().getX().equals(user))
                .count();
    }
    public long getNumberGameDraw(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isEmpty())
                .count();
    }

    public List<Triple<User, User, LocalDate>> getTripleFromGame(final Predicate<Game> predicate) {
        return games.stream().filter(predicate)
                .map(x -> new Triple<>(x.getUsers().getX(), x.getUsers().getY(), x.getStartDate())).collect(Collectors.toUnmodifiableList());
    }

    public Optional<Game> getGame(final Triple<User, User, LocalDate> newSelection) {
        return games.stream().filter(x -> x.getUsers().getX().equals(newSelection.getFirst()))
                .filter(x -> x.getUsers().getY().equals(newSelection.getSecond()))
                .filter(x -> x.getStartDate().equals(newSelection.getThird())).findFirst();
    }

    public String getWinner(final Triple<User, User, LocalDate> newSelection) {
        return getGame(newSelection)
                .flatMap(Game::getWinner)
                .map(Pair::getX)
                .map(User::getName)
                .orElse("");
    }
}
