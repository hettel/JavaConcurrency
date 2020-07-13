package case_study.o1_countingPrims.version1;

public class Main1
{

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start serach");
    long startTime = System.currentTimeMillis(); 
    
    // Partition the work for 4 workers
    Task task1 = new Task(1_000_000, 1_250_000);
    Task task2 = new Task(1_250_000, 1_500_000);
    Task task3 = new Task(1_500_000, 1_750_000);
    Task task4 = new Task(1_750_000, 2_000_000);

    // Create worker threads
    Thread th1 = new Thread( task1 );
    Thread th2 = new Thread( task2 );
    Thread th3 = new Thread( task3 );
    Thread th4 = new Thread( task4 );
    
    // Start threads
    th1.start();
    th2.start();
    th3.start();
    th4.start();
    
    // Wait until all work is done
    th1.join();
    th2.join();
    th3.join();
    th4.join();
    
    long endTime = System.currentTimeMillis();
 
    System.out.println("Duration " + (endTime - startTime) + " [ms]");
  }

}
