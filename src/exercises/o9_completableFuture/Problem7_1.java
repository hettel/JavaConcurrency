package exercises.o9_completableFuture;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;


public class Problem7_1
{
  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Calculate a RSA key (sequentially)");
    
    long startTime = System.currentTimeMillis();
    
    int bitLen = 2048;
    
    // search two prime numbers with given bit length 
    BigInteger P = BigInteger.probablePrime(bitLen, ThreadLocalRandom.current() );
    BigInteger Q = BigInteger.probablePrime(bitLen, ThreadLocalRandom.current() );
    
    // multiply the two primes: N = P*Q
    BigInteger N = P.multiply(Q);
    // Euler number: Phi = (P-1)*(Q-1)
    BigInteger Phi = P.subtract(BigInteger.ONE).multiply( Q.subtract(BigInteger.ONE) );
    
    // Choose random E with gcd(E, Phi) = 1
    BigInteger E = null;
    while( true )
    {
      BigInteger bInt = new BigInteger(bitLen, ThreadLocalRandom.current() );
      if( Phi.gcd(bInt).equals(BigInteger.ONE) )
      {
        E = bInt;
        break;
      }
    }
    
    // calculate the modular inverse of E
    BigInteger D   = E.modInverse(Phi);
    
    long endTime = System.currentTimeMillis();
    System.out.println("Elapsed time : " + (endTime - startTime) + "[ms]");
    
    
    System.out.println();
    System.out.println("Public Key:");
    System.out.println("N: " + N );
    System.out.println("E: " + E );

    System.out.println("Private Key:");
    System.out.println("P: " + P );
    System.out.println("Q: " + Q );
    System.out.println("D: " + D );
    
    // ============================================================================
    //
    // Check the key
    //
    //-----------------------------------------------------------------------------
    System.out.println();
    System.out.println("=== Do some checks ===");

    // Test 1
    System.out.println("Result should be 1: " + E.multiply( D ).mod( Phi ));
    System.out.println( );
    
    // Test 2: encode and decode a random number
    BigInteger message = new BigInteger(bitLen, ThreadLocalRandom.current() );
    System.out.println("Message: " + message );
    BigInteger encode = message.modPow( E, N);
    System.out.println("Encode:  " + encode );
    BigInteger decode = encode.modPow(D, N);
    System.out.println("Decode:  " + decode );
    System.out.println("Message equals Decode :  " + message.equals(decode) );
  }
}
