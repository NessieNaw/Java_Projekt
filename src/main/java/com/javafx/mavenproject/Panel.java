package com.javafx.mavenproject;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;


public class Panel
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
    private File obraz;

    public Panel(File obraz) {
        this.obraz = obraz;
    }

    public Panel() {
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

        System.out.println(lamA+ lamB+ lamC+ oknoVMF+ progBin+ pr );

        switch(activeButton) {
            case 0:
                System.out.println("In transformations");
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\MKT9\\Pictures\\nm2HM.png"));
                    //BufferedImage bufferedImage = ImageIO.read(this.obraz); //tutaj nie działa
                    OpenAndClose op = new OpenAndClose(bufferedImage);
                    System.out.println(bufferedImage.toString());
                    int[][] pixels = op.convertTo2DArray(bufferedImage);
                    bufferedImage = op.Opening(pixels, Integer.parseInt(pr));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(image);
                }
                catch (Exception e) {
                    System.out.println("Cought exception: " + e.getMessage());
                }
                break;
            case 1:
                break;
        }

    }

    public void otwarcieOnClick(ActionEvent actionEvent) {
        this.activeButton = 0;
        System.out.println(this.activeButton);
    }
}

//Active Buttons:
//0 - otwarcie
//1 - zamknięcie
//...