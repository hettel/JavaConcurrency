package slides.o8_parallelStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Demo10
{
  static class ConcurrentDistinctCollector<E> implements Collector<E, Set<E>, List<E>>
  {
    @Override
    public Supplier<Set<E>> supplier()
    {
      return () -> new ConcurrentSkipListSet<E>();
    }

    @Override
    public BiConsumer<Set<E>, E> accumulator()
    {
      return (set, element) -> set.add(element);
    }

    @Override
    public BinaryOperator<Set<E>> combiner()
    {
      return (setLeft, setRight) -> {
        System.err.println("combiner should not be called with Characteristics.CONCURRENT");
        setLeft.addAll(setRight);
        return setLeft;
      };
    }

    @Override
    public Function<Set<E>, List<E>> finisher()
    {
      return set -> new ArrayList<>(set);
    }

    @Override
    public Set<Characteristics> characteristics()
    {
      return Set.of(Characteristics.UNORDERED, Characteristics.CONCURRENT);
    }
  }

  public static void main(String[] args)
  {
    List<Integer> distinctRandomList =
        IntStream.range(0, 1000)
                 .parallel()
                 .map( i -> ThreadLocalRandom.current().nextInt(1000))
                 .mapToObj( i -> Integer.valueOf(i) )
                 .collect( new ConcurrentDistinctCollector<>() );

        
    System.out.println("Count " + distinctRandomList.size() );

  }

}
