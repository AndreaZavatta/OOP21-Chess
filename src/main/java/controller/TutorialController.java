package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * 
 *
 */

public class TutorialController {

    @FXML
    private Button slider;
    private Button slider2;

    @FXML
    void openSlide(final ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/1.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Stats");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void goOn(final ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/2.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Stats");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void show(final MouseEvent event) {
        slider.setVisible(true);
    }
}
