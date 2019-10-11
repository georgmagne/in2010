import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public class TaskGraph {

  private Task[] tasks;
  Task[] topSort;
  String cycle = "";
  int shortestTime = 0;

  public TaskGraph (Task[] tasks) {
    this.tasks = tasks;
  }

  public Task[] getTaskArr() {
    return this.tasks;
  }


  public void resetTasks() {
    for (Task elem: tasks) {
      elem.resetPredecessor();
      elem.resetSuccessor();
      elem.unVisit();
    }
  }

  public String getCycle() {
    return this.cycle;
  }

  // Source:
  // https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleInDirectedGraph.java
  // O(TASK + OUTEDGES)
  // Iterates through Tasks once and then trough its outEdges once.
  // Worst case: O(N^2) if all tasks have all other tasks as neighbors.
  // Possible weakness: if the graph has more than one cycle, one must go trough the greySet and find the separate cycles.
  public boolean hasCycle() {
    Set<Task> whiteSet = new HashSet<>();
    Set<Task> greySet = new HashSet<>();
    Set<Task> blackSet = new HashSet<>();

    for (Task task: tasks){
      whiteSet.add(task);
    }

    while(whiteSet.size() > 0) {
      Task current = whiteSet.iterator().next();
      if( dfs(current, whiteSet, greySet, blackSet)){
        resetTasks();
        return true;
      }
    }
    return false;
  }

  // If dfs returns true, the task has been seen twice or more. And a cycle is present.
  public boolean dfs(Task current, Set<Task> whiteSet, Set<Task> greySet, Set<Task> blackSet){
    whiteSet.remove(current); // Whiteset contains the unproccesed Tasks.
    greySet.add(current); // greyset contains tasks that have been seen, and possible cycle.

    for(Task outEdge: current.outEdges ){
      if(blackSet.contains(outEdge)){ // blackset contains the tasks not part of a cycle.
        continue;
      }

      if(greySet.contains(outEdge)){ //
        // Not counting this in the O() as it is not necessary to find the cycle.
        for (Task task: greySet){
          cycle += task.id + "->";
        }
        return true;
      }

      if(dfs(outEdge, whiteSet, greySet, blackSet)){
        return true;
      }
    }
    greySet.remove(current); // If the method excecution gets to this point, the task is not part of a cycle
    blackSet.add(current); // and may be added to the blacklist.
    return false;
  }

  // Setting the earliestStart and topSorting has timecomplexity:
  // O(TASK + OUTEDGES)
  // It loops trough every task, and then its outEdges.
  // Worst case would be O(N^2) if all tasks have all the other tasks as outEdges
  // This cannot be the case as the graph would be cyclic to the fullest extent. And no topSort may be present.
  public Task[] topSort(){
    Stack<Task> s = new Stack<>();
    Task[] topSorted = new Task[tasks.length];

    for (Task elem: tasks){ // Finds all Tasks with 0 Predecessors.
      if(elem.getCntPredecessor() == 0){
        s.push(elem); // adds them to the stack to be proccesed under.
      }
    }

    int i = 0;
    while (!s.empty()){
      Task current = s.pop();
      topSorted[i] = current;
      i++;

      for (Task successor: current.outEdges){
        successor.subPredecessor();

        int oldES = successor.earliestStart;
        int potentialNewES = current.earliestStart + current.time;

        // If there is a possible earliestStart that is higher than the highest so far
        // It must be updated.
        if(oldES < potentialNewES){
          successor.setEarliestStart(potentialNewES);
        }

        // Updating the shortes total time if the current task has largest earliestStart + finishTime
        // That task must be the one to finish last.
        if( successor.getEarliestStart() + successor.getTime() > this.shortestTime) {
          this.shortestTime = successor.getEarliestStart() + successor.getTime();
        }

        // "Removes" predecessor to be able to move a "lvl deeper".
        if(successor.getCntPredecessor() == 0){
          s.push(successor);
        }
      }
    }

    // My implemetation needs this part to properly set the latest start.
    // Not the proper way of doing it!
    // Does more or less the same as the algorithm setting earliestStart, but in the opposite direction.
    // Starting with the task with zero outEdges.
    s = new Stack<>();

    for (Task elem: tasks){
      elem.setLatestStart(Integer.MAX_VALUE);
      elem.resetPredecessor();

      if(elem.getCntSuccsessor() == 0){
        elem.latestStart = this.shortestTime - elem.time;
        s.push(elem);
      }
    }
    while(!s.isEmpty()){
      Task current = s.pop();

      for (Task predecessor: current.inEdges){

        int oldLS = predecessor.getLatestStart();
        int potentialNewLS = current.getLatestStart() - predecessor.getTime();

        if(oldLS > potentialNewLS){
          predecessor.setLatestStart(potentialNewLS);
        }
        predecessor.subSuccessor();

        if(predecessor.getCntSuccsessor() == 0){
          s.push(predecessor);
        }
      }
    }

    try {
      if(i == tasks.length){ // No cycle, and a topSort is possible.
        return topSorted;

      } else { // cycle is present, topSort not possible.
        return null;
      }
    }

    finally {
      // Resets the variables used by the algorithm.
      // Enabled more than one method call that uses the same variables.
      setCritical();
      resetTasks();
    }
  }

  public void setCritical(){
    for(Task elem: tasks){
      if(elem.getLatestStart() == elem.getEarliestStart()){
        elem.setCritical(true);
      } else {
        elem.setCritical(false);
      }
    }
  }
}
