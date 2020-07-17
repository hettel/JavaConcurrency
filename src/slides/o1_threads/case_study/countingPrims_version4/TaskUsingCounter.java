package slides.o1_threads.case_study.countingPrims_version4;

import java.math.BigInteger;

import slides.o1_threads.case_study.countingPrims_version4.util.Counter;
import slides.o1_threads.case_study.countingPrims_version4.util.NumberBlock;

public class TaskUsingCounter implements Runnable
{
  private final NumberBlock numBlock;
  private final Counter counter;
  private int end;
  
  public TaskUsingCounter(int end, NumberBlock numBlock, Counter counter)
  {
    this.numBlock = numBlock;
    this.counter = counter;
    this.end = end;
  }

  @Override
  public void run()
  {
    long startTime = System.currentTimeMillis(); 
    long candiate = numBlock.getNext();
    while( candiate < this.end )
    {
      BigInteger bInt = BigInteger.valueOf(candiate);
      if( bInt.isProbablePrime(1000) )
      {
        this.counter.increment();
      }
      candiate = numBlock.getNext();
    }
    long endTime = System.currentTimeMillis(); 
    
    System.out.println(" Duration " + (endTime - startTime) + "[ms]");
  }

}
