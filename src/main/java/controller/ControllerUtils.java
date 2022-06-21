package controller;
import static javafx.scene.control.Alert.AlertType.ERROR;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * It's a utility class that contains methods that are used by multiple controllers.
 */
public final class ControllerUtils {
    private final Alert alert = new Alert(Alert.AlertType.NONE);

    private void changePage(final Event event, final String string) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource(string));
            buildWindowNodes(event, loader);
        } catch (IOException e) {
            showAlert("unable to open page", ERROR);
        }
    }

    /**
     * When the backToMenu button is clicked, change the page to the MainMenu.fxml page.
     *
     * @param event The event that triggered the method.
     */
    public void backToMenu(final Event event) {
        changePage(event, "/layouts/MainMenu.fxml");
    }

    /**
     * When the user clicks the button, change the page to the UserHandler.fxml page.
     *
     * @param event The event that triggered the method.
     */
    public void openUserHandler(final Event event) {
        changePage(event, "/layouts/UserHandler.fxml");
    }

    /**
     * When the user clicks the tutorial button, change the page to the tutorial page.
     *
     * @param event The event that triggered the method.
     */
    public void openTutorial(final Event event) {
        changePage(event, "/layouts/Tutorial.fxml");
    }

    /**
     * When the user clicks the Stats button, change the page to the Stats page.
     *
     * @param event The event that triggered the method.
     */
    public void openStats(final Event event) {
        changePage(event, "/layouts/Stats.fxml");
    }

    /**
     * When the user clicks the button, change the page to the tutorial page.
     *
     * @param event The event that triggered the method.
     */
    public void openSlide(final Event event) {
        changePage(event, "/layouts/tutorial/1.fxml");
    }

    /**
     * When the user clicks the button, change the page to the next page in the tutorial.
     *
     * @param event The event that triggered the method.
     */
    public void goOn(final Event event) {
        changePage(event, "/layouts/tutorial/2.fxml");
    }

    /**
     * When the user clicks the button, change the page to the castling page.
     *
     * @param event The event that triggered the method.
     */
    public void openCastling(final Event event) {
        changePage(event, "/layouts/tutorial/castling.fxml");
    }

    /**
     * When the user clicks the button, change the page to the endgame.fxml page.
     *
     * @param event The event that triggered the method.
     */
    public void openEndgame(final ActionEvent event) {
        changePage(event, "/layouts/tutorial/endgame.fxml");
    }

    /**
     * When the user clicks the button, change the page to the final slide.
     *
     * @param event The event that triggered the method.
     */
    public void openFinalSlide(final ActionEvent event) {
        changePage(event, "/layouts/tutorial/final.fxml");
    }

    /**
     * This function sets up an alert with a title, content, and type, and then shows it.
     *
     * @param title The title of the alert
     * @param header The header text of the alert.
     * @param content The content of the alert.
     * @param type The type of alert.
     */
    public void showCompleteAlert(final String title, final String header, final String content, final Alert.AlertType type) {
        setUpAlert(title, content, type);
        alert.setHeaderText(header);
        alert.show();
    }

    /**
     * Create a custom alert for the end of the game.
     * @param title the title of the alert.
     * @param content the content of the alert.
     * @param type the type of the alert.
     */
    public void createEndGameAlert(final String title, final String content, final Alert.AlertType type) {
        setUpAlert(title, content, type);
    }

    private void setUpAlert(final String title, final String content, final Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setContentText(content);
    }

    /**
     * It shows an alert with the given message, title, and type.
     *
     * @param str The message you want to display
     * @param type The type of alert.
     */
    public void showAlert(final String str, final Alert.AlertType type) {
        showCompleteAlert("Message", "", str, type);
    }

    /**
     * It takes an event and an FXMLLoader, and it creates a new window with the FXMLLoader's root as the scene.
     *
     * @param event The event that triggered the method.
     * @param loader The FXMLLoader object that is used to load the FXML file.
     * @throws IOException The standard exception when loading a fxml file.
     */
    public void buildWindowNodes(final Event event, final FXMLLoader loader) throws IOException {
        final Parent root = loader.load();
        final Stage stage = new Stage();
        stage.setTitle("L.A.M.A. Chess");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * A standard getter for the alert.
     * @return the alert
     */
    public Alert getAlert() {
        return alert;
    }

    /**
     * Create a button with an image on it.
     *
     * @param path The path to the image file.
     * @return A button with an image.
     */
    public Button createImageButton(final String path) {
        final Button btn = new Button();
        final Image img = new Image(path, 50, 50, false, false);
        final ImageView imgVw = new ImageView(img);
        btn.setGraphic(imgVw);
        return btn;
    }
}
