package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class UserHandlerController {

	// Event Listener on Button.onAction
	@FXML
	public void startGame(ActionEvent event) {
	    //TODO
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Board.fxml"));
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
}
