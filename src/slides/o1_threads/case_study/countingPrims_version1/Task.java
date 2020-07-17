package slides.o1_threads.case_study.countingPrims_version1;

import java.math.BigInteger;

public class Task implements Runnable
{
  private final int start;
  private final int end;
  
  public Task(int start, int end)
  {
    this.start = start;
    this.end = end;
  }

  @Override
  public void run()
  {
    int count = 0;
       
    long startTime = System.currentTimeMillis(); 
    for( int candidate = this.start; candidate < this.end; candidate++ )
    {
      BigInteger bInt = BigInteger.valueOf(candidate);
      if( bInt.isProbablePrime(1000) )
      {
        count++;
      }
    }
    long endTime = System.currentTimeMillis(); 
    
    System.out.println("Number of prims " + count + ", Task Runtime: " + (endTime - startTime) + "[ms]");
  }
}
