package slides.o9_completableFuture.case_study;

public enum OrderState
{
  Approved, Rejected;
  
  public static OrderState check(OrderState ...states)
  {   
    for( OrderState state : states )
    {
      if( state == Rejected )
        return Rejected;
    }
    
    return Approved;
  }
}
