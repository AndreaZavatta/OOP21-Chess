package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * 
 * This class controls the Menu and the related FXML files.
 *
 */

public class MenuController extends  AbstractController {

    @FXML
    void openUserHandler(final ActionEvent event) throws IOException {
        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/layouts/UserHandler.fxml"));
            buildWindowNodes(event, userLoader);
    }

    @FXML
    void openTutorial(final ActionEvent event) throws IOException {
        FXMLLoader tutorialLoader = new FXMLLoader(getClass().getResource("/layouts/Tutorial.fxml"));
            buildWindowNodes(event, tutorialLoader);
    }

    @FXML
    void openStats(final ActionEvent event) throws IOException {
            FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("/layouts/Stats.fxml"));
            buildWindowNodes(event, statsLoader);
    }

    @FXML
    void exitApp() {
        Platform.exit();
    }
}
