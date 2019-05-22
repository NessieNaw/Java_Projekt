package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.image.BufferedImage;
import static com.javafx.mavenproject.morfologicalTransfLibrary.ImageUtils.convertFromRGBtoInt;

public class Kirsch {
    private static int[][][] kirschMask = { //8 masek nakładanych na zdjęcie
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

    /**
     * @param bufferedImage wczytane zdjęcie
     * @param mask maska, którą nakładamy, wzięta z 'kirschMask'
     * @return Nakłada odpowiednią maskę
     * Wywoływania w kirschFilter() 8 razy - dla każdej maski osobno
     */
    private static int[][] appalyKirschMask(BufferedImage bufferedImage, int mask[][]) {
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        int[][] out = new int[height - 2][width - 2];

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int red = 0;
                int blue = 0;
                int green = 0;

                for (int ki = -1; ki < 2; ki++) {
                    for (int kj = -1; kj < 2; kj++) {
                        red += (bufferedImage.getRGB(j + kj, i + ki) >> 16 & 0xff) * mask[ki + 1][kj + 1];
                        green += (bufferedImage.getRGB(j + kj, i + ki) >> 8 & 0xff) * mask[ki + 1][kj + 1];
                        blue += (bufferedImage.getRGB(j + kj, i + ki) & 0xff) * mask[ki + 1][kj + 1];
                    }
                }
                out[i - 1][j - 1] = convertFromRGBtoInt(red, green, blue);
            }
        }

        return out;
    }

    /**
     * @param bufferedImage wczytane zdjęcie
     * @return Główna metoda tworząca 8 obrazów wynikowych, przechowywanych w tablicy res
     * w zależności od ustawienej wartości, do zdjęcia wynikowego wkładana jest najmniejsza wartość
     * z 8 wynikowych zdjęć
     */
    public static BufferedImage kirschFilter(BufferedImage bufferedImage) {

        int[][][] res = new int[8][bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < 8; i++)
            res[i] = appalyKirschMask(bufferedImage, kirschMask[i]);

        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < 8; i++) {
            for (int x = 0; x < bufferedImage.getWidth() - 2; x++)
                for (int y = 0; y < bufferedImage.getHeight() - 2; y++) {
                    if (result.getRGB(x, y) > res[i][y][x])
                        result.setRGB(x, y, res[i][y][x]);
                }
        }
        return result;
    }
}
