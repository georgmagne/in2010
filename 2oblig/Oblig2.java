public class Oblig2 {
  public static void main(String[] args) {

    String fil = args[0];

    ReadFile fileReader = new ReadFile();
    TaskGraph testGraf = fileReader.buildGraph("txt/"+fil); // input files are in txt directory.

    Task[] sorted = testGraf.topSort();


    System.out.println("\n\nPRINTING FOR PROJECT: " + args[0]);
    System.out.println("*********************************************************************\n*********************************************************************");
    System.out.println("ALL TASKS: \n");
    for(Task elem: testGraf.getTaskArr()){
      System.out.println(elem);
    }

    String out = ""; // String to be printed.
    if(testGraf.hasCycle()){ // Unable to simulate project.
      out = "This project is unrealizable because of a cycle @ ";
      out += testGraf.cycle;
    } else { // able to simulate project.

      // Helper variables for creation of output string.
      StringBuilder sb = new StringBuilder();
      String spaces = "       ";
      String timeString = "";
      boolean helper = false;
      int currentStaff = 0;

      // Looping through each time unit
      for (int i = 0; i <= testGraf.shortestTime; i++) {
        helper = false;
        timeString = "\n\n------------------------\nTime " + i;
        timeString += "\n------------------------";

        // Looping through every task and checking if something should happen at given timeunit.
        for (Task task: testGraf.getTaskArr()) {

          if (task.getEarliestStart() == i){
            helper = true;

            timeString += "\n"+spaces + "Starting task " + task.id + "\n";
            currentStaff += task.getStaff();
          }

          int finishTime = task.getEarliestStart() + task.getTime();
          if(finishTime == i) {
            helper = true;

            timeString += "\n" + spaces + "Finishing task " + task.id + "\n";
            currentStaff -= task.getStaff();
          }

        }

        // If something happened at given timeunit.
        if(helper){
          timeString += "\n" + spaces + "Current staff: " + currentStaff;
          sb.append(timeString);
        }
      }
      out = sb.toString();
    }

    System.out.println(out);
  }
}
