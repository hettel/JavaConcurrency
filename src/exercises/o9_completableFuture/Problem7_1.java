package exercises.o9_completableFuture;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;


public class Problem7_1
{
  public static void main(String[] args) throws InterruptedException
  {
    int bitLen = 10;
    
    BigInteger P = BigInteger.probablePrime(bitLen, ThreadLocalRandom.current() );
    BigInteger Q = BigInteger.probablePrime(bitLen, ThreadLocalRandom.current() );
    
    BigInteger N = P.multiply(Q);
    BigInteger Phi = P.subtract(BigInteger.ONE).multiply( Q.subtract(BigInteger.ONE) );
    
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
    
    BigInteger D   = E.modInverse(Phi);
    
    System.out.println("Public Key:");
    System.out.println("N: " + N );
    System.out.println("E: " + E );

    System.out.println("Private Key:");
    System.out.println("P: " + P );
    System.out.println("Q: " + Q );
    System.out.println("D: " + D );
    
    //
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //
    System.out.println("Do some tests:");

    // Test 1
    System.out.println("Result should be 1:");
    System.out.println( E.multiply( D ).mod( Phi ));
    
    // Test 2: encode and decode a number
    BigInteger message = new BigInteger(bitLen, ThreadLocalRandom.current() );
    System.out.println("Message: " + message );
    BigInteger encode = message.modPow( E, N);
    System.out.println("Encode: " + encode );
    BigInteger decode = encode.modPow(D, N);
    System.out.println("Decode: " + decode );
    System.out.println("Result: " + message.equals(decode) );
  }
}
