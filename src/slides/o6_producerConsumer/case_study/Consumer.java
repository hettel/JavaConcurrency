package slides.o6_producerConsumer.case_study;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable
{
  private final BlockingQueue<Optional<BigInteger>> inQueue;
  private final int numPrecidingFilter;
  
  public Consumer(BlockingQueue<Optional<BigInteger>> inQueue, int numPrecidingFilter )
  {
    this.inQueue  = inQueue;
    this.numPrecidingFilter = numPrecidingFilter;
  }
  
  @Override
  public void run()
  {
    try
    {
      int counter = 0;
      int poisonCount = 0;
      while( true )
      {
        Optional<BigInteger> item = this.inQueue.take();
        if( item.isPresent() )
        {
          counter++;
        }
        else
        {
          poisonCount++;
          if( poisonCount == this.numPrecidingFilter )
          {
            break;
          }
        }
      }
      
      System.out.println("Count prims " + counter );
    }
    catch(InterruptedException exce)
    {
      exce.printStackTrace();
    }
  }
}
