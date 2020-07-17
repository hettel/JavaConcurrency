package exercises.o3_mutualExclusion;

public interface HashSet<T>
{
  public boolean add(T element);
  public boolean remove(T element);
  public boolean contains(T element);
  public int size();
}
