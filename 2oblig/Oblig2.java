public class Oblig2 {
  public static void main(String[] args) {

    String fil = args[0];

    ReadFile fileReader = new ReadFile();
    TaskGraph testGraf = fileReader.buildGraph("txt/"+fil); // input files are in txt directory.

    // for (Task elem : testGraf.getTaskArr()){
    //   System.out.println(elem);
    // }
    // System.out.println(testGraf.getTaskArr()[0]);
    // System.out.println(testGraf.getTaskArr()[7]);
    // System.out.println("Starter i " + testGraf.getTaskArr()[0]);
    // testGraf.findCycle(testGraf.getTaskArr()[0]);

    // System.out.println(testGraf.hasCycle());
    System.out.println();
    if(testGraf.hasCycle()){
      System.out.println(testGraf.cycle);
    }




    Task[] sorted = testGraf.topSort();
    if(sorted != null){
      String s = "";
      for (Task elem: sorted){
        s += elem.id + "->";
      }
      s += "Complete";
      System.out.println("Printing topSort.");
      System.out.println(s);

    } else {
      System.out.println(args[0] + " has a cycel. Project is not realizable.");
    }
    System.out.println(testGraf.shortestTime);

    // Task[] test = testGraf.getTaskArr();
    // for(Task elem: test){
    //
    //   System.out.println(elem.getCntPredecessor());
    // }
    //
    // System.out.println("Shortest Time: "+testGraf.shortestTime());





  }
}
