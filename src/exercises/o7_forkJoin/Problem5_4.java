package exercises.o7_forkJoin;

import java.math.BigDecimal;


public class Problem5_4
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
    
    // array for the different values
    BigDecimal[] series = new BigDecimal[n+1];
    for (int k = 0; k <= n; k++)
    {
      series[k] = binomial(n, k).multiply(p.pow(k).multiply(q.pow(n - k)));
    }

    System.out.println("Check sum");
    // test if the sum is equal 1.0
    BigDecimal sum = BigDecimal.ZERO;
    for (int k = 0; k <= n; k++)
    {
      sum = sum.add(series[k]);
    }
    
    System.out.println("Summe " + sum.setScale(3).toPlainString() );
  }

  public static BigDecimal binomial(long n, long k)
  {
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
