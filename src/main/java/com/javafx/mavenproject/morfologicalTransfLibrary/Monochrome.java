package com.javafx.mavenproject.morfologicalTransfLibrary;

import com.javafx.mavenproject.StartWindow;
import javafx.scene.image.PixelReader;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Monochrome extends StartWindow
{
    public BufferedImage image;

    public Monochrome(BufferedImage image)
    {
        this.image = image;
    }


    public BufferedImage ToMonochrome( BufferedImage bufferedImage)
    {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);

        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++)
            {
                int pixel = array2D[y][x];

                Color mycolor = new Color(bufferedImage.getRGB(x, y));
               // System.out.println(mycolor);

                int red = ((pixel >> 16) & 0xff);
                int green = ((pixel >> 8) & 0xff);
                int blue = (pixel & 0xff);

                int grayLevel = (int) (0.6 * (double)red + 0.3 * (double)green + 0.1 * (double)blue) ; //???
                grayLevel = 255 - grayLevel;
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

                binaryImage.setRGB(x, y,-gray);
            }


        StartWindow.which = 2;
        return binaryImage;
    }

    public BufferedImage ToBinary( BufferedImage bufferedImage)
    {
        BufferedImage binaryImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);
        
        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++)
            {
                int pixel = array2D[y][x];

                if ((pixel & 0xff) > 127 )
                {
                    binaryImage.setRGB(x, y, 0xffffff);
                }
                else
                    binaryImage.setRGB(x, y, 0);
            }
        StartWindow.which = 1;
        return binaryImage;
    }

    private static int rgbToGray(int red, int green, int blue)
    {
     //   int r = (red >> 16) & 0xff;
       // int g = (green >> 8) & 0xff;
        //int b = blue & 0xff;

        int r= red;
        int g = green;
        int b = blue;
        int gray;

        gray = Math.round(0.3f * r + 0.6f * g + 0.1f * b);
        System.out.println("Gray   "+gray );
        return gray;
}



}
