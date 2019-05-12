package com.javafx.mavenproject;

import com.javafx.mavenproject.morfologicalTransfLibrary.Monochrome;
import com.javafx.mavenproject.morfologicalTransfLibrary.OpenAndClose;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;


public class Panel extends StartWindow
{

    @FXML private ImageView imageView;
    @FXML private javafx.scene.control.Button otwarcie;
    @FXML private javafx.scene.control.Button zamkniecie;
    @FXML private javafx.scene.control.Button monochromatyzacja;
    @FXML private javafx.scene.control.Button erozja;
    @FXML private javafx.scene.control.Button dylacja;
    @FXML private javafx.scene.control.Button normalizacja;
    @FXML private javafx.scene.control.Button vmf;
    @FXML private javafx.scene.control.Button progowanie;
    @FXML private javafx.scene.control.Button kirch;
    @FXML private javafx.scene.control.Button zamknieciesymetryczne;
    @FXML private javafx.scene.control.Button odlegloscgeodezyjna;
    @FXML private javafx.scene.control.Button binaryzacja;
    @FXML private TextField progbinaryzacji;
    @FXML private TextField lamanaA;
    @FXML private TextField lamanaB;
    @FXML private TextField lamanaC;
    @FXML private TextField oknovmf;
    @FXML private TextField promien;

    private int activeButton;

    @FXML
    public void initialize() {
        imageView.setImage(new Image(StartWindow.obraz.toURI().toString()));
    }

    public void ShowImage(File obraz)
    {
        Image image = new Image(obraz.toURI().toString());
        //imageView.setImage(image);// ????????

        StackPane sp = new StackPane();
        ImageView imgView = new ImageView(image);
        sp.getChildren().add(imgView);

        Scene scene = new Scene(sp);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
    public void params()
    {
        String progBin = progbinaryzacji.getText();
        String lamA = lamanaA.getText();
        String lamB = lamanaB.getText();
        String lamC = lamanaC.getText();
        String oknoVMF = oknovmf.getText();
        String pr = promien.getText();

        switch(activeButton) {
            case 0: //otwarcie
                System.out.println("In transformations");
                try {
                    OpenAndClose op = new OpenAndClose(ImageIO.read(StartWindow.obraz));

                    int[][] pixels = op.convertTo2DArray(op.image);
                    op.image = op.Opening(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(op.image, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Radius cannot be <= 0!");
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 1: //zamkniecie
                try {
                    OpenAndClose op = new OpenAndClose(ImageIO.read(StartWindow.obraz));

                    int[][] pixels = op.convertTo2DArray(op.image);
                    op.image = op.Closing(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(op.image, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Radius cannot be <= 0!");
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 2: //monochromatyzacja
                try {
                    Monochrome mono = new Monochrome(ImageIO.read(StartWindow.obraz));

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    mono.image = mono.ToMonochrome(bufferedImage);

                    Image img  = SwingFXUtils.toFXImage(mono.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 3: //erozja
                try {
                    OpenAndClose op = new OpenAndClose(ImageIO.read(StartWindow.obraz));

                    int[][] pixels = op.convertTo2DArray(op.image);
                    op.image = op.erode(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(op.image, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Radius cannot be <= 0!");
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 4: //dylacja
                try {
                    OpenAndClose op = new OpenAndClose(ImageIO.read(StartWindow.obraz));

                    int[][] pixels = op.convertTo2DArray(op.image);
                    op.image = op.dilate(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(op.image, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Radius cannot be <= 0!");
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 5: //normalizacja
               break;
            //---------------------------------------------------------------------------------------------
            case 6: //VMF
                break;
            //---------------------------------------------------------------------------------------------
            case 7: //progowanie
                break;
            //---------------------------------------------------------------------------------------------
            case 8: //filtracja kircha
                break;
            //---------------------------------------------------------------------------------------------
            case 9: //zamkniecie elem. kołowym
                break;
            //---------------------------------------------------------------------------------------------
            case 10: //odbicie symetryczne
                break;
            //---------------------------------------------------------------------------------------------
            case 11: //mapa odl geodezyjnej
                break;
            //--------------------------------------------------------------------------------------------- 
            case 12: //binaryzacja
                try {
                    Monochrome mono = new Monochrome(ImageIO.read(StartWindow.obraz));

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                   // mono.image = mono.ToBinary(bufferedImage);

                    Image img  = SwingFXUtils.toFXImage(mono.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                break;
        }
    }

    public void otwarcieOnClick(ActionEvent actionEvent) {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(true);
        this.activeButton = 0;
    }

    public void zamkniecieOnClick(ActionEvent actionEvent) {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(true);
        this.activeButton = 1;
    }

    public void monoOnClick(ActionEvent actionEvent)
    {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(false);
        this.activeButton = 2;
    }

    public void erozjaOnClick(ActionEvent actionEvent) {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(true);
        this.activeButton = 3;
    }

    public void dylacjaOnClick(ActionEvent actionEvent) {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(true);
        this.activeButton = 4;
    }

    public void normOnClick(ActionEvent actionEvent) {
        this.activeButton = 5;
    }

    public void vmfOnClick(ActionEvent actionEvent) {
        this.activeButton = 6;
    }

    public void progowanieOnClick(ActionEvent actionEvent) {
        this.activeButton = 7;
    }

    public void kirchOnClick(ActionEvent actionEvent) {
        this.activeButton = 8;
    }

    public void zamkKoloOnClick(ActionEvent actionEvent) {
        this.activeButton = 9;
    }

    public void odbicieOnClick(ActionEvent actionEvent) {
        this.activeButton = 10;
    }

    public void odlGeoOnClick(ActionEvent actionEvent)
    {
        this.activeButton = 11;

    }


    public void binaryzacjaOnClick(ActionEvent actionEvent)
    {
        progbinaryzacji.setVisible(false);
        lamanaA.setVisible(false);
        lamanaB.setVisible(false);
        lamanaC.setVisible(false);
        oknovmf.setVisible(false);
        promien.setVisible(false);
        this.activeButton = 12;
    }
}