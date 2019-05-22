package com.javafx.mavenproject.morfologicalTransfLibrary;
import java.awt.image.BufferedImage;

public class OpenAndClose {

    /**
     * @param pixelsTab tablica pikseli
     * @param radius wielkość promienia
     * @return Metoda przeprowadza dylację
     * elementem kwadratowym
     */
    public static BufferedImage dilate(int[][] pixelsTab, int radius) {
        int width = pixelsTab[0].length;
        int height = pixelsTab.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                int rgb = (255 << 16) | (255 << 8) | 255;

                for (int i = -radius; i < radius; i++) {
                    for (int j = -radius; j < radius; j++) {
                        if (i * i + j * j <= radius * radius) {
                            int pixel = pixelsTab[j + y][i + x];
                            int value = (pixel & 0xff);
                            int currentRGB = (value << 16) | (value << 8) | value;

                            if (value < (rgb & 0xff))
                                rgb = currentRGB;
                        }
                    }
                }
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    /**
     * @param pixelsTab tablica pikseli
     * @param radius promień eozji
     * @return Metoda przeprowadza erozję
     * elementem kwadratowym
     */
    public static BufferedImage erode(int[][] pixelsTab, int radius) {
        int width = pixelsTab[0].length;
        int height = pixelsTab.length;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                int rgb = (0 << 16) | (0 << 8) | 0;

                for (int i = -radius; i < radius; i++) {
                    for (int j = -radius; j < radius; j++) {
                        if (i * i + j * j <= radius * radius) {
                            int pixel = pixelsTab[j + y][i + x];
                            int value = (pixel & 0xff);
                            int currentRGB = (value << 16) | (value << 8) | value;

                            if (value > (rgb & 0xff))
                                rgb = currentRGB;
                        }
                    }

                }
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    /**
     * @param pixelsTab tablica pikseli
     * @param radius promień
     * @return Metoda przeprowadza otwarcie
     * elementem kwadratowym
     */
    public static BufferedImage Opening(int[][] pixelsTab, int radius) {
        if (radius <= 0) {
            return null;
        }

        int[][] tmpPixels = ImageUtils.convertTo2DArray(erode(pixelsTab, radius));
        tmpPixels = ImageUtils.convertTo2DArray(dilate(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2 * radius, tmpPixels.length - 2 * radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2 * radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2 * radius; x++) {
                int pixel;

                if (y < 2 * radius || x < 2 * radius)
                    pixel = pixelsTab[y][x];
                else
                    pixel = tmpPixels[y][x];

                int value = (pixel & 0xff);
                rgb = (value << 16) | (value << 8) | value;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    /**
     * @param pixelsTab tablica pikseli
     * @param radius promień
     * @return Metoda przeprowadza zamknięcie
     * elementem kwadratowym
     */
    public static BufferedImage Closing(int[][] pixelsTab, int radius) {
        int[][] tmpPixels = ImageUtils.convertTo2DArray(dilate(pixelsTab, radius));
        tmpPixels = ImageUtils.convertTo2DArray(erode(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2 * radius, tmpPixels.length - 2 * radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2 * radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2 * radius; x++) {
                int pixel;

                if (y < 2 * radius || x < 2 * radius)
                    pixel = pixelsTab[y][x];
                else
                    pixel = tmpPixels[y][x];

                int value = (pixel & 0xff);
                rgb = (value << 16) | (value << 8) | value;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }
}
