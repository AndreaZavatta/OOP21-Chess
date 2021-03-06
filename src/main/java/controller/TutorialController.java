package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * 
 * This class handles the Tutorial GUI and its functionalities.
 *
 */
public class TutorialController {
    private final ControllerUtils controller = new ControllerUtils();

    @FXML
    void openSlide(final ActionEvent event) {
            controller.openSlide(event);
    }
    @FXML
    void goOn(final ActionEvent event) {
        controller.goOn(event);
    }
    @FXML
    void openTutorial(final ActionEvent event) {
        controller.openTutorial(event);
    }
    @FXML
    void backToMenu(final ActionEvent event) {
        controller.backToMenu(event);
    }
    @FXML
    void openCastling(final ActionEvent event) {
        controller.openCastling(event);
    }
    @FXML
    void openEndgame(final ActionEvent event) {
        controller.openEndgame(event);
    }
    @FXML
    void openFinalSlide(final ActionEvent event) {
        controller.openFinalSlide(event);
    }
    @FXML
    void showPiece(final ActionEvent event) throws IOException {
        final Button caller = (Button) event.getSource();
        System.out.println(caller.getId());
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/" + caller.getId() + ".fxml"));
            controller.buildWindowNodes(event, loader);
    }
}
