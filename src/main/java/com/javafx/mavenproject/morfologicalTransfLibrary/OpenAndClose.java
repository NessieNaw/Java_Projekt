package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class OpenAndClose {
    public BufferedImage image;

    public OpenAndClose(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage dilate(int[][] pixels, int radius) {
            int width = pixels[0].length;
            int height = pixels.length;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            for (int y = radius; y < height - radius; y++) {
                for (int x = radius; x < width - radius; x++) {
                    int rgb = (255 << 16) | (255 << 8) | 255;

                    for (int i = -radius; i < radius; i++) {
                        for (int j = -radius; j < radius; j++) {
                            if (i * i + j * j <= radius * radius) {
                                int pixel = pixels[j + y][i + x];
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

        public BufferedImage erode(int[][] pixels, int radius) {
            int width = pixels[0].length;
            int height = pixels.length;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            ;

            for (int y = radius; y < height - radius; y++) {
                for (int x = radius; x < width - radius; x++) {
                    int rgb = (0 << 16) | (0 << 8) | 0;

                    for (int i = -radius; i < radius; i++) {
                        for (int j = -radius; j < radius; j++) {
                            if (i * i + j * j <= radius * radius) {
                                int pixel = pixels[j + y][i + x];
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

    public BufferedImage Opening(int[][] pixels, int radius) {
        int[][] tmpPixels = convertTo2DArray(erode(pixels, radius));
        tmpPixels = convertTo2DArray(dilate(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2*radius, tmpPixels.length - 2*radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2*radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2*radius; x++) {
                int pixel;

                if (y < 2*radius || x < 2*radius)
                    pixel = pixels[y][x];
                else
                    pixel = tmpPixels[y][x];

                int value = (pixel & 0xff);
                rgb = (value << 16) | (value << 8) | value;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    public BufferedImage Closing(int[][] pixels, int radius) {
        int[][] tmpPixels = convertTo2DArray(dilate(pixels, radius));
        tmpPixels = convertTo2DArray(erode(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2*radius, tmpPixels.length - 2*radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2*radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2*radius; x++) {
                int pixel;

                if (y < 2*radius || x < 2*radius)
                    pixel = pixels[y][x];
                else
                    pixel = tmpPixels[y][x];

                int value = (pixel & 0xff);
                rgb = (value << 16) | (value << 8) | value;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }

    public int[][] convertTo2DArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[][] result = new int[height][width];

        for(int i = 0; i < width; i++)  {
            for(int j = 0; j < height; j++)
                result[i][j] = image.getRGB(i, j);
        }

         return result;
    }
}
