package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * 
 * It manage the selection of the name and image 
 * of the two users before starting the game.
 *
 */
public class UserHandlerController {
    @FXML
    private TextField userName1 = new TextField();
    @FXML
    private TextField userName2 = new TextField();
    @FXML 
    private final Pane pane = new Pane();
    private final Alert alert = new Alert(AlertType.NONE);

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

    @FXML
    void startGame(final ActionEvent event) {
        final String namePlayer1 = this.getUserName(userName1);
        final String namePlayer2 = this.getUserName(userName2);
        if (namePlayer1.isEmpty() || namePlayer2.isEmpty()) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Complete all field");
            alert.show();
            return;
        }
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Board.fxml"));
            final Parent root = (Parent) loader.load();
            final Stage stage = new Stage();
            stage.setTitle("MATCH");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String getUserName(final TextField userName) {
        return userName.getText();
    }

    private Image getUserImage(final Image img) {
        return img;
    }
}
