package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class OpenAndClose {
    public BufferedImage image;

    public OpenAndClose(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage dilate(int[][] pixelsTab, int radius) {
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

        public BufferedImage erode(int[][] pixelsTab, int radius) {
            int width = pixelsTab[0].length;
            int height = pixelsTab.length;
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            ;

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

    public BufferedImage Opening(int[][] pixelsTab, int radius) {
        int[][] tmpPixels = convertTo2DArray(erode(pixelsTab, radius));
        tmpPixels = convertTo2DArray(dilate(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2*radius, tmpPixels.length - 2*radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2*radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2*radius; x++) {
                int pixel;

                if (y < 2*radius || x < 2*radius)
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

    public BufferedImage Closing(int[][] pixelsTab, int radius) {
        int[][] tmpPixels = convertTo2DArray(dilate(pixelsTab, radius));
        tmpPixels = convertTo2DArray(erode(tmpPixels, radius));
        BufferedImage img = new BufferedImage(tmpPixels[0].length - 2*radius, tmpPixels.length - 2*radius, BufferedImage.TYPE_3BYTE_BGR);
        int rgb;

        for (int y = 0; y < tmpPixels.length - 2*radius; y++) {
            for (int x = 0; x < tmpPixels[y].length - 2*radius; x++) {
                int pixel;

                if (y < 2*radius || x < 2*radius)
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

    public int[][] convertTo2DArray(BufferedImage image) { //nie jestem w 100% pewna, czy to działa
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int width = image.getWidth();
        int height = image.getHeight();

        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];

        if (isMonochrome(image)) {
            final int pixelLength = 1;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int grey = ((int) pixels[pixel] & 0xff);
                result[row][col] = grey;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
            return result;
        }

        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    public static boolean isMonochrome(BufferedImage image) {
        final int type = image.getColorModel().getColorSpace().getType();
        final boolean isMonochrome = (type == ColorSpace.TYPE_GRAY || type == ColorSpace.CS_GRAY);

        return isMonochrome;
    }
}
