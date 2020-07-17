package slides.o1_threads.case_study.countingPrims_version2;

import java.math.BigInteger;

public class Task implements Runnable
{
  
  private final int start;
  private final int end;
  private final int step;
  
  public Task(int start, int end, int stepSize)
  {
    this.start = start;
    this.end = end;
    this.step = stepSize;
  }

  @Override
  public void run()
  {
    int count = 0;
       
    long startTime = System.currentTimeMillis(); 
    long candidate = this.start; 
    while( candidate < this.end )
    {
      BigInteger bInt = BigInteger.valueOf(candidate);
      if( bInt.isProbablePrime(1000) )
      {
        count++;
      }
      
      candidate += this.step;
    }
    long endTime = System.currentTimeMillis(); 
    
    System.out.println("Number of prims " + count + " Duration " + (endTime - startTime) + "[ms]");
  }

}
