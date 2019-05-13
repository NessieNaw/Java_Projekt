package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.image.BufferedImage;

public class Kirsch {
    public static int[][][] kirschMask = {
            {{-3, -3, 5},
                    {-3, 0, 5},
                    {-3, -3, 5}},
            {{-3, 5, 5},
                    {-3, 0, 5},
                    {-3, -3, -3}},
            {{5, 5, 5},
                    {-3, 0, -3},
                    {-3, -3, -3}},
            {{5, 5, -3},
                    {5, 0, -3},
                    {-3, -3, -3}},
            {{5, -3, -3},
                    {5, 0, -3},
                    {5, -3, -3}},
            {{-3, -3, -3},
                    {5, 0, -3},
                    {5, 5, -3}},
            {{-3, -3, -3},
                    {-3, 0, -3},
                    {5, 5, 5}},
            {{-3, -3, -3},
                    {-3, 0, 5},
                    {-3, 5, 5}},};

    public static int[][] appalyKirschMask(int[][] array2D, int mask[][]) {
        int height = array2D.length;
        int width = array2D[0].length;

        int[][] out = new int[height - 2][width - 2];

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int sum = 0;

                for (int ki = -1; ki < 2; ki++) {
                    for (int kj = -1; kj < 2; kj++) {
                        sum += (array2D[i + ki][j + kj] * mask[ki + 1][kj + 1]);
                    }
                }

                out[i - 1][j - 1] = sum;
            }
        }

        return out;
    }

    public static BufferedImage kirschFilter(BufferedImage bufferedImage) {
        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        int[][][] res = new int[8][bufferedImage.getWidth()][bufferedImage.getHeight()];
        for(int i=0; i<8; i++)
            res[i] = appalyKirschMask(array2D, kirschMask[i]);

        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int i =0; i<8; i++) {
            for (int x = 0; x < bufferedImage.getWidth() - 2; x++)
                for (int y = 0; y < bufferedImage.getHeight() - 2; y++) {
                    if (result.getRGB(x, y) > res[i][y][x])
                        result.setRGB(x, y, res[i][y][x]);
                }
        }
        return result;
    }
}
