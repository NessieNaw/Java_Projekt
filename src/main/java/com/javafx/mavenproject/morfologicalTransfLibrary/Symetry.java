package com.javafx.mavenproject.morfologicalTransfLibrary;
import java.awt.image.BufferedImage;

public class Symetry {
    public BufferedImage image;
    public BufferedImage im;
    public Symetry(BufferedImage image)
    {
        this.image = image;
    }
    public  void ToSymetric() {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage mimg = new BufferedImage(width , height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0, sx = width - 1; x < width; x++, sx--) {
                int p = image.getRGB(x, y);
                mimg.setRGB(sx, y, p);

            }
        }

        //save mirror image
        im=mimg;
     //   return mimg;
    }
}
