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
public final class AbstractController {
    private final Alert alert = new Alert(Alert.AlertType.NONE);

    private void changePage(final Event event, final String string) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource(string));
            buildWindowNodes(event, loader);
        } catch (IOException e) {
            showAlert("unable to open menu", ERROR);
        }
    }

    /**
     * @FXML
     * @param event
     */
    public void backToMenu(final Event event) {
        changePage(event, "/layouts/MainMenu.fxml");
    }
    
    /**
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

    public void openSlide(final Event event) {
        changePage(event, "/layouts/tutorial/1.fxml");
    }

    public void goOn(final Event event) {
        changePage(event, "/layouts/tutorial/2.fxml");
    }


    public void showAlert(final String str, final Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setContentText("");
        alert.setHeaderText(str);
        alert.show();
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
}
