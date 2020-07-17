package slides.o6_producerConsumer;

import java.util.OptionalInt;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01
{
  public static void main(String[] args)
  {
    BlockingQueue<OptionalInt> queue = new ArrayBlockingQueue<>(10);
    
    Runnable producer = () -> {
       try {
         for(int i=0; i < 1_000; i++) {
           queue.put( OptionalInt.of(i) );
         }
         queue.put( OptionalInt.empty() );
       }catch(InterruptedException e) {}
     };
       
    Runnable consumer = () -> {
       try {
         int sum = 0;
         while(true) {
           OptionalInt oInt = queue.take();
           if( oInt.isEmpty() )
             break;
           else
             sum += oInt.getAsInt();
         }
         System.out.println("Sum: " + sum );
       }catch(InterruptedException e) {}
     };
       
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(producer);
    executor.execute(consumer);
    executor.shutdown();
  }
}
