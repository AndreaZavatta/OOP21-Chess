package controller;

import java.io.IOException;
import java.util.List;

import gui.board.BoardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import user.UserController;
import user.UserControllerImpl;

/**
 * 
 * It manage the selection of the name and image 
 * of the two users before starting the game.
 *
 */
public class UserHandlerController {
    @FXML private TextField userName1 = new TextField();
    @FXML private TextField userName2 = new TextField();
    @FXML private Button choiseMaleUser1 = new Button();
    @FXML private Button choiseFemaleUser1 = new Button();
    @FXML private Button choiseMaleUser2 = new Button();
    @FXML private Button choiseFemaleUser2 = new Button();
    private final Alert alert = new Alert(AlertType.NONE);
    private Image imgUser1 = new Image("user/images/MaleLama.png");
    private Image imgUser2 = new Image("user/images/MaleLama.png");
    private List<Button> buttonList = List.of(choiseMaleUser1, choiseFemaleUser1,
                                                choiseMaleUser2, choiseFemaleUser2);

    @FXML
    void initialize() {
        final Image maleImg = new Image("user/images/MaleLama.png", 40, 40, false, false);
        final Image femaleImg = new Image("user/images/FemaleLama.png", 40, 40, false, false);
        final ImageView viewMale1 = new ImageView(maleImg);
        final ImageView viewMale2 = new ImageView(maleImg);
        final ImageView viewFemale1 = new ImageView(femaleImg);
        final ImageView viewFemale2 = new ImageView(femaleImg);

        choiseMaleUser1.setGraphic(viewMale1);
        choiseMaleUser2.setGraphic(viewMale2);
        choiseFemaleUser1.setGraphic(viewFemale1);
        choiseFemaleUser2.setGraphic(viewFemale2);
        update();
    }

    @FXML
    void backToMenu(final ActionEvent event) {
        //TODO 
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/MainMenu.fxml"));
            final Parent root = (Parent) loader.load();
            final Stage stage = new Stage();
            stage.setTitle("MENU");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void startGame(final ActionEvent event) {
        final String namePlayer1 = this.getUserName(userName1);
        final String namePlayer2 = this.getUserName(userName2);
        if (namePlayer1.isEmpty() || namePlayer2.isEmpty()) {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Complete all field");
            alert.show();
            return;
        }
        try {
            final UserController player1 = new UserControllerImpl(namePlayer1, imgUser1);
            final UserController player2 = new UserControllerImpl(namePlayer2, imgUser2);
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Board.fxml"));
            final Parent root = (Parent) loader.load();

            final BoardController boardContrl = loader.getController();
            boardContrl.createPlayers(player1, player2);

            final Stage stage = new Stage();
            stage.setTitle("MATCH");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    void setImageMaleUser1(final ActionEvent event) {
        imgUser1 = new Image("user/images/MaleLama.png");
        update();
    }

    @FXML
    void setImageMaleUser2(final ActionEvent event) {
        imgUser2 = new Image("user/images/MaleLama.png");
        update();
    }

    private String getUserName(final TextField userName) {
        return userName.getText();
    }

    private Image getUserImage(final Image img) {
        return img;
    }

    private void update() {
        buttonList.forEach(x -> x.setDisable(true));
        System.out.println("ciao");
    }
}
