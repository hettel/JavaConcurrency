package slides.o8_parallelStreams;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Demo03
{

  public static void main(String[] args)
  {
    long sum = IntStream.range(0, 1_000)
                        .filter(i -> BigInteger.valueOf(i).isProbablePrime(1000) )
                        .sum();
    
    System.out.println("Count " + sum);

    sum = IntStream.range(0, 1_000)
                  .filter(i -> BigInteger.valueOf(i).isProbablePrime(1000) )
                  .reduce(0, (a,b) -> a + b);
    
    System.out.println("Count " + sum);
  }

}
