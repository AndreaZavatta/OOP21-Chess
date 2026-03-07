package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import application.Start;
import controller.user.UserController;
import controller.user.UserControllerImpl;
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

/**
 * 
 * It manage the selection of the name and image of the two users before
 * starting the game.
 *
 */
public class UserHandlerController {

	private static final String MALE_PATH = "user" + Start.SEPARATOR + "images" + Start.SEPARATOR + "MaleLama.png";
	private static final String FEMALE_PATH = "user" + Start.SEPARATOR + "images" + Start.SEPARATOR + "FemaleLama.png";
	private static final String BOT_PATH = "user" + Start.SEPARATOR + "images" + Start.SEPARATOR + "MaleLama.png"; // Reusing
																													// MaleLama
																													// as
																													// bot
																													// representation
																													// for
																													// now

	@FXML
	private TextField userName1 = new TextField();
	@FXML
	private TextField userName2 = new TextField();
	@FXML
	private Button chooseMaleUser1 = new Button();
	@FXML
	private Button chooseFemaleUser1 = new Button();
	@FXML
	private Button chooseBotUser1 = new Button();
	@FXML
	private Button chooseMaleUser2 = new Button();
	@FXML
	private Button chooseFemaleUser2 = new Button();
	@FXML
	private Button chooseBotUser2 = new Button();
	private final Alert alert = new Alert(AlertType.NONE);
	private Image imgUser1 = new Image(MALE_PATH);
	private Image imgUser2 = new Image(MALE_PATH);
	private boolean isBot1 = false;
	private boolean isBot2 = false;
	private List<Button> buttonList = new LinkedList<>();

	@FXML
	void initialize() {
		final Image maleImg = new Image(MALE_PATH, 40, 40, false, false);
		final Image femaleImg = new Image(FEMALE_PATH, 40, 40, false, false);
		final ImageView viewMale1 = new ImageView(maleImg);
		final ImageView viewMale2 = new ImageView(maleImg);
		final ImageView viewFemale1 = new ImageView(femaleImg);
		final ImageView viewFemale2 = new ImageView(femaleImg);

		chooseMaleUser1.setGraphic(viewMale1);
		chooseMaleUser2.setGraphic(viewMale2);
		chooseFemaleUser1.setGraphic(viewFemale1);
		chooseFemaleUser2.setGraphic(viewFemale2);
		// Assuming we render viewBot using maleImg for now
		final ImageView viewBot1 = new ImageView(new Image(BOT_PATH, 40, 40, false, false));
		final ImageView viewBot2 = new ImageView(new Image(BOT_PATH, 40, 40, false, false));
		chooseBotUser1.setGraphic(viewBot1);
		chooseBotUser2.setGraphic(viewBot2);

		buttonList.addAll(List.of(chooseMaleUser1, chooseFemaleUser1, chooseBotUser1, chooseMaleUser2,
				chooseFemaleUser2, chooseBotUser2));
		update(chooseFemaleUser1, chooseBotUser1, chooseMaleUser1);
		update(chooseFemaleUser2, chooseBotUser2, chooseMaleUser2);
	}

	@FXML
	void backToMenu(final ActionEvent event) {
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
			alert.setContentText("");
			alert.setHeaderText("Complete all field");
			alert.show();
			return;
		}
		try {
			final UserController player1 = new UserControllerImpl(namePlayer1, imgUser1);
			final UserController player2 = new UserControllerImpl(namePlayer2, imgUser2);
			final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/Board.fxml"));
			final Parent root = (Parent) loader.load();

			final BoardController boardContrl = loader.getController();
			boardContrl.initializePlayers(player1, player2, isBot1, isBot2);

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
		imgUser1 = new Image(MALE_PATH);
		isBot1 = false;
		update(chooseFemaleUser1, chooseBotUser1, chooseMaleUser1);
	}

	@FXML
	void setImageFemaleUser1(final ActionEvent event) {
		imgUser1 = new Image(FEMALE_PATH);
		isBot1 = false;
		update(chooseMaleUser1, chooseBotUser1, chooseFemaleUser1);
	}

	@FXML
	void setImageBotUser1(final ActionEvent event) {
		imgUser1 = new Image(BOT_PATH);
		isBot1 = true;
		userName1.setText("AI Player");
		update(chooseMaleUser1, chooseFemaleUser1, chooseBotUser1);
	}

	@FXML
	void setImageMaleUser2(final ActionEvent event) {
		imgUser2 = new Image(MALE_PATH);
		isBot2 = false;
		update(chooseFemaleUser2, chooseBotUser2, chooseMaleUser2);
	}

	@FXML
	void setImageFemaleUser2(final ActionEvent event) {
		imgUser2 = new Image(FEMALE_PATH);
		isBot2 = false;
		update(chooseMaleUser2, chooseBotUser2, chooseFemaleUser2);
	}

	@FXML
	void setImageBotUser2(final ActionEvent event) {
		imgUser2 = new Image(BOT_PATH);
		isBot2 = true;
		userName2.setText("AI Player");
		update(chooseMaleUser2, chooseFemaleUser2, chooseBotUser2);
	}

	private String getUserName(final TextField userName) {
		return userName.getText();
	}

	private void update(final Button enableBtn, final Button enableBtn2, final Button disableBtn) {
		enableBtn.setStyle("-fx-background-color: red");
		enableBtn2.setStyle("-fx-background-color: red");
		disableBtn.setStyle("-fx-background-color: brown");
	}

	private void updateBoardController(final BoardController boardContrl, final UserController player1,
			final UserController player2) {
		boardContrl.initializePlayers(player1, player2, isBot1, isBot2);
	}
}
