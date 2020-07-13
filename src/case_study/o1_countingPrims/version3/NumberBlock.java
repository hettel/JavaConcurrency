package case_study.o1_countingPrims.version3;

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
