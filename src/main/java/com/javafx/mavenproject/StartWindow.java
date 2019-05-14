package com.javafx.mavenproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


public class StartWindow
{
    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.Button binarny;
    @FXML private javafx.scene.control.Button szary;
    @FXML private javafx.scene.control.Button kolorowy;

    public static int which = 0;
    static File obraz;

    public int chooseFile() throws Exception
    {
        System.out.println(which);
        Stage stage = (Stage) binarny.getScene().getWindow();
        int c = Choose(stage);
        if( c != 0 )
            openPanel(stage);

        return 0;
    }
    public int bin() throws Exception {
        which = 1;
        chooseFile();
        return which;
    }
    public int sz() throws Exception {
        which = 2;
        chooseFile();
        return which;
    }
    public int kol() throws Exception {
        which = 3;
        chooseFile();
        return which;
    }
    public void openPanel(Stage stage) throws Exception
    {
        String fxmlFile = "/fxml/panel.fxml";
        FXMLLoader loader = new FXMLLoader();

        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        Scene scene = new Scene(rootNode, 708,540);
        scene.getStylesheets().add("/styles/style2.css");

        stage.setTitle("Panel window");
        stage.setScene(scene);
        stage.show();

    }
    public int Choose(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null)
        {
            //stage.display(selectedFile);
            this.obraz = selectedFile;
            System.out.println(obraz.getPath());
            return 1;
        }
        return 0;
    }
    public void Close()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
