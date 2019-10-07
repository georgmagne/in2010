import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
// import java.lang.System;


public class Task {
  int id, time, staff;
  String name;
  int earliestStart, latestStart;
  List<Task> outEdges;
  List<Task> inEdges;
  int cntPredecessors;
  int cntSuccessors;

  ArrayList<Integer> depEdgesIndex;
  private boolean visited = false;



  public Task(int id, String name, int time, int staff, ArrayList<Integer> depEdges) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.staff = staff;
    this.outEdges = new ArrayList<>();
    this.inEdges = new ArrayList<>();
    this.depEdgesIndex = depEdges;
    this.cntPredecessors = depEdges.size();
    this.cntSuccessors = inEdges.size();
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

  public void addInEdge(Task inEdge){
    if(!inEdges.contains(inEdge)){
      inEdges.add(inEdge);
    }
  }

  public int getTime(){
    return this.time;
  }

  public int getEarliestStart(){
    return this.earliestStart;
  }

  public int getLatestStart(){
    return this.latestStart;
  }

  public void setEarliestStart(int i){
    this.earliestStart = i;
  }

  public void setLatestStart(int i){
    this.latestStart = i;
  }

  public int getCntPredecessor(){
    return cntPredecessors;
  }

  public void addPredecessor(){
    cntPredecessors++;
  }

  public void subPredecessor(){
    cntPredecessors--;
  }

  public void subSuccessor(){
    cntSuccessors--;
  }

  public int getCntSuccsessor(){
    return this.cntSuccessors;
  }
  public void resetSuccessor(){
    cntSuccessors = inEdges.size();
  }

  public void resetPredecessor(){
    cntPredecessors = depEdgesIndex.size();
  }

  public String toString(){
    String s = "";
    // s += "Hash: " + System.identityHashCode(this) + "\n";
    s += "Id: " + String.valueOf(this.id) + "\n";
    s += "Name: " + name + "\n";
    s += "Predecessors: " + cntPredecessors + "\n";
    s += "Time: " + time  + "\n";
    // s += "Staff: " + staff + "\n";
    s += "ES: " + this.earliestStart + "\n";
    s += "LS: " + this.latestStart + "\n";

    // s += "depEdges: ";
    // for (int elem : depEdgesIndex){
      // s += String.valueOf(elem) + " ";
    // }

    // s += "\n";
    s += "Out edges \n";
    for (Task elem: outEdges){
      s += "Out " + String.valueOf(this.id) + "->" + String.valueOf(elem.id);// + " HASH:" + System.identityHashCode(elem) + " "; //String.valueOf(elem.id) + " ";
    }

    s += "\n IN EDGE: \n";
    for (Task elem: inEdges){
      s += elem.id + "..";
    }
    s += "\n--------------";
    s += "\n";
    return s;
  }
}
