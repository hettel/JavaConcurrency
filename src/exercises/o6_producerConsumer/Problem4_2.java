package exercises.o6_producerConsumer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Problem4_2
{
  static class DataTransferObject
  {
    private String fileName;
    private BufferedImage image;
  }
  
  public static void main(String[] args) throws IOException
  {
    // Step 1
    // read all png-files from the actual directory
    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.png");
    Path root = Paths.get("");
    System.out.println("Reading files from " + root.toAbsolutePath()  );
    
    Stream<Path> list = Files.list(root);  
    List<File> files = list.filter( p -> matcher.matches(p))
                           .map( p -> p.toFile() )
                           .collect( Collectors.toList() );
    list.close();
     
    List<DataTransferObject> data = new ArrayList<>();
    for(File file : files)
    {
      DataTransferObject dataItem = new DataTransferObject();
      dataItem.fileName = file.getName();
      dataItem.image = ImageIO.read(file);
      data.add(dataItem);
    }
    System.out.println( data.size() + " files read" );
    
    
    
    // Step 2
    // compress and save images
    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
    ImageWriter writer = (ImageWriter) writers.next();
    ImageWriteParam param = writer.getDefaultWriteParam();
    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    // Change the quality value (0.0 to 1.0) you prefer
    param.setCompressionQuality(0.3f); 
    
    for(DataTransferObject dataItem :  data )
    {
      String fileName = dataItem.fileName.replace(".png", ".jpg");
      File compressedImageFile = new File("Compressed_" + fileName);
      OutputStream os = new FileOutputStream(compressedImageFile);
      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);
      writer.write(null, new IIOImage(dataItem.image, null, null), param);
      os.close();
      ios.close();
    }
    
    writer.dispose();

    System.out.println("done");
  }
}
