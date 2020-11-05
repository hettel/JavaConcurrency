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

    long startTime = System.currentTimeMillis();

    // area of the complex plane (the area should be a square)
    // this are the values that can be changed to produce different images
    double delta = 1.5;
    double reCenter = -0.5;
    double imCenter = 0.0;

    // calculation of the coordinates
    double reStart = reCenter - delta;
    double reEnd = reCenter + delta;
    double imStart = imCenter - delta;
    double imEnd = imCenter + delta;

    // step sizes for the traversal of the area
    double reStepSize = (reEnd - reStart) / RESOLUTION;
    double imStepSize = (imEnd - imStart) / RESOLUTION;

    // array representing the the considered area
    // A field will contain the number of iteration done
    // for the corresponding value of x + i*y = Re(c) + i*Im(c)
    int[][] area = new int[RESOLUTION][RESOLUTION];

    // initial values (do not change)
    double zRe = 0.0;
    double zIm = 0.0;

    // calculation of the convergence behavior
    double y = imStart;
    for (int j = 0; j < RESOLUTION; j++, y += imStepSize)
    {
      double x = reStart;
      for (int i = 0; i < RESOLUTION; i++, x += reStepSize)
      {

        area[i][j] = iterate(zRe, zIm, x, y, ITERATIONS);
      }
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Elapsed time : " + (endTime - startTime) + "[ms]");

    System.out.println("create and save image");
    plot(area);

    System.out.println("done");
  }

  // Returns the number of iteration until the threshold is reached
  // If the iteration converge the max value of iterations is return
  private static int iterate(double re, double im, double cRe, double cIm, int iterations)
  {
    for (int i = 0; i < iterations; i++)
    {
      double tmpRe = re * re - im * im + cRe;
      double tmpIm = 2.0 * re * im + cIm;
      re = tmpRe;
      im = tmpIm;

      if (sqrt(re * re + im * im) > THRESHOLD)
        return i;
    }

    return iterations;
  }

  // store the array as png-file using the specified color map
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

  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  //
  // Some utilities for the color handling
  //
  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

  // color mapping
  static private final Color colors[] = new Color[ITERATIONS];
  static
  {
    for (int n = 0; n < colors.length; n++)
    {
      colors[n] = new Color((int) (Math.atan(40.0 * n / ITERATIONS) * 255 * 2.0 / Math.PI),
          (int) (Math.atan(140.0 * n / ITERATIONS) * 255 * 2.0 / Math.PI), (int) (Math.atan(160.0 * n / ITERATIONS) * 255 * 2.0 / Math.PI));
    }
  }

  private static int getRgbColor(int value)
  {
    return colors[Math.min(ITERATIONS - 1, value)].getRGB();
  }
}
