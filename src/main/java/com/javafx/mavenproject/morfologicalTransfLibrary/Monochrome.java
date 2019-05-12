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

        for( int i =1; i <50; i++ )
        {
            for( int j = 1; j <50; j++)
            {

                colorImage.setRGB(i,j,10);
            }
        }
        binaryImage.setRGB(0,0,10);
        /*
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

            }*/
        return binaryImage;

    }
    /*
    private enum MODE {
        LIGHTNESS, AVERAGE, LUMINOSITY
    }
    public void Test(BufferedImage img)
    {
        BufferedImage myColorImage = img;
        BufferedImage myBWImage = new BufferedImage(myColorImage.getWidth(), myColorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < myColorImage.getWidth(); x++)
            for (int y = 0; y < myColorImage.getHeight(); y++)
                if (rgbToGray2(myColorImage.getRGB(x, y), MODE.AVERAGE) > 1)
                    myBWImage.setRGB(x, y, 0);
                else
                    myBWImage.setRGB(x, y, 0xffffff);
    }
    private static int rgbToGray2(int rgb, MODE mode) {
        // split rgb integer into R, G and B components
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int gray;
        // Select mode
        switch (mode) {
            case LIGHTNESS:
                gray = Math.round((Math.max(r, Math.max(g, b)) + Math.min(r, Math.min(g, b))) / 2);
                break;
            case LUMINOSITY:
                gray = Math.round(0.21f * r + 0.72f * g + 0.07f * b);
                break;
            case AVERAGE:
            default:
                gray = Math.round((r + g + b) / 3);
                break;
        }
        return gray;
    }
*/


/*
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
                if (rgbToGray(colorImage.getRGB(x, y)) > 1 )
                {
                    binaryImage.setRGB(x, y, 0);
                }
                else
                    binaryImage.setRGB(x, y, 0xffffff);
            }
        return binaryImage;
    }
    */
    private static int rgbToGray(int rgb)
    {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;

        int gray;

        gray = Math.round(0.3f * r + 0.6f * g + 0.1f * b);
        System.out.println("r,g,b   "+r+" "+g+" "+b );
        System.out.println("Gray   "+gray );

        return gray;
}



}
