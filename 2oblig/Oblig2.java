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
    // System.out.println();
    // if(testGraf.hasCycle()){
    //   System.out.println(testGraf.cycle);
    // }




    Task[] sorted = testGraf.topSort();
    // if(sorted != null){
    //   String s = "";
    //   for (Task elem: sorted){
    //     s += elem.id + "->";
    //   }
    //   s += "Complete";
    //   System.out.println("Printing topSort.");
    //   System.out.println(s);
    //
    // } else {
    //   System.out.println(args[0] + " has a cycel. Project is not realizable.");
    // }

    System.out.println("SHORTEST TIME: "+testGraf.shortestTime);

    // for (Task elem: testGraf.getTaskArr()){
    //   System.out.println(elem);
    // }

    // Task[] test = testGraf.getTaskArr();
    // for(Task elem: test){
    //
    //   System.out.println(elem.getCntPredecessor());
    // }
    //
    // System.out.println("Shortest Time: "+testGraf.shortestTime());


    System.out.println("*********************************************************************\n*********************************************************************");
    for(Task elem: testGraf.getTaskArr()){
      System.out.println(elem);
    }

    //StringBuilder to format good looking strings,
    StringBuilder sb = new StringBuilder();
    String spaces = "       ";
    for (int i = 0; i < testGraf.getTaskArr().length; i++) {
      String timeString = "";
      timeString += "\nTime " + i;
      timeString += "\n------------------------";

      boolean helper = false;
      for (Task task: testGraf.getTaskArr()) {
        helper = false;
        if (task.getEarliestStart() == i){
          timeString += "\n"+spaces + "Starting task " + task.id + "\n";
          sb.append(timeString);
          helper = true;
        }

        int finishTime = task.getEarliestStart() + task.getTime();
        if(finishTime == i) {
          timeString += "\n" + spaces + "Finishing task " + task.id + "\n";
          sb.append(timeString);
          helper = true;

        }

        }

        if(helper){
          sb.append(timeString);

      }

    }
    String out = sb.toString();
    System.out.println(out);
  }
}
