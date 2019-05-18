package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeodesicDistance {

    public static BufferedImage geodesicDistance(BufferedImage bufferedImage, Point point) {

        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[][] binary = new int[width][height];
        int[][] dilated = new int[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                binary[j][i] = bufferedImage.getRGB(j,i);

                if (binary[j][i] <= (-16777216))
                    binary[j][i] = 0;
                else
                    binary[j][i] = 1;
            }
        }

        int[][] marker = new int[width][height];

        marker[point.x][point.y] = 1;

        int k = 0;
        while (k < (height + width)*3) {
            marker = dilateArray2DtoGeoMap(marker,dilated);
            marker = logicalAND(marker, binary);
            k++;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.setRGB(j, i, dilated[j][i]);
            }
        }
        return result;
    }

    private static int[][] logicalAND(int[][] marker, int[][] binary) {

        for (int i = 0; i < marker[0].length; i++) {
            for (int j = 0; j < marker.length; j++) {
                if ((marker[j][i] == binary[j][i]) && binary[j][i] == 1 )
                    marker[j][i] = 1;
                else
                    marker[j][i] = 0;
            }
        }
        return marker;
    }

    private static int[][] dilateArray2DtoGeoMap(int[][] marker, int[][] dilated) {
        for (int i = 0; i < marker[0].length; i++ ) {
            for (int j = 0; j < marker.length; j++ ) {
                if (marker[j][i] == 1) {
                    if (j > 0 && marker[j - 1][i] == 0)
                        marker[j - 1][i] = 3;
                    if (i > 0 && marker[j][i - 1] == 0)
                        marker[j][i - 1] = 3;
                    if (j + 1 < marker.length && marker[j + 1][i] == 0)
                        marker[j + 1][i] = 3;
                    if (i + 1 < marker[0].length && marker[j][i + 1] == 0)
                        marker[j][i + 1] = 3;
                }
            }
        }

        for (int i = 0; i < marker[0].length; i++ ) {
            for (int j = 0; j < marker.length; j++ ) {
                if (marker[j][i] == 3) {
                    dilated[j][i] = 233; //w oznaczonym miejscu ustawiamy wartosc koloru (jakąkolwiek)
                    marker[j][i] = 1; //przywracamy poprzednia postac markera
                }
            }
        }

        return marker;
    }
}
