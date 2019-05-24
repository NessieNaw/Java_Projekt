package com.javafx.mavenproject.morfologicalTransfLibrary;

import com.javafx.mavenproject.StartWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Monochrome {

    /**
     * @param bufferedImage wczytane zdjęcie
     * @return Monochromatyzacja zdjęcia
     */
    public static BufferedImage ToMonochrome(BufferedImage bufferedImage) {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = array2D[y][x];

                Color mycolor = new Color(bufferedImage.getRGB(x, y));

                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.6 * (double) red + 0.3 * (double) green + 0.1 * (double) blue); //???
                grayLevel = 255 - grayLevel;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                binaryImage.setRGB(x, y, -gray);
            }

        StartWindow.which = 2;
        return binaryImage;
    }

    /**
     * @param bufferedImage wczytane zdjęcie
     * @return Binaryzacja zdjęcia stałym progiem
     */
    public  static BufferedImage ToBinary(BufferedImage bufferedImage) {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

      //entropia(histogram(bufferedImage),bufferedImage);
        int prog=entropy(histogram(bufferedImage));
        System.out.println(prog);
        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);
        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = array2D[y][x];

                if ((pixel & 0xff) > prog) {
                    binaryImage.setRGB(x, y, 0xffffff);
                } else
                    binaryImage.setRGB(x, y, 0);
            }
        StartWindow.which = 1;
        return binaryImage;
    }
    /**
    //histogram - ile jest pikseli w danej skali osobno dla r g  b*/
    private static int[] histogram( BufferedImage bufferedImage){
        int[] tab = new int[256];
        for(int i =0; i<256;i++)
        {
            tab[i]=0;
        }

        for(int x=0; x<bufferedImage.getWidth();x++)
        {
            for(int y=0; y<bufferedImage.getHeight();y++)
            {
                int tmp=(int)ConvertPixelToDouble( bufferedImage, x, y);
                tab[tmp]++;
            }
        }
        return tab;
    }
    private static int entropy(int[] histogram) {

        // Normalizacja histogramu
        double sum = 0;
        for (int i = 0; i < histogram.length; ++i) {
            sum += histogram[i];
        }
        if (sum == 0) {
            throw new IllegalArgumentException("Empty histogram: sum==0");
        }
        double[] normalizedHistogram = new double[histogram.length];
        for (int i = 0; i < histogram.length; i++) {
            normalizedHistogram[i] = histogram[i] / sum;
        }
        double[] pT = new double[histogram.length];
        pT[0] = normalizedHistogram[0];
        for (int i = 1; i < histogram.length; i++) {
            pT[i] = pT[i - 1] + normalizedHistogram[i];
        }
        // Entropia dla białych i czarnych elementów
        final double epsilon = Double.MIN_VALUE;
        double[] B = new double[histogram.length];
        double[] W = new double[histogram.length];
        for (int t = 0; t < histogram.length; t++) {
            // Entropia - black
            if (pT[t] > epsilon) {
                double black = 0;
                for (int i = 0; i <= t; i++) {
                    if (normalizedHistogram[i] > epsilon) {
                        black -= normalizedHistogram[i] / pT[t] * Math.log(normalizedHistogram[i] / pT[t]);
                    }
                }
                B[t] = black;
            } else {
                B[t] = 0;
            }

            // Entropia - white
            double pTW = 1 - pT[t];
            if (pTW > epsilon) {
                double white = 0;
                for (int i = t + 1; i < histogram.length; ++i) {
                    if (normalizedHistogram[i] > epsilon) {
                        white -= normalizedHistogram[i] / pTW * Math.log(normalizedHistogram[i] / pTW);
                    }
                }
                W[t] = white;
            } else {
                W[t] = 0;
            }
        }

        // Szukanie Maksymalnej entropii na histogramie
        double jMax = B[0] + W[0];
        int tMax = 0;
        for (int t = 1; t < histogram.length; ++t) {
            double j = B[t] + W[t];
            if (j > jMax) {
                jMax = j;
                tMax = t;
            }
        }

        return tMax;
    }

    /**
     * @param red int red
     * @param green int green
     * @param blue int blue
     * @return Przekształcenie wartości RGB do skali szarości
     */
    private static int rgbToGray(int red, int green, int blue) {
        int r = red;
        int g = green;
        int b = blue;
        int gray;

        gray = Math.round(0.3f * r + 0.6f * g + 0.1f * b);
        System.out.println("Gray   " + gray);
        return gray;
    }
    private static double ConvertPixelToDouble(BufferedImage image, int x, int y) {
        Color color = new Color(image.getRGB(x, y));
        double r = color.getRed() * 0.299;
        double g = color.getGreen() * 0.587;
        double b = color.getBlue() * 0.114;
        double sum = r + g + b;
        return sum;
    }
}
