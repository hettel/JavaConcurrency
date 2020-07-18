package slides.o6_producerConsumer.case_study;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main
{
  public static void main(String[] args) throws InterruptedException
  {
    BlockingQueue<Optional<BigInteger>> numQueue = new ArrayBlockingQueue<>(100);
    BlockingQueue<Optional<BigInteger>> primQueue = new ArrayBlockingQueue<>(100);
    
    Producer producer = new Producer(1_000_000L, 2_000_000L, numQueue, 2);
    Filter   filter1  = new Filter(numQueue, primQueue);
    Filter   filter2  = new Filter(numQueue, primQueue);
    Consumer cosnumer = new Consumer(primQueue, 2);
    
    System.out.println("Start counting");
    long startTime = System.currentTimeMillis();
    Thread th1 = new Thread( producer );
    Thread th2 = new Thread( filter1 );
    Thread th3 = new Thread( filter2 );
    Thread th4 = new Thread( cosnumer );
    
    th1.start(); th2.start(); th3.start(); th4.start();
    th1.join(); th2.join(); th3.join(); th4.join();
    
    long endTime = System.currentTimeMillis();
    System.out.println("Elapsed Time " + (endTime - startTime) + "[ms]");
  }
}
