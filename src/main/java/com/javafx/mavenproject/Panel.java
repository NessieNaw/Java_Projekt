package com.javafx.mavenproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Panel
{
    @FXML private ImageView imageView;

    public void ShowImage(File obraz)
    {
        Image image = new Image(obraz.toURI().toString());
        //imageView.setImage(image);

        StackPane sp = new StackPane();
        ImageView imgView = new ImageView(image);
        sp.getChildren().add(imgView);

        Scene scene = new Scene(sp);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


}
