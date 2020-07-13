package case_study.o1_countingPrims.version4.util;

public class Counter
{
  private int value;
  
  public Counter()
  {
    this.value = 0;
  }
  
  public synchronized void increment()
  {
    this.value++;
  }
  
  public synchronized int getValue()
  {
    return this.value;
  }
}
