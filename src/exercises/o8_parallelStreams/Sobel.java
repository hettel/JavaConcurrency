package exercises.o8_parallelStreams;

public class Sobel
{
  private static final int[][] HorizontalGradient = {{1, 0, -1}, 
                                                     {2, 0, -2}, 
                                                     {1, 0, -1}};
  
  private static final int[][] VerticalGradient = {{1, 2, 1}, 
                                                   {0, 0, 0}, 
                                                   {-1, -2, -1}};
  
  private static int gradHorizontal(int[][] image, int x, int y)
  {
    int sum = 0;
    for(int i=0; i<3; i++)
    {
      for(int j=0; j<3; j++)
      {
        sum += image[x+1-i][y+1-j]*HorizontalGradient[i][j];
      }
    }
    
    return sum;
  }
  
  private static int gradVertical(int[][] image, int x, int y)
  {
    int sum = 0;
    for(int i=0; i<3; i++)
    {
      for(int j=0; j<3; j++)
      {
        sum += image[x+1-i][y+1-j]*VerticalGradient[i][j];
      }
    }
    
    return sum;
  }
  
  
  public static double meanGradiant(int[][] image, int x, int y)
  {
    int soble_h = gradHorizontal(image, x, y);
    int soble_v = gradVertical(image, x, y);
    
    return Math.sqrt( soble_h*soble_h + soble_v*soble_v ) ;
  }
}
