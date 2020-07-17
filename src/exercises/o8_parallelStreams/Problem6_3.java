package exercises.o8_parallelStreams;

import java.io.File;
import java.io.IOException;


public class Problem6_3
{
  public static void main(String[] args) throws IOException
  {
    String inputFileName = "AliceDetector_CERN.jpg";
    String outputFileName = "AliceDetector_CERN_Edges.jpg";
    
    File infile = new File(inputFileName);
    long globalStart = System.currentTimeMillis();
    
    System.out.println("load image " + inputFileName + " and convert it to gray scale");
    long start = System.currentTimeMillis();
    int[][] image = IOUtils.loadImageAndConvertToGrayScale(infile);
    long end = System.currentTimeMillis();
    System.out.println("end loading " + (end - start) + " [ms]" );
   
    
    System.out.println("start process edge detection");
    start = System.currentTimeMillis();
    int[][] newImage = new int[image.length][image[0].length]; 
    for(int i=1; i< image.length-1;i++)
    {
      for(int j=1; j< image[0].length-1;j++)
      {
         double grad = Sobel.meanGradiant(image, i, j);
         int grayValue = Math.min((int) grad,255);
         newImage[i][j] = grayValue < 150 ? 0 : grayValue;
      }
    }
    System.out.println("end process " + (System.currentTimeMillis() - start) + " [ms]" );
  
    System.out.println("store image as " + outputFileName );
    start = System.currentTimeMillis();
    File outfile = new File(outputFileName);
    IOUtils.storeImage(outfile, newImage);
    System.out.println("end store " + (System.currentTimeMillis() - start) + " [ms]" );
     
    System.out.println("done in " + (System.currentTimeMillis() - globalStart) + " [ms]");
  }
}
