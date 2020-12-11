package slides.o8_parallelStreams.case_study;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import slides.o8_parallelStreams.case_study.util.ColorCentroid;
import slides.o8_parallelStreams.case_study.util.IOTools;
import slides.o8_parallelStreams.case_study.util.Image;
import slides.o8_parallelStreams.case_study.util.Pixel;



public class MainWithStandardCollectors
{
  public static void main(String[] args) throws IOException
  {
    final String inputFileName = "moi.jpg";
    final String outputFileName = "moi_reduced.png";
    final int k = 3;
    
    System.out.println("Color k-Means");
    System.out.println("Load image: " + inputFileName);
    Image image = IOTools.load(inputFileName);
    
    System.out.println("Reduce image (k = " + k + ")");
    long startTime = System.currentTimeMillis();
    reduce(image, k);
    long endTime = System.currentTimeMillis();
    System.out.println("Duration: " + (endTime - startTime) + "[ms]");
    
    System.out.println("Store image: " + outputFileName);
    IOTools.store(image, outputFileName);
    System.out.println("done");
  }
  
  private static void reduce(Image image, final int maxCluster)
  {
    // random assignment of color pixels to a cluster
    image.pixels.parallelStream().forEach( p -> p.centroidId = ThreadLocalRandom.current().nextInt(maxCluster) );
    
    ColorCentroid[] centroids = new ColorCentroid[ maxCluster ];
    
    // counts reallocations
    LongAdder accum = new LongAdder();
    while(true)
    {
      accum.reset();
      
      Map<Integer, List<Pixel>> clusterMapList = image.pixels.parallelStream()
          .collect( Collectors.groupingBy( p -> p.centroidId) );
          
      // calculate centroids
      for(Integer id : clusterMapList.keySet() )
      {
        double sumRed   = clusterMapList.get(id).stream().mapToDouble( p -> p.red ).sum();
        double sumGreen = clusterMapList.get(id).stream().mapToDouble( p -> p.green ).sum();
        double sumBlue  = clusterMapList.get(id).stream().mapToDouble( p -> p.blue ).sum();
        double len = clusterMapList.get(id).size();
        
        centroids[id] = new ColorCentroid( sumRed/len, sumGreen/len, sumBlue/len);
      }
         
      // assign centroids
      image.pixels.parallelStream().forEach( p ->
      {
           int newClusterId = Util.getNearestCentroidId(p, centroids);
           if( newClusterId != p.centroidId )
           {
             p.centroidId = newClusterId;
             accum.increment();
           }
      } );
      
      // if there are no reallocation
      // the cluster is stable    
      if( accum.sum() == 0 )
      {
        // reassign the new color (centroid) to the pixel
        image.pixels.parallelStream().forEach( 
            p -> {
              p.red   = (int) centroids[p.centroidId].red;
              p.green = (int) centroids[p.centroidId].green;
              p.blue  = (int) centroids[p.centroidId].blue;
            } ); 
        break;
      }
    }
  }
}
