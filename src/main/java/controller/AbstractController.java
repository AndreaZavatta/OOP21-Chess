package controller;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.event.ActionEvent;
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
            showError("unable to open menu", ERROR);
        }
    }

    void showError(String str, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setContentText("");
        alert.setHeaderText(str);
        alert.show();
    }



}
