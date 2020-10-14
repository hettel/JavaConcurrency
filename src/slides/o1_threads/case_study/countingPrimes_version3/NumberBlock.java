package slides.o1_threads.case_study.countingPrimes_version3;

public class NumberBlock
{
  private int value;

  public NumberBlock(int startValue)
  {
    this.value = startValue;
  }
  
  public synchronized int getNext()
  {
    return this.value++;
  }
  
}
