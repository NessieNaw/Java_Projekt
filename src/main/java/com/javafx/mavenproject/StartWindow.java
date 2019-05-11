package com.javafx.mavenproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    public int chooseFile() throws Exception {
        Stage stage = (Stage) binarny.getScene().getWindow();
        System.out.println(stage);
        Choose(stage);
        openPanel(stage);


        return 0;
    }
    public void openPanel(Stage stage) throws Exception
    {
        String fxmlFile = "/fxml/panel.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        Scene scene = new Scene(rootNode, 700,500);
        scene.getStylesheets().add("/styles/style2.css");

        stage.setTitle("Panel window");
        stage.setScene(scene);
        stage.show();
    }
    public void Choose(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);

    }
    public void Close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
