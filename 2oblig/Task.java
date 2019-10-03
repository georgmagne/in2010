import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;


public class Task {
  int id, time, staff;
  String name;
  int earliestStart, latestStart;
  List<Task> outEdges;
  int cntPredecessors;

  public Task(int id, String name, int time, int staff) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.staff = staff;
    // this.depEdges = depEdges;
    this.cntPredecessors = 0;

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
    String s = "";
    s = s + "Id: " + String.valueOf(this.id) + "\n";
    s = s + "Name: " + name + "\n";
    s = s + "predecessor: " + cntPredecessors;
    return s;
  }
}
