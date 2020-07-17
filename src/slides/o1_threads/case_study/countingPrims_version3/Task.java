package slides.o1_threads.case_study.countingPrims_version3;

import java.math.BigInteger;

public class Task implements Runnable
{
  private final NumberBlock numBlock;
  private int end;
  
  public Task(int end, NumberBlock numBlock)
  {
    this.numBlock = numBlock;
    this.end = end;
  }

  @Override
  public void run()
  {
    int count = 0;
    
    long startTime = System.currentTimeMillis(); 
    long candiate = numBlock.getNext();
    while( candiate < this.end )
    {
      BigInteger bInt = BigInteger.valueOf(candiate);
      if( bInt.isProbablePrime(1000) )
      {
        count++;
      }
      candiate = numBlock.getNext();
    }
    long endTime = System.currentTimeMillis(); 
    
    System.out.println("Number of prims " + count + " Duration " + (endTime - startTime) + "[ms]");
  }

}
