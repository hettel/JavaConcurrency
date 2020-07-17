package slides.o8_parallelStreams;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Demo05
{

  public static void main(String[] args)
  {
    List<Integer> primes = IntStream.range(0, 1_000)
        .filter(i -> BigInteger.valueOf(i).isProbablePrime(1000) )
        .collect( () -> new ArrayList<Integer>(),  // supplier
                  (l,i) -> l.add(i),               // accumulator
                  (l,r) -> l.addAll(r)             // combiner
                );

    System.out.println("Primes " + primes.size() );
  }

}
