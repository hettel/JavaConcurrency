package case_study.o1_countingPrims.version4.util;

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
