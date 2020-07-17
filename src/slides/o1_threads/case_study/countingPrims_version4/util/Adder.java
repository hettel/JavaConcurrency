package slides.o1_threads.case_study.countingPrims_version4.util;

public class Adder
{
  private int value;
  
  public Adder()
  {
    this.value = 0;
  }
  
  public synchronized void add(long value)
  {
    this.value += value;
  }
  
  public synchronized int getValue()
  {
    return this.value;
  }
}
