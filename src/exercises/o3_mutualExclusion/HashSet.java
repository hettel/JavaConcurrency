package exercises.o3_mutualExclusion;

/**
 * A collection that contains no duplicate elements.  More formally, sets
 * contain no pair of elements {@code e1} and {@code e2} such that
 * {@code e1.equals(e2)}, and at most one null element.  As implied by
 * its name, this interface models the mathematical <i>set</i> abstraction.
 * 
 * <p>An implementation should be backed by a hash table.
 */
public interface HashSet<T>
{
  /**
   * Add the element to the hash set. Returns {@code true} if this hash 
   * set does not contains the element. 
   * 
   * @param element to be added to this hash set
   * @return {@code true} if this hash set does not contains the elements. 
   */
  public boolean add(T element);
  
  /**
   * Removes the element from the hash set. Returns {@code true} if this hash 
   * set contains the element. 
   * 
   * @param element to be removed from this hash set
   * @return {@code true} if this hash set contained the specified element 
   */
  public boolean remove(T element);
  
  /**
   * Returns {@code true} if this hash set contains the specified element. 
   * 
   * @param element to be checked for containment
   * @return {@code true} if this hash set contained the specified element 
   */ 
  public boolean contains(T element);
  
  /**
   * Returns the number of elements in this set. 
   * 
   * @return the number of elements in this set
   */ 
  public int size();
}
