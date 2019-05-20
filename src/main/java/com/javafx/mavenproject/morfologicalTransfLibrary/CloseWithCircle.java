package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.*;

public class CloseWithCircle {
    public BufferedImage image;
    public CloseWithCircle(BufferedImage image)
    {
        this.image = image;
    }

    private double ConvertPixelToDouble(BufferedImage image, int x, int y) {
        Color color = new Color(image.getRGB(x, y));
        double r = color.getRed() * 0.299;
        double g = color.getGreen() * 0.587;
        double b = color.getBlue() * 0.114;
        double sum=r+g+b;
        return sum;
    }

   private BufferedImage erode(BufferedImage image, int radius) {
        int kol;
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = radius; i < image.getHeight() - radius; i++) {
            for (int j = radius; j < image.getWidth() - radius; j++) {
                //if (ConvertPixelToDouble(image, j, i) == 0) {
                int val=(int)ConvertPixelToDouble(image,j,i);
                int kolor=(int)Circle(image,radius,j,i,val,"erode");
                kol=new Color(kolor,kolor,kolor).getRGB();
                img.setRGB(j,i,kol);
            }
        }

        return img;
    }
    private BufferedImage dilate(BufferedImage image, int radius) {

        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        int kol;
        for (int i = radius; i < image.getHeight() - radius; i++) {
            for (int j = radius; j < image.getWidth() - radius; j++) {
                int val=(int)ConvertPixelToDouble(image,j,i);
                int kolor=(int)Circle(image,radius,j,i,val,"dilate");
                kol=new Color(kolor,kolor,kolor).getRGB();
                img.setRGB(j,i,kol);
            }
        }

        return img;
    }
    private double Circle(BufferedImage image, int radius, int x, int y, int val, String str) {
        double zm=val;
        for (int i = y - radius; i < y + radius; i++) {
            for (int j = x; (j - x) * (j - x) + (i - y) * (i - y) <= radius * radius; j--) {
                if(str=="erode") {
                    if (ConvertPixelToDouble(image, j, i) > zm) {
                        zm = ConvertPixelToDouble(image, j, i);
                    }
                }
                else if(str=="dilate")
                {
                    if (ConvertPixelToDouble(image, j, i) < zm) {
                        zm = ConvertPixelToDouble(image, j, i);
                    }
                }
            }
            for (int j = x + 1; (j - x) * (j - x) + (i - y) * (i - y) <= radius * radius; j++) {
                if(str=="erode") {
                    if (ConvertPixelToDouble(image, j, i) > zm) {
                        zm = ConvertPixelToDouble(image, j, i);
                    }
                }
                else if(str=="dilate")
                {
                    if (ConvertPixelToDouble(image, j, i) < zm) {
                        zm = ConvertPixelToDouble(image, j, i);
                    }
                }
            }
        }
        return zm;
    }
    public BufferedImage closing(BufferedImage image, int radius) {
        BufferedImage dilate = dilate(image, radius);
        return erode(dilate,radius);
    }
}
