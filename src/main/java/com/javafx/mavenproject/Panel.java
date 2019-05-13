package com.javafx.mavenproject;


import com.javafx.mavenproject.morfologicalTransfLibrary.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    @FXML private javafx.scene.control.Button zamknieciekolowe;
    @FXML private javafx.scene.control.Button odbiciesymetryczne;
    @FXML private javafx.scene.control.Button odlegloscgeodezyjna;
    @FXML private javafx.scene.control.Button binaryzacja;
    @FXML private TextField progbinaryzacji;
    @FXML private TextField lamanaA;
    @FXML private TextField lamanaB;
    @FXML private TextField lamanaC;
    @FXML private TextField oknovmf;
    @FXML private TextField promien;

    @FXML private javafx.scene.control.Button save;

    private int activeButton;
    private BufferedImage bufferedImage;



    @FXML
    public void initialize() {
        imageView.setImage(new Image(StartWindow.obraz.toURI().toString()));
        progbinaryzacji.setDisable(true);
        lamanaA.setDisable(true);
        lamanaB.setDisable(true);
        lamanaC.setDisable(true);
        oknovmf.setDisable(true);
        promien.setDisable(true);
        if(which==1)
        {
            System.out.printf("Opcja logiczna");
            monochromatyzacja.setDisable(true);
            normalizacja.setDisable(true);
            vmf.setDisable(true);
            progowanie.setDisable(true);
            kirch.setDisable(true);
            binaryzacja.setDisable(true);
            odbiciesymetryczne.setDisable(true);

        }
        if(which==2)
        {
            System.out.printf("Opcja mono");
            monochromatyzacja.setDisable(true);
            vmf.setDisable(true);
            odlegloscgeodezyjna.setDisable(true);

        }if(which==3)
        {
            System.out.printf("Opcja kolorowa");
            otwarcie.setDisable(true);
            zamkniecie.setDisable(true);
            erozja.setDisable(true);
            dylacja.setDisable(true);
            zamknieciekolowe.setDisable(true);
            odlegloscgeodezyjna.setDisable(true);
        }

    }

    public  void reInitialize()
    {
        if(which==1)
        {
            monochromatyzacja.setDisable(true);
            normalizacja.setDisable(false);
            vmf.setDisable(false);
            progowanie.setDisable(false);
            kirch.setDisable(false);
            binaryzacja.setDisable(true);
            odbiciesymetryczne.setDisable(false);
            erozja.setDisable(false);
            dylacja.setDisable(false);
            otwarcie.setDisable(false);
            zamkniecie.setDisable(false);
            odlegloscgeodezyjna.setDisable(false);
        }
        if(which==2)
        {
            monochromatyzacja.setDisable(true);
            vmf.setDisable(true);
            odlegloscgeodezyjna.setDisable(false);
            binaryzacja.setDisable(false);
            progowanie.setDisable(false);

        }
        Image ima = imageView.getImage();
        this.bufferedImage =  SwingFXUtils.fromFXImage(ima, null);

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
    public int a = 0;
    public void saveToFile(Image image)
    {

        File outputFile = new File("D:\\JavaFxImages"+a+".png");
        System.out.println("D:\\JavaFxImages"+a+".png");
        a++;
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void params()
    {
        String progBin = progbinaryzacji.getText();
        String lamA = lamanaA.getText();
        String lamB = lamanaB.getText();
        String lamC = lamanaC.getText();
        String oknoVMF = oknovmf.getText();
        String pr = progbinaryzacji.getText();

        switch(activeButton) {
            case 0: //otwarcie
                System.out.println("In transformations");
                try {
                    int[][] pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.Opening(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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
                    int[][] pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.Closing(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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
                try
                {
                    Image ima = imageView.getImage();
                    BufferedImage im = SwingFXUtils.fromFXImage(ima, null);

                    Monochrome mono = new Monochrome(im);

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    mono.image = mono.ToMonochrome(bufferedImage);

                    Image img  = SwingFXUtils.toFXImage(mono.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                reInitialize();

                break;
            //---------------------------------------------------------------------------------------------
            case 3: //erozja
                try {

                    int[][] pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.erode(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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
                    int[][] pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.dilate(pixels, Integer.parseInt(pr));

                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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
                try
                {
                    Image ima = imageView.getImage();
                    BufferedImage im = SwingFXUtils.fromFXImage(ima, null);

                    Histogram hist = new Histogram(im);

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    int verge = Integer.parseInt(pr);
                    hist.image = hist.MonoWithVerge(bufferedImage, verge);

                    Image img  = SwingFXUtils.toFXImage(hist.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }

                reInitialize();
                break;
            //---------------------------------------------------------------------------------------------
            case 8: //filtracja kircha
                try {
                    bufferedImage = Kirsch.kirschFilter(ImageIO.read(StartWindow.obraz));

                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(img);
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 9: //zamkniecie elem. kołowym
                break;
            //---------------------------------------------------------------------------------------------
            case 10: //odbicie symetryczne
                System.out.println("In transformations");
                try {
                    Symetry op = new Symetry(ImageIO.read(StartWindow.obraz));
                    op.ToSymetric();

                    Image image = SwingFXUtils.toFXImage(op.im, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }

                break;
            //---------------------------------------------------------------------------------------------
            case 11: //mapa odl geodezyjnej
                break;
            //---------------------------------------------------------------------------------------------
            case 12: //binaryzacja
                try {
                    Image ima = imageView.getImage();
                    BufferedImage im = SwingFXUtils.fromFXImage(ima, null);

                    Monochrome mono = new Monochrome(im);

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    mono.image = mono.ToBinary(bufferedImage);

                    Image img  = SwingFXUtils.toFXImage(mono.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                System.out.println(which);
                reInitialize();
                break;
                
        }

    }
    public void save(ActionEvent actionEvent)
    {
        Image ima = imageView.getImage();
        saveToFile(ima);
    }

    public void otwarcieOnClick(ActionEvent actionEvent) {
        promien.setDisable(false);
        this.activeButton = 0;
    }

    public void zamkniecieOnClick(ActionEvent actionEvent) {
        promien.setDisable(false);
        this.activeButton = 1;
    }

    public void monoOnClick(ActionEvent actionEvent)
    {
        this.activeButton = 2;
    }

    public void erozjaOnClick(ActionEvent actionEvent) {
        promien.setDisable(false);
        this.activeButton = 3;
    }

    public void dylacjaOnClick(ActionEvent actionEvent) {
        promien.setDisable(false);
        this.activeButton = 4;
    }

    public void normOnClick(ActionEvent actionEvent) {
        this.activeButton = 5;
    }

    public void vmfOnClick(ActionEvent actionEvent) {
        this.activeButton = 6;
    }

    public void progowanieOnClick(ActionEvent actionEvent)
    {
        progbinaryzacji.setDisable(false);
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
        this.activeButton = 12;
    }
}