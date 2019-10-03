import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
// import java.lang.System;


public class Task {
  int id, time, staff;
  String name;
  int earliestStart, latestStart;
  List<Task> outEdges;
  int cntPredecessors;

  ArrayList<Integer> depEdgesIndex;
  private boolean visited = false;



  public Task(int id, String name, int time, int staff, ArrayList<Integer> depEdges) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.staff = staff;
    this.cntPredecessors = 0;
    this.outEdges = new ArrayList<>();
    this.depEdgesIndex = depEdges;
  }
  public void visit(){
    visited = true;
  }

  public void unVisit(){
    visited = false;
  }

  public boolean isVisited(){
    return visited;
  }

  public void addOutEdge(Task addTask){
    if (!outEdges.contains(addTask)) {
      outEdges.add(addTask);
    } else {
      return;
    }
  }

  public void addPredecessor(){
    cntPredecessors++;
  }

  public void subPredecessor(){
    cntPredecessors--;
  }

  public String toString(){
    String s = "\n";
    s += "Hash: " + System.identityHashCode(this) + "\n";
    s += "Id: " + String.valueOf(this.id) + "\n";
    s += "Name: " + name + "\n";
    s += "Predecessors: " + cntPredecessors + "\n";
    s += "Time: " + time  + "\n";
    s += "Staff: " + staff + "\n";

    s += "depEdges: ";
    for (int elem : depEdgesIndex){
      s += String.valueOf(elem) + " ";
    }

    s += "\n";
    s += "Out edges \n";
    for (Task elem: outEdges){
      s += "Out " + String.valueOf(this.id) + System.identityHashCode(elem) + " "; //String.valueOf(elem.id) + " ";
    }

    s += "\n";
    return s;
  }
}
