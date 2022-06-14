package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class sets up the GUI for the Main Menu of the application. 
 *
 */
public final class Start extends Application {
    /**
     * 
     */
    public static final String SEPARATOR = System.getProperty("file.separator");
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;

    private final Stage startStage = new Stage();

    /**
     * This method loads the Menu and sets the window up.
     * 
     * @param stage is the loaded stage.
     */
    public void start(final Stage stage) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/MainMenu.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        startStage.setScene(scene);
        startStage.setTitle("L.A.M.A. Chess");
        startStage.setResizable(true);
        startStage.show();
    }

    /**
     * Main method.
     * 
     * @param args ignored
     */
    public static void main(final String[] args) {
        launch();
    }
}
