package controller;

import game.Game;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
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
    private TableView<String> tableView = new TableView<>();


    public void showStats(){
        txtAreaStats.setText("here the stats");
        txtFieldName.setText("il mio nome");
        txtFieldSurname.setText("il mio cognome");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldName.textProperty().addListener((observableValue, s, s2) -> {
            txtAreaStats.setText(s2);
        });
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
