package slides.o6_producerConsumer.case_study;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

public class Filter implements Runnable
{
  private final BlockingQueue<Optional<BigInteger>> outQueue;
  private final BlockingQueue<Optional<BigInteger>> inQueue;
  
  public Filter(BlockingQueue<Optional<BigInteger>> inQueue, BlockingQueue<Optional<BigInteger>> outQueue )
  {
    this.outQueue = outQueue;
    this.inQueue  = inQueue;
  }
  
  @Override
  public void run()
  {
    try
    {
      while( true )
      {
        Optional<BigInteger> item = this.inQueue.take();
        
        if( item.isPresent() )
        {
          if( item.get().isProbablePrime(1000) )
          {
            this.outQueue.put(item);
          }
        }
        else
        {
          // send poison pill and terminate
          this.outQueue.put(item);
          break;
        }
      }
    }
    catch(InterruptedException exce)
    {
      exce.printStackTrace();
    }
  }
}
