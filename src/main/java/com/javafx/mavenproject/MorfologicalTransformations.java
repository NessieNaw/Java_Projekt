package com.javafx.mavenproject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class MorfologicalTransformations {
    BufferedImage image;

    public MorfologicalTransformations(Image image) {
        this.image = toBufferedImage(image);
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        return bimage;
    }


}
