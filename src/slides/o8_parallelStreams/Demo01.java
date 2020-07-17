package slides.o8_parallelStreams;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Demo01
{
  public static void main(String[] args)
  {
    long count = IntStream.range(0, 1_000)
        .filter(i -> BigInteger.valueOf(i).isProbablePrime(1000) )
        .count();
    
    System.out.println("Count " + count);
  }
}
