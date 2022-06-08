package controller;
import static javafx.scene.control.Alert.AlertType.ERROR;
import game.Game;
import io.JsonFileReader;
import io.JsonFileReaderImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.database.DatabaseFilters;
import tuple.Triple;
import user.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * controller for updating stats view.
 *
 */
public class StatsController implements Initializable {
    private final AbstractController contr = new AbstractController();
    private final JsonFileReader fr = new JsonFileReaderImpl("database.txt");
    @FXML
    private TextField txtFieldName = new TextField();
    @FXML
    private TextArea txtAreaStats = new TextArea();
    @FXML
    private TableColumn<User, String> firstPlayer;
    @FXML
    private TableColumn<User, String> secondPlayer;
    @FXML
    private TableColumn<LocalDate, String> date;
    @FXML
    private TableView<Triple<User, User, LocalDate>> tableView = new TableView<>();
    private DatabaseFilters database;

    /**
     * show stats of user, on click of show stats button.
     */
    public void showStats() {
        Optional<User> user = database.getFirstOccurrenceUser(txtFieldName.getText());
        if (user.isPresent()) {
            long gameWon = database.getNumberGameWon(user.get());
            long gamePlayed = database.getNumberGamePlayed(user.get());
            long gameDraw = database.getNumberGameDraw(user.get());

            txtAreaStats.setText("Name: " + user.get().getName() + "\n");
            txtAreaStats.appendText(user.get().getName() + " won " + (gameWon * 100 / gamePlayed) + "% of game played\n");
            txtAreaStats.appendText(user.get().getName() + " draw " + (gameDraw * 100 / gamePlayed) + "% of game played\n");
            txtAreaStats.appendText(user.get().getName() + " lose " + ((gamePlayed - gameDraw - gameWon) * 100 / gamePlayed) + "% of game played");
        } else {
            txtAreaStats.setText("name not found!");
        }
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        database = new DatabaseFilters(wrappedRead());
        firstPlayer.setCellValueFactory(new PropertyValueFactory<>("x"));
        secondPlayer.setCellValueFactory(new PropertyValueFactory<>("y"));
        date.setCellValueFactory(new PropertyValueFactory<>("z"));
        txtFieldName.textProperty().addListener((observableValue, s, s2) -> tableView.setItems(observableList(filter(s2))));
        tableView.setItems(observableList((x -> true)));
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> writeWinner(newSelection));
        tableView.setRowFactory(tableView2 -> addDeselectionRowEvent());
    }
    private List<Game> wrappedRead() {
        List<Game> games = null;
        try {
            games = fr.readFile();
        } catch (IOException e) {
            showAlert("error! unable to read database", ERROR);
        }
        return games;
    }
    private Predicate<Game> filter(final String s2) {
        return x -> x.getUsers().getX().getName().contains(s2) || x.getUsers().getY().getName().contains(s2);
    }
    private ObservableList<Triple<User, User, LocalDate>> observableList(final Predicate<Game> predicate) {
        return  FXCollections.observableArrayList(database.getTripleFromGame(predicate));
    }
    private void writeWinner(final Triple<User, User, LocalDate> newSelection) {
        if (newSelection != null) {
            database.getGame(newSelection).
                    ifPresentOrElse(x -> writeStatsGamePresent(database.getWinner(newSelection)),
                            () -> txtAreaStats.setText("error! game not found"));
        }
    }
    private void writeStatsGamePresent(final String winner) {
        if (!winner.isEmpty()) {
            txtAreaStats.setText("the winner is: " + winner);
        } else {
            txtAreaStats.setText("the game ended in a draw");
        }
    }
    private TableRow<Triple<User, User, LocalDate>> addDeselectionRowEvent() {
        final TableRow<Triple<User, User, LocalDate>> row = new TableRow<>();
        row.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> deselectRow(row, event));
        return row;
    }

    private void deselectRow(final TableRow<Triple<User, User, LocalDate>> row, final MouseEvent event) {
        final int index = row.getIndex();
        if (index >= 0 && index < tableView.getItems().size() && tableView.getSelectionModel().isSelected(index)) {
            tableView.getSelectionModel().clearSelection();
            event.consume();
            txtAreaStats.setText("");
        }
    }
    private void showAlert(final String str, final Alert.AlertType type) {
        contr.showAlert(str, type);
    }
    @FXML
    void backToMainMenu(final Event event) {
        contr.backToMenu(event);
    }
}
