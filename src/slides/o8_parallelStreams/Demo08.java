package slides.o8_parallelStreams;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo08
{


  public static void main(String[] args)
  {
    List<Integer> distinctRandomList =
        IntStream.range(0, 100)
                 .parallel()
                 .map( i -> ThreadLocalRandom.current().nextInt(1000))
                 .mapToObj( i -> Integer.valueOf(i) )
                 .distinct()
                 .collect( Collectors.toList() );
        
    System.out.println("Count " + distinctRandomList.size() );
  }

}
