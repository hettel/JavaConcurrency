package slides.o4_synchronization;

public interface LockFreeStack<T>
{
  public void push(T item);
  public T pop();
}
