package exercises.o8_parallelStreams;

import java.io.File;
import java.io.IOException;


public class Problem6_3
{
  public static void main(String[] args) throws IOException
  {
    // In- and output files
    String inputFileName  = "AliceDetector_CERN.jpg";
    String outputFileName = "AliceDetector_CERN_Edges.jpg";
        
    long startTime, endTime;
    long globalStart = System.currentTimeMillis();

    System.out.println("load image " + inputFileName + " and convert it to gray scale");
    
    startTime = System.currentTimeMillis();
    File infile = new File(inputFileName);
    int[][] image = IOUtils.loadImageAndConvertToGrayScale(infile);
    endTime = System.currentTimeMillis();
    System.out.println("runtime for loading : " + (endTime - startTime) + " [ms]" );
   
    
    System.out.println("start process edge detection");

    startTime = System.currentTimeMillis();
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
    endTime = System.currentTimeMillis();
    System.out.println("runtime for edge detection : " + (endTime - startTime) + " [ms]" );
  
    
    System.out.println("store image as " + outputFileName );

    startTime = System.currentTimeMillis();
    File outfile = new File(outputFileName);
    IOUtils.storeImage(outfile, newImage);
    endTime = System.currentTimeMillis();
    System.out.println("runtime for saving the image : " + (endTime - startTime) + " [ms]" );
     
    System.out.println("Global elapsed time : " + (System.currentTimeMillis() - globalStart) + " [ms]");
  }
}
