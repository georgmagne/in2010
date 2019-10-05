public class Oblig2 {
  public static void main(String[] args) {

    String fil = args[0];

    ReadFile fileReader = new ReadFile();
    TaskGraph testGraf = fileReader.buildGraph(fil);

    // for (Task elem : testGraf.getTaskArr()){
    //   System.out.println(elem);
    // }
    System.out.println(testGraf.getTaskArr()[0]);
    System.out.println(testGraf.getTaskArr()[7]);
    System.out.println("Starter i " + testGraf.getTaskArr()[0]);
    testGraf.findCycle(testGraf.getTaskArr()[0]);


    Task[] sorted = null;//testGraf.topSort();
    if(sorted != null){
      String s = "";
      for (Task elem: sorted){
        s += elem.id + "->";
      }
      System.out.println(s);

    } else {
      System.out.println(args[0] + " has a cycel. Project is not realizable.");
    }

    System.out.println(testGraf.shortestTime());



  }
}
