package exercises.o6_producerConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * Some remarks
 * 
 * Reading and writing binary files with Java can be easily done 
 * with the nio-classes:
 * 
 *   Path input = Paths.get("...");
 *   byte[] content = Files.readAllBytes(input);
 *   
 *   Path output = Paths.get("...");
 *   Files.write(output, content, StandardOpenOption.CREATE);
 *   
 * The following program use the old style Input- and OutputStream
 * classes.
 * 
 * For pedagogical reason in  a read and  a write cycle only one byte is used.
 * That is not an efficient way because at least a page size should
 * accessed by a read or write.
 * Also a very simple cipher is used (Caesar shift cipher). The generated load 
 * is therefore very low.
 */

public class Problem4_3
{
  static final byte initVector = Byte.parseByte("01100110", 2);
  
  public static void main(String[] args) throws Exception
  {
    encrypt("Cinderella_RoaldDahl.txt", "Cinderella_RoaldDahl.bin");
    decrypt("Cinderella_RoaldDahl.bin", "Cinderella_RoaldDahl2.txt");
     
    System.out.println("done");
  }
  
  private static void encrypt(String inFile, String outFile) throws IOException
  {
    // Input- and OutpitStream are autoclosable
    try(InputStream input   = new FileInputStream( new File( inFile ) );
        OutputStream output = new FileOutputStream( new File( outFile ) ))
    {
      // read buffer
      byte[] block = new byte[1];
      
      int bytesRead = 0;
      byte salt = initVector;
      while (bytesRead != -1)
      {
        bytesRead = input.read(block);
        if (bytesRead > 0)
        {
          // XOR operation
          byte nextValue = (byte) (salt ^ block[0]);

          // encrypt using a simple Caesar cipher (shift 42)
          int intValue = Byte.toUnsignedInt(nextValue);
          intValue = (intValue + 42) % 256;
          byte value = Integer.valueOf(intValue).byteValue();
          salt = value;
          
          output.write(value);
        }
      }
    }
  }
  
  private static void decrypt(String inFile, String outFile) throws IOException
  {
    // Input- and OutpitStream are autoclosable
    try(InputStream input   = new FileInputStream( new File( inFile ) );
        OutputStream output = new FileOutputStream( new File( outFile ) ))
    {
      // read buffer
      byte[] block = new byte[1];
      
      int bytesRead = 0;
      byte salt = initVector;
      while (bytesRead != -1)
      {
        bytesRead = input.read(block);
        if (bytesRead > 0)
        {
          // decrypt the Caesar cipher (shift 42)
          int intValue = Byte.toUnsignedInt(block[0]);
          intValue = (intValue - 42 + 256) % 256;
          byte value = Integer.valueOf(intValue).byteValue();

          // XOR operation
          value = (byte) (salt ^ value);
          output.write(value);
          
          salt = block[0];
        }
      }
    }
  }
}
