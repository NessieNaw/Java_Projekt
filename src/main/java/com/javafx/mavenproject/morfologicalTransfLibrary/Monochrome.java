package com.javafx.mavenproject.morfologicalTransfLibrary;

import javafx.scene.image.PixelReader;

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

    public BufferedImage ToMonochrome( BufferedImage bufferedImage)
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        System.out.println( width + "width" + height+"height");
        BufferedImage colorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        BufferedImage binaryImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

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
                System.out.println(mycolour);
                System.out.println(colorImage.getRaster().getSample(x, y, 0));  //R
                System.out.println(colorImage.getRaster().getSample(x, y, 1));  //G
                System.out.println(colorImage.getRaster().getSample(x, y, 2));  //B


                binaryImage.setRGB(x,y,rgbToGray(colorImage.getRGB(x,y)));

            }
        return binaryImage;

    }

    public BufferedImage ToBinary( BufferedImage bufferedImage)
    {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        System.out.println( width + "width" + height+"height");
        BufferedImage colorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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
                System.out.println(mycolour);
                System.out.println(colorImage.getRaster().getSample(x, y, 0));  //R
                System.out.println(colorImage.getRaster().getSample(x, y, 1));  //G
                System.out.println(colorImage.getRaster().getSample(x, y, 2));  //B


                

                System.out.println(Integer.toString(colorImage.getRGB(x, y), 16));
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



}
