package exercises.o2_executors;

import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Problem2_2
{
  static final int RESOLUTION = 3_000;
  static final int ITERATIONS = 3_000;  
  static private final double THRESHOLD = 2.0;
  
  public static void main(String[] args) throws IOException
  {
    System.out.println("Start image calculation");
    
    //initial values (do not change)
    double zRe = 0.0;
    double zIm = 0.0;

    // area of the compley plane
    // the area should be a square
    double detla = 1.5;
    double reStart = -0.5 - detla; 
    double reEnd   = -0.5 + detla;  
    double imStart = 0.0 - detla; 
    double imEnd   = 0.0 + detla;

    double reStepSize = (reEnd - reStart) / RESOLUTION;
    double imStepSize = (imEnd - imStart) / RESOLUTION;
    int[][] area = new int[RESOLUTION][RESOLUTION];

    // calculation of the convergence behavior 
    double x = reStart;
    for (int i = 0; i < RESOLUTION; i++, x += reStepSize)
    {
      double y = imStart;
      for (int j = 0; j < RESOLUTION; j++, y += imStepSize)
      {
        area[i][j] = iterate(zRe, zIm, x, y, ITERATIONS);
      }
    }

    System.out.println("create and save image");
    plot(area);
    
    System.out.println("done");
  }

  private static int iterate(double re, double im, double cRe, double cIm, int iterations)
  {
    for (int i = 0; i < iterations; i++)
    {
      double tmpRe = re * re - im * im + cRe;
      double tmpIm = 2.0 * re * im + cIm;
      re = tmpRe;
      im = tmpIm;
      
      if( sqrt(re * re + im * im) > THRESHOLD )
        return i;
    }
    
    return iterations;
  }

  //store the array as png-file
  private static void plot(int[][] imageArea) throws IOException
  {
    int width = imageArea.length;
    int height = imageArea.length;

    BufferedImage imageOut = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < width; i++)
    {
      for (int j = 0; j < height; j++)
      {
        int rgb = getRgbColor(imageArea[i][j]);
        imageOut.setRGB(i, j, rgb);
      }
    }

    File file = new File("Mandelbrot.png");
    ImageIO.write(imageOut, "png", file);
  }
  
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  //
  // Some utilities for the color handling
  //
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  
  // color mapping
  static private final  Color colors[] = new Color[ITERATIONS];
  static
  {
    for (int n = 0; n < colors.length; n++) {
        colors[n] =  new Color(  (int)(Math.atan(40.0*n/ITERATIONS) * 255*2.0/Math.PI ), 
                                 (int)(Math.atan(140.0*n/ITERATIONS) * 255*2.0/Math.PI ), 
                                 (int)(Math.atan(160.0*n/ITERATIONS) * 255*2.0/Math.PI ) );
    }
  }

  private static int getRgbColor(int value)
  {    
    return  colors[ Math.min(ITERATIONS-1,value) ].getRGB();
  }
}
