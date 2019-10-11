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
  boolean critical;

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

  public int getStaff(){
    return this.staff;
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

  public void setCritical(boolean b){
    this.critical = b;
  }

  public String toString(){
    String s = "";
    s += "ID: " + String.valueOf(this.id) + "\n";
    s += "NAME: " + name + "\n";
    s += "TIME: " + time  + "\n";
    s += "STAFF: " + staff + "\n";
    s += "EARLIEST START: " + this.earliestStart;
    s += "\nSLACK :" + (latestStart-earliestStart);
    if(critical){
      s += "\nCRITICAL";
    } else {
      s += "\nNOT CRITICAL";
    }

    if (this.outEdges.size() != 0){
      s += "\n\nTHIS TASK MUST BE COMPLETED BEFORE STARTING:\n";
      for (Task elem: this.outEdges){
        s += "TASK ID: " + elem.id + " | ";
      }
    } else {
      s += "\n\nNO TASK IS DEPENDENT ON THIS";
    }

    s += "\n--------------";
    s += "\n";
    return s;
  }
}
