package com.javafx.mavenproject.morfologicalTransfLibrary;

import com.javafx.mavenproject.StartWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Monochrome {

    /**
     * @param bufferedImage wczytane zdjęcie
     * @return Monochromatyzacja zdjęcia
     */
    public static BufferedImage ToMonochrome(BufferedImage bufferedImage) {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = array2D[y][x];

                Color mycolor = new Color(bufferedImage.getRGB(x, y));

                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.6 * (double) red + 0.3 * (double) green + 0.1 * (double) blue); //???
                grayLevel = 255 - grayLevel;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                binaryImage.setRGB(x, y, -gray);
            }

        StartWindow.which = 2;
        return binaryImage;
    }

    /**
     * @param bufferedImage wczytane zdjęcie
     * @return Binaryzacja zdjęcia stałym progiem
     */
    public static BufferedImage ToBinary(BufferedImage bufferedImage) {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = array2D[y][x];

                if ((pixel & 0xff) > 127) {
                    binaryImage.setRGB(x, y, 0xffffff);
                } else
                    binaryImage.setRGB(x, y, 0);
            }
        StartWindow.which = 1;
        return binaryImage;
    }

    /**
     * @param red int red
     * @param green int green
     * @param blue int blue
     * @return Przekształcenie wartości RGB do skali szarości
     */
    private static int rgbToGray(int red, int green, int blue) {
        int r = red;
        int g = green;
        int b = blue;
        int gray;

        gray = Math.round(0.3f * r + 0.6f * g + 0.1f * b);
        System.out.println("Gray   " + gray);
        return gray;
    }
}
