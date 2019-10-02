import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;


public class Task {
  int id, time, staff;
  String name;
  int earliestStart, latestStart;
  List<Task> outEdges;
  int cntPredecessors;
  ArrayList<Integer> depEdges;

  public Task(int id, String name, int time, int staff, ArrayList<Integer> depEdges) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.staff = staff;
    this.depEdges = depEdges;
    this.outEdges = new LinkedList<Task>();
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
}
