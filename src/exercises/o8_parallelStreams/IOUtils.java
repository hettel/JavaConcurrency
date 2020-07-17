package exercises.o8_parallelStreams;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class IOUtils
{
  public static int[][]  loadImageAndConvertToGrayScale(File file) throws IOException
  {
    byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    InputStream iStream = new ByteArrayInputStream(bytes);
    BufferedImage  image = ImageIO.read(iStream);

    int width  = image.getWidth();
    int height = image.getHeight();
    
    int[][] pixelImage = new int[width][height];
    
    for(int x = 0; x < width; x++)
    {
      for(int y = 0; y < height; y++ )
      {
        int color   = image.getRGB(x, y);
        // int alpha   = (color>>24) & 0xff; not used
        int red     = (color>>16) & 0xff;
        int green   = (color>>8) & 0xff;
        int blue    = (color>>0) & 0xff;
               
        int gray = (int) (red + green + blue) / 3;       
        pixelImage[x][y] = gray;
      }
    }

    return pixelImage;
  }
  
  
  public static void storeImage(File file, int[][] pixelImage) throws IOException
  {
    int width  = pixelImage.length;
    int height = pixelImage[0].length;
    
    // BufferedImage.TYPE_INT_RGB ==> no alpha channel
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
    for(int x = 0; x < width; x++)
    {
      for(int y = 0; y < height; y++ )
      {
        int gray = pixelImage[x][y];
        int grayValue =  (gray << 16) + (gray << 8) + gray;
        image.setRGB(x, y,  grayValue);
      }
    }
     
    if( file.exists() )
      file.delete();
    
    file.createNewFile();
    ImageIO.write(image, "jpg", file);
  }
}
