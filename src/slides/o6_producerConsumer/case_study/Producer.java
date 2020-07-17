package slides.o6_producerConsumer.case_study;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable
{
  private final BlockingQueue<Optional<BigInteger>> outQueue;
  private final long startIndex;
  private final long endIndex;
  private final int numFollowingFilter;
  
  public Producer(long startIndex, long endIndex, BlockingQueue<Optional<BigInteger>> outQueue, int numFollowingFilter )
  {
    this.outQueue = outQueue;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
    this.numFollowingFilter = numFollowingFilter;
  }
  
  @Override
  public void run()
  {
    try
    {
      for(long i = this.startIndex; i < this.endIndex; i++ )
      {
        Optional<BigInteger> item = Optional.of( BigInteger.valueOf(i) );
        this.outQueue.put(item);
      }
      
      // send poison pills 
      for(int i=0; i < this.numFollowingFilter; i++)
      {
        this.outQueue.put( Optional.empty() );
      }
    }
    catch(InterruptedException exce)
    {
      exce.printStackTrace();
    }
  }
}
