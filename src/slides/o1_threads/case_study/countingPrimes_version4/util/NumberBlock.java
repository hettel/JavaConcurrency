package slides.o1_threads.case_study.countingPrimes_version4.util;

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
