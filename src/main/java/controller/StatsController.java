package controller;
import game.Game;
import io.JsonFileReader;
import io.JsonFileReaderImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tuple.Pair;
import tuple.Triple;
import user.User;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * controller for updating stats view.
 *
 */
public class StatsController implements Initializable {

    private final JsonFileReader fr = new JsonFileReaderImpl("database.txt");

    private final Alert alert = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField txtFieldName = new TextField();
    @FXML
    private TextArea txtAreaStats = new TextArea();
    @FXML
    private TableColumn<User, String> firstPlayer;
    @FXML
    private TableColumn<User, String> secondPlayer;
    @FXML
    private TableColumn<Instant, String> date;
    @FXML
    private TableView<Triple<User, User, Instant>> tableView = new TableView<>();

    private List<Game> games;

    /**
     * show stats of user, on click of show stats button.
     */
    public void showStats() {
        Optional<User> user = getFirstOccurrenceUser(txtFieldName.getText());
        if(user.isPresent()) {
            long gameWon = getNumberGameWon(user.get());
            long gamePlayed = getNumberGamePlayed(user.get());
            long gameDraw = getNumberGameDraw(user.get());

            txtAreaStats.setText("Name: " + user.get().getName()+ "\n");
            txtAreaStats.appendText(user.get().getName() + " won " + (gameWon * 100 / gamePlayed) + "% of game played\n");
            txtAreaStats.appendText(user.get().getName() + " draw " + (gameDraw * 100 / gamePlayed) + "% of game played\n");
            txtAreaStats.appendText(user.get().getName() + " lose " + ((gamePlayed - gameDraw - gameWon) * 100 / gamePlayed) + "% of game played");
        }else{
            txtAreaStats.setText("name not found!");
        }
    }

    private Optional<User> getFirstOccurrenceUser(String str){
    return games.stream().map(Game::getUsers)
            .flatMap(x -> Stream.of(x.getX(), x.getY()))
            .filter(x -> x.getName().contains(str)).findFirst();
}

    private long getNumberGamePlayed(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user)).count();
    }

    private long getNumberGameWon(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isPresent())
                .filter(x -> x.getWinner().get().getX().equals(user))
                .count();
    }
    private long getNumberGameDraw(final User user) {
        return games.stream()
                .filter(x -> x.getUsers().getX().equals(user) || x.getUsers().getY().equals(user))
                .filter(x -> x.getWinner().isEmpty())
                .count();
    }
    private List<Game> wrappedRead(){
        List<Game> games = null;
        try{
            games = fr.readFile();
        } catch (IOException e) {
            showError();
        }
        return games;
    }

    private void showError() {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setHeaderText("error! unable to read database");
        alert.show();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        games = wrappedRead();
        firstPlayer.setCellValueFactory(new PropertyValueFactory<>("x"));
        secondPlayer.setCellValueFactory(new PropertyValueFactory<>("y"));
        date.setCellValueFactory(new PropertyValueFactory<>("z"));
        txtFieldName.textProperty().addListener((observableValue, s, s2) -> tableView.setItems(observableList(filter(s2))));
        tableView.setItems(observableList((x -> true)));
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> wrappedWinner(newSelection));
        tableView.setRowFactory(tableView2 -> addDeselectionRowEvent());

    }

    private void wrappedWinner(Triple<User, User, Instant> newSelection) {
        try {
            writeWinner(newSelection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TableRow<Triple<User, User, Instant>> addDeselectionRowEvent() {
        final TableRow<Triple<User, User, Instant>> row = new TableRow<>();
        row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> deselectRow(row, event));
        return row;
    }

    private void deselectRow(TableRow<Triple<User, User, Instant>> row, MouseEvent event) {
        final int index = row.getIndex();
        if (index >= 0 && index < tableView.getItems().size() && tableView.getSelectionModel().isSelected(index)  ) {
            tableView.getSelectionModel().clearSelection();
            event.consume();
            txtAreaStats.setText("");
        }
    }

    private Predicate<Game> filter(final String s2) {
        return x -> x.getUsers().getX().getName().contains(s2) || x.getUsers().getY().getName().contains(s2);
    }

    private void writeWinner(final Triple<User, User, Instant> newSelection) throws IOException {
        if (newSelection != null) {
            String winner;
            Optional<Game> game;
            try {
                winner = getWinner(newSelection);
                game = getGame(newSelection);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            game.ifPresentOrElse(x -> writeStatsGamePresent(winner), () -> txtAreaStats.setText("error! game not found"));
        }
    }

    private void writeStatsGamePresent(final String winner) {
            if (!winner.isEmpty()) {
                txtAreaStats.setText("the winner is: " + winner);
            } else {
                txtAreaStats.setText("the game ended in a draw");
            }
    }

    private Optional<Game> getGame(final Triple<User, User, Instant> newSelection) throws IOException {
        return fr.readFile().stream().filter(x -> x.getUsers().getX().equals(newSelection.getFirst()))
                .filter(x -> x.getUsers().getY().equals(newSelection.getSecond()))
                .filter(x -> x.getStartDate().equals(newSelection.getThird())).findFirst();
    }
    private String getWinner(final Triple<User, User, Instant> newSelection) throws IOException {
        return getGame(newSelection)
                .flatMap(Game::getWinner)
                .map(Pair::getX)
                .map(User::getName)
                .orElse("");
    }

    private ObservableList<Triple<User, User, Instant>> observableList(final Predicate<Game> predicate) {
        return  FXCollections.observableArrayList(getTripleFromGame(predicate));
    }

    private List<Triple<User, User, Instant>> getTripleFromGame(final Predicate<Game> predicate) {
        return games.stream().filter(predicate)
                .map(x -> new Triple<>(x.getUsers().getX(), x.getUsers().getY(), x.getStartDate())).collect(Collectors.toUnmodifiableList());
    }


    @FXML
    void backToMenu(final ActionEvent event) {
        //TODO
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/MainMenu.fxml"));
            final Parent root = (Parent) loader.load();
            final Stage stage = new Stage();
            stage.setTitle("MENU");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
