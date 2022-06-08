package controller;
import static javafx.scene.control.Alert.AlertType.ERROR;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractController {
    private final Alert alert = new Alert(Alert.AlertType.NONE);

    void changePage(final Event event, final String string) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource(string));
            buildWindowNodes(event, loader);
        } catch (IOException e) {
            showAlert("unable to open menu", ERROR);
        }
    }
    @FXML
    void backToMenu(final Event event){
        changePage(event, "/layouts/MainMenu.fxml");
    }

    void showAlert(String str, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setContentText("");
        alert.setHeaderText(str);
        alert.show();
    }

    void buildWindowNodes(final Event event, final FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("L.A.M.A. Chess");
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }



}
