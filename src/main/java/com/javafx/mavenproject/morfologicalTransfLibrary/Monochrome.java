package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Monochrome
{
    public BufferedImage image;

    public Monochrome(BufferedImage image)
    {
        this.image = image;
    }

    public BufferedImage ToMonochrome(int [][] pixelsTab)
    {
        int width = pixelsTab[0].length;
        int height = pixelsTab.length;

        System.out.println( width + "width" + height+"height");
        BufferedImage colorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        BufferedImage binaryImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < colorImage.getWidth(); x++)
            for (int y = 0; y < colorImage.getHeight(); y++)
            {
                Color mycolour = new Color(colorImage.getRGB(x, y));
                int color = colorImage.getRGB(x,y);
                int blue = color & 0xff;
                int green = (color & 0xff00) >> 8;
                int red = (color & 0xff0000) >> 16;
                int alpha = (color & 0xff000000) >>> 24;

                System.out.println(blue +" "+green+" "+red+" "+alpha+" ");

                System.out.println(mycolour + " x, y " + x + " " + y  + " "+ mycolour.getAlpha());

                if (rgbToGray(colorImage.getRGB(x, y)) > 127 )
                {
                    binaryImage.setRGB(x, y, 0);
                }
                else
                    binaryImage.setRGB(x, y, 0xffffff);
            }
        return binaryImage;
    }
    private static int rgbToGray(int rgb)
    {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;

        int gray;

        gray = Math.round(0.3f * r + 0.6f * g + 0.1f * b);
        System.out.println("Gray   "+gray );
        return gray;
}


    public int[][] convertTo2DArray(BufferedImage image) {
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
