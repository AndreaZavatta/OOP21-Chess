package controller;

import game.Game;
import game.GameImpl;
import io.JsonFileReader;
import io.JsonFileReaderImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.piece.utils.Side;
import pair.Pair;
import pair.Triple;
import user.User;
import user.UserImpl;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StatsController implements Initializable {

    JsonFileReader fr = new JsonFileReaderImpl("database.txt", ArrayList.class);
    List<?> list;
    private final Alert alert = new Alert(Alert.AlertType.NONE);
    @FXML
    private TextField txtFieldName = new TextField();
    @FXML
    private TextField txtFieldSurname = new TextField();
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



    public void showStats(){
        txtAreaStats.setText("here the stats");
        txtFieldName.setText("il mio nome");
        txtFieldSurname.setText("il mio cognome");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game game = new GameImpl(new Pair<>(new UserImpl("andrea"), Side.BLACK), new Pair<>(new UserImpl("marco"), Side.WHITE));
        Game game2 = new GameImpl(new Pair<>(new UserImpl("alessia"), Side.BLACK), new Pair<>(new UserImpl("martina"), Side.WHITE));
        List<Game> games = List.of(game, game2);
        System.out.println("dopo game");
        firstPlayer.setCellValueFactory(new PropertyValueFactory<>("x"));
        secondPlayer.setCellValueFactory(new PropertyValueFactory<>("y"));
        date.setCellValueFactory(new PropertyValueFactory<>("z"));
        System.out.println("dopo set cell");
        tableView.setItems(observableList());
        txtFieldName.textProperty().addListener((observableValue, s, s2) -> {
            txtAreaStats.setText(s2);
        });
    }

    private ObservableList<Triple<User,User,Instant>> observableList(){
        System.out.println(List.of(new Triple<>(new UserImpl("andrea"), new UserImpl("romina"),Instant.parse("2017-02-03T10:37:30.00Z"))));
        return  FXCollections.observableArrayList(
                //games.stream().map(x -> new Triple<>(x.getUsers().getX(), x.getUsers().getY(), x.getStartDate())).collect(Collectors.toList())
                List.of(new Triple<>(new UserImpl("andrea"), new UserImpl("romina"),Instant.parse("2017-02-03T10:37:30.00Z")))

        );
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
            System.out.println(e);
        }
    }
}
