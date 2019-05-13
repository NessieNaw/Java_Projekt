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

        for (int r = 1; r < height - 1; r++) {
            for (int c = 1; c < width - 1; c++) {
                int sum = 0;

                for (int kr = -1; kr < 2; kr++) {
                    for (int kc = -1; kc < 2; kc++) {
                        sum += (array2D[r + kr][c + kc] * mask[kr + 1][kc + 1]);
                    }
                }

                out[r - 1][c - 1] = sum;
            }
        }

        return out;
    }

    public static BufferedImage kirschFilter(BufferedImage bufferedImage) {
        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        int[][] res = appalyKirschMask(array2D, kirschMask[0]); //trzeba zrobić pętlę???

        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < bufferedImage.getWidth()-2; x++)
            for (int y = 0; y < bufferedImage.getHeight()-2; y++) {
                result.setRGB(x, y, res[y][x]);
            }

        return result;
    }
}
