package exercises.o7_forkJoin;

import java.math.BigDecimal;


public class Problem5_3
{
  public static void main(String[] args)
  {
    // trials
    final int n = 2000;
    // probabilities
    final BigDecimal p = BigDecimal.valueOf(0.4);
    final BigDecimal q = BigDecimal.ONE.subtract(p);
    
    System.out.println("Calculate the probability distribution");
    System.out.printf("n = %4d,  p = %5.4f, q = %5.4f", n, p, q);
    System.out.println();
    
    long startTime = System.currentTimeMillis();
    
    // array for the different values
    // Note the values for k : 0 <= k <= n
    BigDecimal[] series = new BigDecimal[n+1];
    for (int k = 0; k <= n; k++)
    {
      series[k] = binomial(n, k).multiply(p.pow(k).multiply(q.pow(n - k)));
    }
    
    long endTime = System.currentTimeMillis();
    System.out.println("Elapsed time : " + (endTime - startTime) + "[ms]");

    System.out.println("Check sum (should be 1.0)");
    // test if the sum is equal 1.0
    BigDecimal sum = BigDecimal.ZERO;
    for (int k = 0; k <= n; k++)
    {
      sum = sum.add(series[k]);
    }
    
    System.out.println("Sum : " + sum.setScale(3).toPlainString() );
  }

  // calculate the binomial coefficient (n oder k)
  public static BigDecimal binomial(long n, long k)
  {
    assert n >= 0 && k >= 0;
    assert k <= n;
    
    // handle special cases
    if (k == 0 || k == n)
      return BigDecimal.ONE;

    long nk = n - k;
    if (nk < k)
    {
      k = nk;
    }

    BigDecimal result = BigDecimal.valueOf(n);
    for (long i = 1; i < k; i++)
    {
      result = result.multiply(BigDecimal.valueOf(n - i)).divide(BigDecimal.valueOf(i + 1));
    }

    return result;
  }
}
