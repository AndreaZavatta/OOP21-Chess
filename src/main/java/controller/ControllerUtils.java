package controller;
import static javafx.scene.control.Alert.AlertType.ERROR;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *
 *
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
     * Opens the main menu screen and closes the current one.
     * @FXML
     * @param event
     */
    public void backToMenu(final Event event) {
        changePage(event, "/layouts/MainMenu.fxml");
    }
    /**
     * Opens the user handler.
     * @FXML
     * @param event
     */
    public void openUserHandler(final Event event) {
        changePage(event, "/layouts/UserHandler.fxml");
    }
    /**
     * @FXML
     * @param event
     */
    public void openTutorial(final Event event) {
        changePage(event, "/layouts/Tutorial.fxml");
    }
    /**
     * @FXML
     * @param event
     */
    public void openStats(final Event event) {
        changePage(event, "/layouts/Stats.fxml");
    }

    /**
     *
     * @param event
     */
    public void openSlide(final Event event) {
        changePage(event, "/layouts/tutorial/1.fxml");
    }

    /**
     *
     * @param event
     */
    public void goOn(final Event event) {
        changePage(event, "/layouts/tutorial/2.fxml");
    }

    /**
     *
     * @param str
     * @param type
     */
    public void showCompleteAlert(final String title, final String header, final String content, final Alert.AlertType type){
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    private void setUpAlert(final String title, final String content, final Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setContentText(content);
    }
    public void showEndGameAlert(final String title, final String content, final Alert.AlertType type) {
        setUpAlert(title, content, type);
    }

    public void showAlert(final String str, final Alert.AlertType type) {
        showCompleteAlert("Message","",str,type);
    }

    /**
     *
     * @param event
     * @param loader
     * @throws IOException
     */
    public void buildWindowNodes(final Event event, final FXMLLoader loader) throws IOException {
        final Parent root = loader.load();
        final Stage stage = new Stage();
        stage.setTitle("L.A.M.A. Chess");
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public Alert getAlert() {
        return alert;
    }
}
