package com.javafx.mavenproject;

import com.javafx.mavenproject.morfologicalTransfLibrary.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
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
    @FXML private TextField odlX;
    @FXML private TextField odlY;

    @FXML private javafx.scene.control.Button save;

    private int activeButton;
    private BufferedImage bufferedImage;

    @FXML
    public void initialize() {
        try {
            this.bufferedImage = ImageIO.read(StartWindow.obraz);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));

        lockAllClearAll();

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
        String pr = promien.getText();
        String x = odlX.getText();
        String y = odlY.getText();

        switch(activeButton) {
            case 0: //otwarcie
                int[][] pixels = ImageUtils.convertTo2DArray(bufferedImage);
                this.bufferedImage = OpenAndClose.Opening(pixels, Integer.parseInt(pr));

                imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));
                break;
            //---------------------------------------------------------------------------------------------
            case 1: //zamkniecie
                pixels = ImageUtils.convertTo2DArray(bufferedImage);
                this.bufferedImage = OpenAndClose.Closing(pixels, Integer.parseInt(pr));

                imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));
                break;
            //---------------------------------------------------------------------------------------------
            case 2: //monochromatyzacja
                this.bufferedImage = Monochrome.ToMonochrome(this.bufferedImage);
                Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
                imageView.setImage(image);

                reInitialize();
                break;
            //---------------------------------------------------------------------------------------------
            case 3: //erozja
                pixels = ImageUtils.convertTo2DArray(this.bufferedImage);
                this.bufferedImage = OpenAndClose.erode(pixels, Integer.parseInt(pr));

                image = SwingFXUtils.toFXImage(this.bufferedImage, null);
                imageView.setImage(image);
                break;
            //---------------------------------------------------------------------------------------------
            case 4: //dylacja
                try {
                    pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.dilate(pixels, Integer.parseInt(pr));

                    image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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

                    image = SwingFXUtils.toFXImage(op.im, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }

                break;
            //---------------------------------------------------------------------------------------------
            case 11: //mapa odl geodezyjnej
                try {
                    bufferedImage = GeodesicDistance.geodesicDistance(ImageIO.read(StartWindow.obraz), new Point(Integer.parseInt(x), Integer.parseInt(y)));
                    Image imga = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(imga);
                }
                catch(IOException e) {
                   System.out.println(e.getMessage());
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 12: //binaryzacja
                try {
                    this.bufferedImage = Monochrome.ToBinary(ImageIO.read(StartWindow.obraz));
                    image = SwingFXUtils.toFXImage(this.bufferedImage, null);
                    imageView.setImage(image);
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
        lockAllClearAll();
        promien.setDisable(false);
        this.activeButton = 0;
    }

    public void zamkniecieOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        promien.setDisable(false);
        this.activeButton = 1;
    }

    public void monoOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 2;
    }

    public void erozjaOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        promien.setDisable(false);
        this.activeButton = 3;
    }

    public void dylacjaOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        promien.setDisable(false);
        this.activeButton = 4;
    }

    public void normOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 5;
    }

    public void vmfOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 6;
    }

    public void progowanieOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        progbinaryzacji.setDisable(false);
        this.activeButton = 7;
    }

    public void kirchOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 8;
    }

    public void zamkKoloOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 9;
    }

    public void odbicieOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 10;
    }

    public void odlGeoOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        odlX.setDisable(false);
        odlY.setDisable(false);
        this.activeButton = 11;

    }
    public void binaryzacjaOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 12;
    }

    private void lockAllClearAll() {
        progbinaryzacji.setDisable(true);
        lamanaA.setDisable(true);
        lamanaB.setDisable(true);
        lamanaC.setDisable(true);
        oknovmf.setDisable(true);
        promien.setDisable(true);
        odlX.setDisable(true);
        odlY.setDisable(true);

        progbinaryzacji.clear();
        lamanaA.clear();
        lamanaB.clear();
        lamanaC.clear();
        oknovmf.clear();
        promien.clear();
        odlX.clear();
        odlY.clear();
    }

    public void zaladujPonownie(ActionEvent actionEvent) {
        lockAllClearAll();
        try {
            this.bufferedImage = ImageIO.read(StartWindow.obraz);
            imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));
        }
        catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}