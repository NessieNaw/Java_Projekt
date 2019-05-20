package com.javafx.mavenproject;

import com.javafx.mavenproject.morfologicalTransfLibrary.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends StartWindow
{

    public javafx.scene.control.Label label_go;
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
    @FXML private TextField ax;
    @FXML private TextField ay;
    @FXML private TextField bx;
    @FXML private TextField by;
    @FXML private TextField cx;
    @FXML private TextField cy;

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
           // zamknieciekolowe.setDisable(true);

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

    private void reInitialize()
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

    private int a = 0;
    private void saveToFile(Image image)
    {

        File outputFile = new File("JavaFxImages"+a+".png");
        //System.out.println("D:\\JavaFxImages"+a+".png");
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
        String axx = ax.getText();
        String ayy = ay.getText();
        String bxx = bx.getText();
        String byy = by.getText();
        String cxx = cx.getText();
        String cyy = cy.getText();
        String oknoVMF = oknovmf.getText();
        String pr = promien.getText();
        String x = odlX.getText();
        String y = odlY.getText();

        switch(activeButton) {
            case 0: //otwarcie
                try {
                    int[][] pixels = ImageUtils.convertTo2DArray(ImageIO.read(StartWindow.obraz));
                    this.bufferedImage = OpenAndClose.Opening(pixels, Integer.parseInt(pr));

                    imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));
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

                    imageView.setImage(SwingFXUtils.toFXImage(this.bufferedImage, null));
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
                    this.bufferedImage = Monochrome.ToMonochrome(ImageIO.read(StartWindow.obraz));
                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Radius cannot be <= 0!");
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
                try
                {
                    Image ima = imageView.getImage();
                    BufferedImage im = SwingFXUtils.fromFXImage(ima, null);

                    Norm norm = new Norm(im);

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    int ax2 = Integer.parseInt(axx);
                    int ay2 = Integer.parseInt(ayy);
                    int bx2 = Integer.parseInt(bxx);
                    int by2 = Integer.parseInt(byy);
                    int cx2 = Integer.parseInt(cxx);
                    int cy2 = Integer.parseInt(cyy);


                    norm.image = norm.Normalize(bufferedImage,ax2,ay2,bx2,by2,cx2,cy2);

                    Image img  = SwingFXUtils.toFXImage(norm.image, null);
                    imageView.setImage(img);

                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }

                reInitialize();


                break;
            //---------------------------------------------------------------------------------------------
            case 6: //VMF
                try {
                    this.bufferedImage = VMF.vmfFilter(ImageIO.read(StartWindow.obraz), Integer.parseInt(x), Integer.parseInt(y));

                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
                    imageView.setImage(image);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
                break;
            //---------------------------------------------------------------------------------------------
            case 7: //progowanie
                try
                {
                    Image ima = imageView.getImage();
                    BufferedImage im = SwingFXUtils.fromFXImage(ima, null);

                    Histogram hist = new Histogram(im);

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);

                    int verge = Integer.parseInt(progBin);
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
                try {
                    CloseWithCircle withcircle = new CloseWithCircle(ImageIO.read(StartWindow.obraz));

                    BufferedImage bufferedImage = ImageIO.read(StartWindow.obraz);
                    withcircle.image = withcircle.closing(bufferedImage,Integer.parseInt(pr) );
                    Image img = SwingFXUtils.toFXImage(withcircle.image, null);
                    imageView.setImage(img);
                }
                catch (IOException e) {
                    System.out.println("Caught exception: " + e.getMessage());
                }
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
                    Image image = SwingFXUtils.toFXImage(this.bufferedImage, null);
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
        ax.setDisable(false);
        ay.setDisable(false);
        bx.setDisable(false);
        by.setDisable(false);
        cx.setDisable(false);
        cy.setDisable(false);
        this.activeButton = 5;
    }

    public void vmfOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        odlX.setDisable(false);
        odlY.setDisable(false);
        this.label_go.setText("wielkosc okna");
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
        promien.setDisable(false);
        this.activeButton = 9;
    }

    public void odbicieOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.activeButton = 10;
    }

    public void odlGeoOnClick(ActionEvent actionEvent) {
        lockAllClearAll();
        this.label_go.setText("odl. geodezyjna");
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
        ax.setDisable(true);
        ay.setDisable(true);
        bx.setDisable(true);
        by.setDisable(true);
        cx.setDisable(true);
        cy.setDisable(true);
        oknovmf.setDisable(true);
        promien.setDisable(true);
        odlX.setDisable(true);
        odlY.setDisable(true);

        progbinaryzacji.clear();
        ax.setDisable(true);
        ay.setDisable(true);
        bx.setDisable(true);
        by.setDisable(true);
        cx.setDisable(true);
        cy.setDisable(true);
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