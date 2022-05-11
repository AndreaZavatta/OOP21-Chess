package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 
 * 
 *
 */

public class TutorialController {

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
//
//    @FXML
//    void goBack(final ActionEvent event) {
//
//    }

    @FXML
    void showPiece(final ActionEvent event) {
        Button caller = (Button)event.getSource();
        System.out.println(caller.getId());
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/"+piece+".fxml"));
//            Parent root = (Parent) loader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Pieces");
//            stage.setScene(new Scene(root));
//            stage.show();
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }
    

  void showBishop(final ActionEvent event) {
      showPiece("Bishop", event);
  }
    
    @FXML
    void showBishop(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/Bishop.fxml"));
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
    void showKing(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/King.fxml"));
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
    void showKnight(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/Knight.fxml"));
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
    void showPawn(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/Pawn.fxml"));
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
    void showQueen(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/Queen.fxml"));
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
    void showRook(final ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/tutorial/Rook.fxml"));
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
