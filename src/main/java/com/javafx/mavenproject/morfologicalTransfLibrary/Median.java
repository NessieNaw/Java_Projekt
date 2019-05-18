package com.javafx.mavenproject.morfologicalTransfLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Median {

    public static BufferedImage medianFilter(BufferedImage bufferedImage, int windowWidth, int windowHeight) {
        BufferedImage result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int[] window = new int[windowHeight * windowWidth];
        int[][] image = ImageUtils.convertTo2DArray(bufferedImage);

        int edgeX = windowWidth / 2;
        int edgeY = windowHeight / 2;

        Color[] pixel = new Color[9];

        int[] A=new int[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];

        for(int i=edgeX; i<width-edgeX-1; i++) {
            for(int j=edgeY; j<height-edgeY-1; j++) {
                //int i=0;
                int k = 0;
                for(int fx=0; fx<windowWidth;fx++) {
                    for(int fy=0; fy<windowHeight; fy++) {
                        //window[i] = image[y+fy-edgeY][x+fx-edgeX];
                        //i++;
                /*pixel[0]=new Color(bufferedImage.getRGB(i-1,j-1));
                pixel[1]=new Color(bufferedImage.getRGB(i-1,j));
                pixel[2]=new Color(bufferedImage.getRGB(i-1,j+1));
                pixel[3]=new Color(bufferedImage.getRGB(i,j+1));
                pixel[4]=new Color(bufferedImage.getRGB(i+1,j+1));
                pixel[5]=new Color(bufferedImage.getRGB(i+1,j));
                pixel[6]=new Color(bufferedImage.getRGB(i+1,j-1));
                pixel[7]=new Color(bufferedImage.getRGB(i,j-1));
                pixel[8]=new Color(bufferedImage.getRGB(i,j));
                for(int k=0;k<9;k++){
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                }*/

                A[k] = bufferedImage.getRGB(i + fx-edgeX, j+fy-edgeY) >> 24 & 0xff;
                R[k] =  bufferedImage.getRGB(i + fx-edgeX, j+fy-edgeY) >> 16 & 0xff;
                G[k] = bufferedImage.getRGB(i + fx-edgeX, j+fy-edgeY) >> 8 & 0xff;
                B[k] = bufferedImage.getRGB(i + fx-edgeX, j+fy-edgeY) & 0xff;
                k++;
            }
                    //Arrays.sort(window);
                    //result.setRGB(x, y, window[(windowWidth * windowHeight)/2]);
                    //Arrays.sort(R);
                    //Arrays.sort(G);
                    //Arrays.sort(B);
                    double dist = calculateMinDistance(R, G, B);

                    result.setRGB(i,j, (int)dist);//convertFromRGBtoInt(R[4], G[4], B[4]));
                }
            }
        }
        return result;
    }

    private static int convertFromRGBtoInt(int R, int G, int B) {
        int rgb = R;
        rgb = (rgb << 8) + G;
        rgb = (rgb << 8) + B;

        return rgb;
    }

    //private static double calculateMinDistance(int R0, int G0, int B0, int R, int G, int B) {
    private static double calculateMinDistance(int[] R, int[] G, int[] B) {
        //double distance = Math.sqrt(Math.pow(R0-R, R0-R)+Math.pow(G0-G, G0-G) + Math.pow(B0-B, B0-B));
        double[] distance = new double[R.length];
        //Map<Double, int[]> map = new HashMap<Double, int[]>();
        for(int i = 0; i<R.length; i++) {
            distance[i] = Math.sqrt(Math.pow(R[i]-R[4], R[i]-R[4])+Math.pow(G[i]-G[4], G[i]-G[4]) + Math.pow(B[i]-B[4], B[i]-B[4]));
            //map.put(distance[i], new int[])
        }
        Arrays.sort(distance);
        //System.out.println(distance[0]);

        return distance[1];
        //return convertFromRGBtoInt()

    }

}
