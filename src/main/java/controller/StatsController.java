package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StatsController {


    @FXML
    private TextArea txtAreaStats = new TextArea();

    public void showStats(){
        txtAreaStats.setText("here the stats");
    }
}
