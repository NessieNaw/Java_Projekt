package com.javafx.mavenproject.morfologicalTransfLibrary;

import com.javafx.mavenproject.StartWindow;
import java.util.Arrays;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Histogram {
    public BufferedImage image;

    public Histogram(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    /**
     * @param bufferedImage wczytane zdjęcie
     * @param verge próg binaryzacji
     * @return Binaryzacja zadanym progriem
     */
    public BufferedImage MonoWithVerge(BufferedImage bufferedImage, int verge) {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = array2D[y][x];
                if ((pixel & 0xff) > verge) {
                    binaryImage.setRGB(x, y, 0xffffff);
                } else
                    binaryImage.setRGB(x, y, 0);
            }
        StartWindow.which = 1;
        return binaryImage;
    }


}
