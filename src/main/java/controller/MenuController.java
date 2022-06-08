package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

/**
 * 
 * This class controls the Menu and the related FXML files.
 *
 */

public class MenuController {

    @FXML
    void openUserHandler(final ActionEvent event) throws IOException {
        FXMLLoader userloader = new FXMLLoader(getClass().getResource("/layouts/UserHandler.fxml"));
            buildWindowNodes(event, userloader);
    }

    @FXML
    void openTutorial(final ActionEvent event) throws IOException {
        FXMLLoader tutorialloader = new FXMLLoader(getClass().getResource("/layouts/Tutorial.fxml"));
            buildWindowNodes(event, tutorialloader);
    }

    @FXML
    void openStats(final ActionEvent event) throws IOException {
            FXMLLoader statsloader = new FXMLLoader(getClass().getResource("/layouts/Stats.fxml"));
            buildWindowNodes(event, statsloader);
    }

    @FXML
    void exitApp(final ActionEvent event) {
        Platform.exit();
    }

    private void buildWindowNodes(final ActionEvent event, final FXMLLoader loader) throws IOException {
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("L.A.M.A. Chess");
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
