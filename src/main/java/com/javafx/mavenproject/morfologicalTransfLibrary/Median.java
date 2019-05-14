package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Median {

    public static BufferedImage medianFilter(BufferedImage bufferedImage, int windowWidth, int windowHeight) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[] window = new int[windowHeight * windowWidth];
        int[][] image = ImageUtils.convertTo2DArray(bufferedImage);

        int edgeX = windowWidth / 2;
        int edgeY = windowHeight / 2;

        for(int x=edgeX; x<width-edgeX-1; x++) {
            for(int y=edgeY; y<height-edgeY-1; y++) {
                int i=0;
                for(int fx=0; fx<windowWidth;fx++) {
                    for(int fy=0; fy<windowHeight; fy++) {
                        window[i] = image[y+fy-edgeY][x+fx-edgeX];
                        i++;
                    }
                    Arrays.sort(window);
                    result.setRGB(x, y, window[(windowWidth * windowHeight)/2]);
                }
            }
        }
        return result;
    }
}
