package com.javafx.mavenproject.morfologicalTransfLibrary;

public class Erode {

    /*-------------------------------------------------------------------------*/
    public int[][] erode(int[][] image, float[][] structure) {

        int half_h = structure.length / 2;
        int half_w = structure[0].length / 2;

        int height = image.length;
        int width = image[0].length;

        int[][] result = new int[height][width];
        int pixel = 0;

        for (int j = half_h; j < height - half_h; j++) {
            for (int i = half_w; i < width - half_w; i++) {

                pixel = 255;
                for (int jj = -half_w; jj <= half_w; jj++) {
                    for (int ii = -half_h; ii <= half_h; ii++) {

                        if (structure[ii + half_h][jj + half_w] == 1) {
                            if (image[j - ii][i - jj] < pixel) {
                                pixel = image[j - ii][i - jj];
                            }
                        }
                    }
                }
                result[j][i] = pixel;
            }
        }

        return result;
    }
    /*-------------------------------------------------------------------------*/

}
