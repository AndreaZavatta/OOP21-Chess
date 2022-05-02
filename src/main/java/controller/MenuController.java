package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    void openStats(ActionEvent event) {
        try {
            FXMLLoader statsloader = new FXMLLoader(getClass().getResource("/layouts/Stats.fxml"));
            Parent root3 = (Parent) statsloader.load();
            Stage stage3 = new Stage();
            stage3.setTitle("Stats");
            stage3.setScene(new Scene(root3));
            stage3.show();
        } catch (Exception e) {
            System.out.println("An error occurred loading this window!");
        }
    }

    @FXML
    void openTutorial(ActionEvent event) {
        try {
            FXMLLoader tutorialloader = new FXMLLoader(getClass().getResource("/layouts/Tutorial.fxml"));
            Parent root2 = (Parent) tutorialloader.load();
            Stage stage2 = new Stage();
            stage2.setTitle("Stats");
            stage2.setScene(new Scene(root2));
            stage2.show();
         } catch (Exception e) {
             System.out.println(e);
         }
    }

    @FXML
    void openUserHandler(ActionEvent event) {
        try {
            FXMLLoader userloader = new FXMLLoader(getClass().getResource("/layouts/UserHandler.fxml"));
            Parent root1 = (Parent) userloader.load();
            Stage stage1 = new Stage();
            stage1.setTitle("Stats");
            stage1.setScene(new Scene(root1));
            stage1.show();
         } catch (Exception e) {
             System.out.println("An error occurred loading this window!");
         }
    }

}

