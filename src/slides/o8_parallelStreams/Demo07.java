package slides.o8_parallelStreams;

import java.util.Arrays;

public class Demo07
{

  public static void main(String[] args)
  {
    double  values[] = new double[1_000_001];
    
    double start = -4;
    double step  = 8.0/(values.length-1);
    for(int i=0; i < values.length; i++)
    {
      values[i] = Math.atan( start + i*step );
    }
         
    System.out.println("Sequential Stream: " 
                   + Arrays.stream(values).sum() );

    System.out.println("Parallel Stream: " 
                   +  Arrays.stream(values).parallel().sum() );

  }

}
