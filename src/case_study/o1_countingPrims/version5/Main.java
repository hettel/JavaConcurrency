package case_study.o1_countingPrims.version5;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Main
{

  public static void main(String[] args)
  {
    System.out.println("Start search");
    long startTime = System.currentTimeMillis(); 
    long count = IntStream.range(1_000_000, 2_000_000)
                          .parallel()
                          .mapToObj( BigInteger::valueOf )
                          .filter( bInt -> bInt.isProbablePrime(1000) )
                          .count();
    
    long endTime = System.currentTimeMillis();
    
    System.out.println("Duration " + (endTime - startTime) + " [ms]");
    System.out.println("Count " + count );
  }
}
