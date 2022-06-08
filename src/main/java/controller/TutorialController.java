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

public class TutorialController extends  AbstractController {

    @FXML
    void openSlide(final ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/1.fxml"));
            buildWindowNodes(event, loader);
    }

    @FXML
    void goOn(final ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/2.fxml"));
            buildWindowNodes(event, loader);
    }

    @FXML
    void goBack(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/1.fxml"));
            buildWindowNodes(event, loader);
    }

    @FXML
    void showPiece(final ActionEvent event) throws IOException {
        final Button caller = (Button) event.getSource();
        System.out.println(caller.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/" + caller.getId() + ".fxml"));
            buildWindowNodes(event, loader);
    }
}
