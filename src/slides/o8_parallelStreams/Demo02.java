package slides.o8_parallelStreams;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Demo02
{
  public static void main(String[] args)
  {
    IntStream.range(0, 100)
             .filter(i -> BigInteger.valueOf(i).isProbablePrime(1000) )
             .forEach(i -> System.out.println(i)  );
  }
}
