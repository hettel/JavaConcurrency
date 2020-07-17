package slides.o8_parallelStreams.case_study.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;



public class ExtractColorValues
{
  public static void main(String[] args) throws Exception
  {    
    System.out.println("Open File");
    Image image = IOTools.load("dany.jpg");
 
    
    System.out.println("Amount of pixels " + image.pixels.size() );
    
    System.out.println("Transform Content");
    List<String> pixels = image.pixels.stream().map( dp -> String.format("%3d %3d %3d", dp.red, dp.green, dp.blue )).distinct().collect( Collectors.toList() );
    
    System.out.println("Write File");
    Path outpath = Paths.get("C:\\scratch", "pixel.txt");
    Files.write(outpath, pixels, StandardOpenOption.CREATE,  StandardOpenOption.TRUNCATE_EXISTING);
  }
}
