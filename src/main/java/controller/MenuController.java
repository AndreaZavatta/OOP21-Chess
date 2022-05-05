package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * 
 * This class controls the Menu and the related FXML files.
 *
 */

public class MenuController {

    @FXML
    void openUserHandler(final ActionEvent event) {
        try {
            FXMLLoader userloader = new FXMLLoader(getClass().getResource("/layouts/UserHandler.fxml"));
            //FXMLLoader gridloader = new FXMLLoader(getClass().getResource("/layouts/Grid.fxml"));
            Parent root1 = (Parent) userloader.load();
            Stage stage1 = new Stage();
            stage1.setTitle("Users");
            stage1.setScene(new Scene(root1));
            stage1.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void openTutorial(final ActionEvent event) {
        try {
            FXMLLoader tutorialloader = new FXMLLoader(getClass().getResource("/layouts/Tutorial.fxml"));
            Parent root2 = (Parent) tutorialloader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Tutorial");
            stage2.setScene(new Scene(root2));
            stage2.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void openStats(final ActionEvent event) {
        try {
            FXMLLoader statsloader = new FXMLLoader(getClass().getResource("/layouts/Stats.fxml"));
            Parent root3 = (Parent) statsloader.load();
            Stage stage3 = new Stage();
            stage3.setTitle("Stats");
            stage3.setScene(new Scene(root3));
            stage3.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void exitApp(final ActionEvent event) {
        Platform.exit();
    }

}

