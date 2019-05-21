package com.javafx.mavenproject.morfologicalTransfLibrary;

import com.javafx.mavenproject.StartWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.javafx.mavenproject.morfologicalTransfLibrary.ImageUtils.convertFromRGBtoInt;
public class Norm extends StartWindow
{
    public BufferedImage image;
    public BufferedImage im;

    public Norm(BufferedImage image)
    {
        this.image = image;
    }

    static int l = 0;
    static int m = 0;
    static int n = 0;
    static int u = 0;

    public static void Check(int axx, int ayy, int bxx, int byy, int cxx, int cyy, int kolor )
    {
        if( kolor <= axx )
        {
            l = 0;
            m = 0;
            n = axx;
            u = ayy;
        }
        if( kolor >= axx+1 && kolor <= bxx )
        {
            m = axx+1;
            n = bxx;
            l = ayy+1;
            u = byy;
        }
        if( kolor >= bxx && kolor < cxx )
        {
            m = bxx+1;
            n = cxx;
            l = byy+1;
            u = cyy;
        }
        if( kolor >= cxx && kolor <= 255 )
        {
            m = cxx+1;
            n = 255;
            l = cyy+1;
            u = 255;
        }

        //System.out.println("m,n,l,u"+' '+m+' '+n+' '+l+' '+u);

    }
    public static BufferedImage Normalize( BufferedImage bufferedImage,int axx, int ayy, int bxx, int byy, int cxx, int cyy )
    {
        BufferedImage normalImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int[][] array2D = ImageUtils.convertTo2DArray(bufferedImage);


        for (int x = 0; x < bufferedImage.getWidth(); x++)
            for (int y = 0; y < bufferedImage.getHeight(); y++)
            {
                int pixel = array2D[y][x];

                Color mycolor = new Color(bufferedImage.getRGB(x, y));

                int red = mycolor.getRed();
                int green = mycolor.getGreen();
                int blue = mycolor.getBlue();

                System.out.println("rgb"+' '+red+' '+green+' '+blue);

                Check(axx,ayy,bxx,byy,cxx,cyy,red);
                // System.out.println("m,n,l,u"+' '+m+' '+n+' '+l+' '+u);
                int newRed = FromPoints(l,m, u, n, red);

                Check(axx,ayy,bxx,byy,cxx,cyy,green);
               // System.out.println("m,n,l,u"+' '+m+' '+n+' '+l+' '+u);
                int newGreen = FromPoints(l,m, u, n, green);

                Check(axx,ayy,bxx,byy,cxx,cyy,blue);
              //  System.out.println("m,n,l,u"+' '+m+' '+n+' '+l+' '+u);
                int newBlue = FromPoints(l,m, u, n, blue);

                int converted = convertFromRGBtoInt(newRed, newGreen, newBlue);
                System.out.println("new rgb"+' '+newRed+' '+newGreen+' '+newBlue);

                System.out.println("converted value "+converted);
                zeruj(l,m,u,n);

                normalImage.setRGB(x,y,converted);

            }


        return normalImage;
    }

    private static void zeruj(int l, int m, int u, int n)
    {
        m=0;
        n=0;
        l=0;
        u=0;
    }
    private static int FromPoints(int l, int m, int u, int n,int kolor )
    {
        return ((l-u)/(m-n))*(kolor-m)+l;
    }


}
