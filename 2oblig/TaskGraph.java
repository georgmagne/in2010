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
    // this.topSort = topSort();
  }

  public Task[] getTaskArr() {
    return this.tasks;
  }
  //
  // public void resetVisit() {
  //   for (Task elem: tasks){
  //     elem.unVisit();
  //   }
  // }
  //
  // public void resetPred(){
  //   for (Task elem: tasks) {
  //     elem.resetPredecessor();
  //   }
  // }

  public void resetTasks() {
    for (Task elem: tasks) {
      elem.resetPredecessor();
      elem.unVisit();
    }
  }

  // public void DFS(Task t) {
  //   t.visit();//   String s = "";
  //   //   for (Task elem: sorted){
  //   //     s += elem.id + "->";
  //   //   }
  //   //   s += "Complete";
  //   //   System.out.println("Printing topSort.");
  //   //   System.out.println(s);
  //   //
  //   // } else {
  //   //   System.out.println(args[0] + " has a cycel. Project is not realizable.");
  //   // }
  //
  //
  //   for(Task elem: t.outEdges){
  //     if(!elem.isVisited()){
  //       DFS(elem);
  //     }
  //   }
  // }

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

  public boolean dfs(Task current, Set<Task> whiteSet, Set<Task> greySet, Set<Task> blackSet){
    whiteSet.remove(current);
    greySet.add(current);

    for(Task outEdge: current.outEdges ){
      if(blackSet.contains(outEdge)){
        continue;
      }

      if(greySet.contains(outEdge)){

        cycle += "This graph has cycle: ";
        for (Task task: greySet){
          cycle += "->" + task.id;
        }

        cycle += "-> back to start.";
        return true;
      }

      if(dfs(outEdge, whiteSet, greySet, blackSet)){
        return true;
      }
    }
    greySet.remove(current);
    blackSet.add(current);
    return false;
  }


  public Task[] topSort(){
    Stack<Task> s = new Stack<>();
    Task[] topSorted = new Task[tasks.length];

    for (Task elem: tasks){ // Finds all Tasks with 0 Predecessors.
      if(elem.getCntPredecessor() == 0){
        s.push(elem);
      }
    }

    int i = 0;
    while (!s.empty()){
      Task current = s.pop();
      topSorted[i] = current;
      i++;

      // for(Task elem: topSorted){
      //   System.out.println(elem);
      // }
      System.out.println("Behanlder ny task: ");
      System.out.println(current);

      for (Task successor: current.outEdges){
        System.out.println(successor);
        successor.subPredecessor();

        System.out.println("ID: " + current.id + " TIME: " + current.time);
        System.out.println("ID: " + successor.id + " TIME: " + successor.time);

        int oldES = successor.earliestStart;
        int potentialNewES = current.earliestStart + current.time;

        if(oldES < potentialNewES){
          successor.setEarliestStart(potentialNewES);
        }

        if( successor.getEarliestStart() + successor.getTime() > this.shortestTime) {
          this.shortestTime = successor.getEarliestStart() + successor.getTime();
        }

        if(successor.getCntPredecessor() == 0){
          s.push(successor);
        }
      }
    }

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

      System.out.println(i);
      if(i == tasks.length){
        return topSorted;

      } else {
        System.out.println("Graph has cycel. Something went wrong.");
        return null;
      }
    }
    finally {
      resetTasks();
    }
  }

  public int shortestTime() {
    ArrayList<Task> criticalPath = new ArrayList<>();
    ArrayList<Task> startTasks = new ArrayList<>();

    // //Finding first lvl.
    for (Task task: tasks){
      if (task.getCntPredecessor() == 0){
        startTasks.add(task);
      }
    }
    Task criticalTask = null;
    int compTime = -1;

    for(Task task: startTasks){
      int taskTime = task.getTime();

      if (taskTime > compTime){
        compTime = taskTime;
        criticalTask = task;
      }
    }
    criticalPath.add(criticalTask);

    while( !criticalPath.get(criticalPath.size()-1).outEdges.isEmpty() ){
      criticalTask = null;
      compTime = 0;

      for (Task task: criticalPath.get(criticalPath.size()-1).outEdges){
        int taskTime = task.getTime();
        if(taskTime > compTime){
          compTime = taskTime;
          criticalTask = task;
        }
      }
      criticalPath.add(criticalTask);
    }

    // criticalPath found.
    System.out.println("Crit path");
    int earliestFinish = 0;
    for (Task task: criticalPath){
      System.out.println(task);
      earliestFinish += task.getTime();
    }

    return earliestFinish;

  }
}
