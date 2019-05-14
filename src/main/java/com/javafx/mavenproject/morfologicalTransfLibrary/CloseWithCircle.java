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

    public double IsLogical(BufferedImage image, int x, int y) {
        Color color = new Color(image.getRGB(x, y));
        double r = color.getRed() * 0.299;
        double g = color.getGreen() * 0.587;
        double b = color.getBlue() * 0.114;
        double sum=r+g+b;
        return sum;
    }

    public BufferedImage erode(BufferedImage image, int radius) {
        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = radius; i < image.getHeight() - radius; i++) {
            for (int j = radius; j < image.getWidth() - radius; j++) {
                if (IsLogical(image, j, i) == 0) {
                    if (inCircle(image, radius, j, i, 255)) {
                        img.setRGB(j, i, white);
                    }
                    else {
                        img.setRGB(j, i, black);
                    }
                }
                else {
                    img.setRGB(j, i, white);
                }
            }
        }

        return img;
    }
    public BufferedImage dilate(BufferedImage image, int radius) {

        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        for (int i = radius; i < image.getHeight() - radius; i++) {
            for (int j = radius; j < image.getWidth() - radius; j++) {

                if (IsLogical(image, j, i) == 255) {
                    if (inCircle(image, radius, j, i, 0)) {
                        img.setRGB(j, i, black);
                      //  System.out.printf("1");
                    }
                    else {
                        img.setRGB(j, i, white);
                    //    System.out.printf("0");
                    }
                } else {
                    img.setRGB(j, i, black);
                  //  System.out.printf("2 ");
                }
            }
        }

        return img;
    }
    private boolean inCircle(BufferedImage image, int radius, int x, int y, int val) {
        for (int i = y - radius; i < y + radius; i++) {
            for (int j = x; (j - x) * (j - x) + (i - y) * (i - y) <= radius * radius; j--) {
                if (IsLogical(image, j, i) == val) {
                    return true;
                }
            }
            for (int j = x + 1; (j - x) * (j - x) + (i - y) * (i - y) <= radius * radius; j++) {
                if (IsLogical(image, j, i) == val) {
                    return true;
                }
            }
        }
        return false;
    }
    public BufferedImage closing(BufferedImage image, int radius) {
        BufferedImage dilate = dilate(image, radius);
        return erode(dilate,radius);
    }
}
