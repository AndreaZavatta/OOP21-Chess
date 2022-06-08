package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * 
 * This class controls the Menu and the related FXML files.
 *
 */

public class MenuController {

    private final AbstractController controller = new AbstractController();

    @FXML
    void openUserHandler(final ActionEvent event) {
        controller.openUserHandler(event);
    }

    @FXML
    void openTutorial(final ActionEvent event) {
        controller.openTutorial(event);
    }

    @FXML
    void openStats(final ActionEvent event) {
          controller.openStats(event);
    }

    @FXML
    void exitApp() {
        Platform.exit();
    }
}
