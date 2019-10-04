import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class ReadFile {
  Scanner scanner;
  // File file;
  // TaskGraph graphFromFile;

  public ReadFile(){
    // this.file = new File(filePath);
    //
    // try{
    //   this.scanner = new Scanner(this.file);
    // } catch (FileNotFoundException e){
    //   System.out.println("File not found!");
    // }

    // this.scanner = new Scanner();
  }

  public TaskGraph buildGraph(String filePath){
    Task[] tasks;

    try{
      File file = new File(filePath);
      scanner = new Scanner(file);

    } catch (FileNotFoundException e){
      System.out.println("File not found.");
    }

    if (scanner.hasNextLine()){
      String line = scanner.nextLine(); // Gets first line of File - contains number of tasks in graph.
      tasks = new Task[Integer.parseInt(line)]; // Creates Task[] to fill and give to TaskGraph constructor.
      System.out.println("Number of tasks: " + line);
    } else { // no lines in file.
      System.out.println("Error accessing file.");
      return null;
    }


    try {
      scanner.nextLine(); // skips blank line in file.
    } catch (NoSuchElementException e){
      System.out.println("Error in file! Returning from buildGraph().");
      return null;
    }

    while (scanner.hasNextLine()){ // as long as file has more lines.
      String line = scanner.nextLine();
      String[] lineList = line.split("\\s+"); // Splits on whitespace.

      int id = Integer.parseInt(lineList[0]);
      String name = lineList[1];
      int time = Integer.parseInt(lineList[2]);
      int staff = Integer.parseInt(lineList[3]);
      ArrayList<Integer> depEdges = new ArrayList<>();

      for (int i = 4; i < lineList.length; i++){
        // System.out.print(lineList[i] + " - ");
        if (Integer.parseInt(lineList[i]) != 0){
          depEdges.add(Integer.parseInt(lineList[i]));
        }
      }

      tasks[id-1] = new Task(id, name, time, staff, depEdges);

    }

    for (Task elem: tasks) {
      for (int i: elem.depEdgesIndex) {
        tasks[i-1].addOutEdge(elem);
        elem.addPredecessor();
      }
    }

    TaskGraph taskGraph = new TaskGraph(tasks);
    return taskGraph;
  }

  public void setDependencies() {

  }
}
