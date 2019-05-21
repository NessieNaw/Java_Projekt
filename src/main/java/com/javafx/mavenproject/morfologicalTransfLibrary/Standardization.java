package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.image.BufferedImage;

import static com.javafx.mavenproject.morfologicalTransfLibrary.ImageUtils.convertFromRGBtoInt;

public class Standardization {
    double x1 = 0.1;
    double x2 = 0.5;
    double x3 = 0.8;
    //y
    double y1 = 0.1;
    double y2 = 0.8;
    double y3 = 0.9;

    public BufferedImage standardizeRGB(BufferedImage bufferedImage, double xInput) {

        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        //ax+b
        //3 przedziały
        //0<x1<x2<x3<1
        double yOutput = 0.0;
        if(xInput < x1) {
            yOutput = calculateInFirstCurve(xInput);
        }
        else if(xInput<x2) {
            yOutput = calculateInSecondCurve(xInput);
        }
        else if(xInput<x3) {
            yOutput = calculateInThirdCurve(xInput);
        }
        else
            return null;

        int red = 0;
        int green = 0;
        int blue = 0;

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for(int i =0; i<width-1; i++) {
            for(int j = 0; j<height-1; j++) {
                red = bufferedImage.getRGB(i, j) >> 16 & 0xff;
                green = bufferedImage.getRGB(i, j)>> 8 & 0xff;
                blue = bufferedImage.getRGB(i, j) & 0xff;

                int newRed = (int)(red*yOutput*255)/255;
                int newBlue = (int)(blue*yOutput*255)/255;
                int newGreen = (int)(green*yOutput*255)/255;

                result.setRGB(i, j, convertFromRGBtoInt(newRed, newGreen, newBlue));
            }
        }

        return result;
    }

    private double calculateInThirdCurve(double xInput) {
        double a = (y2 - y3)/(x2 - x3);
        double b = y3 - x3 * a;
        double yOutput = a * xInput + b;

        return yOutput;
    }

    private double calculateInSecondCurve(double xInput) {
        double a = (y1 - y2)/(x1-x2);
        double b = y2 - x2 * a;
        double yOutput = a * xInput + b;

        return yOutput;
    }

    private double calculateInFirstCurve(double xInput) {
        double a = y1/x1;
        double yOutput = a * xInput;

        return yOutput;
    }
}



