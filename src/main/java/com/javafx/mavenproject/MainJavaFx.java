package com.javafx.mavenproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainJavaFx extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainJavaFx.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting application");

        String fxmlFile = "/fxml/start_window.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing scene");
        Scene scene = new Scene(rootNode, 500, 400);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Start window");
        stage.setScene(scene);
        stage.show();
    }
}
