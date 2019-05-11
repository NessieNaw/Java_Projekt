package com.javafx.mavenproject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartWindow
{
    private static final Logger log = LoggerFactory.getLogger(ExampleController.class);
    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.Button binarny;
    @FXML private javafx.scene.control.Button szary;
    @FXML private javafx.scene.control.Button kolorowy;


    public int chooseFile()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();


        return 0;
    }
    public void Choose()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
       // fileChooser.showOpenDialog(stage);
    }
    public void Close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
