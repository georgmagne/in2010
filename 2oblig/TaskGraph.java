import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class TaskGraph {

  private Task[] tasks;
  Task[] topSort;

  public TaskGraph (Task[] tasks) {
    this.tasks = tasks;
    // this.topSort = topSort();
  }

  public Task[] getTaskArr() {
    return this.tasks;
  }

  public void resetVisit() {
    for (Task elem: tasks){
      elem.unVisit();
    }
  }

  public void resetPred(){
    for (Task elem: tasks) {
      elem.resetPredecessor();
    }
  }

  public void resetTasks() {
    for (Task elem: tasks) {
      elem.resetPredecessor();
      elem.unVisit();
    }
  }

  public void DFS(Task t) {
    t.visit();

    for(Task elem: t.outEdges){
      if(!elem.isVisited()){
        DFS(elem);
      }
    }
  }

  public Boolean findCycle(Task startTask){
    // System.out.println("Starter i " + startTask);
    // startTask.visit();

    ArrayList<Task> cyclicTasks = new ArrayList<>();

    for (Task outEdge: startTask.outEdges){
      if(!outEdge.isVisited()){
        outEdge.visit();
        System.out.println("Går til " + outEdge);
        findCycle(outEdge);

      } else if (outEdge.isVisited()){ // Found a cycle
        System.out.println("Potential CYCLE @ " + outEdge.id);

        if(!cyclicTasks.contains(outEdge)){
          cyclicTasks.add(outEdge);
          System.out.println("Adding " + outEdge.id);

          for (Task elem: outEdge.outEdges){

          }
          // findCycle(outEdge);

        } else if (cyclicTasks.contains(outEdge)){ // Completed the cycle.
          System.out.println("Found a cycle, printing:");
          for (Task elem: cyclicTasks){
            System.out.println(elem);
          }
          return true;
        }
      }
    }

    // System.out.println("No cycles.");
    return false;
  }


  public Task[] topSort(){
    Stack<Task> s = new Stack<>();
    Task[] topSorted = new Task[tasks.length];

    for (Task elem: tasks){ // Finds all Tasks with 0 Predecessors.
      System.out.print("yeeting");
      System.out.println(elem);
      if(elem.getCntPredecessor() == 0){
        s.push(elem);
      }
    }

    int i = 0;
    while (!s.empty()){
      Task current = s.pop();
      topSorted[i] = current;
      i++;

      System.out.println(i);
      System.out.println("testingtesting");
      for(Task elem: topSorted){
        System.out.println(elem);
      }

      for (Task successor: current.outEdges){
        successor.subPredecessor();
        System.out.println("successor");
        System.out.println(successor);
        if(successor.getCntPredecessor() == 0){
          s.push(successor);
        }
      }
    }

    try {

      System.out.println(i);
      if(i == tasks.length){
        return topSorted;

      } else {
        System.out.println("Graph has cycel.");
        return null;
      }
    }
    finally {
      resetTasks();
    }
  }

  public int shortestTime() {
    // Shortest time is the time it takes for the longest path to complete.

    int[] dist = new int[tasks.length];
    for(int i = 0; i < dist.length; i++){
      dist[i] = Integer.MIN_VALUE;
    }

    Task start;
      for (Task task: tasks){
        if (task.getCntPredecessor() == 0){
          start = task;
          System.out.println(start);
          dist[start.id-1] = 0;
        }
      }

    for (int i: dist){
      System.out.println(i);
    }

    Task[] topSort = topSort();

    for(Task u: topSort){
      for (Task v: u.outEdges){
        if(dist[v.id-1] < dist[u.id-1] + u.getTime()){
          dist[v.id-1] = dist[u.id-1] + u.getTime();
        }
      }
    }
    for (int i: dist){
      System.out.println(i);

    }


    return 122;


  }

  // public int shortestTime() {
  //   Task start;
  //   for (Task task: tasks){
  //     if (task.getCntPredecessor == 0){
  //       start = task;
  //     } else {
  //       System.out.println("Graph has cycel");
  //       return null;
  //     }
  //   }
  //
  //   LinkedList<Task> q = new LinkedList<>();
  //   q.add(start);
  //
  //   Task current = q.poll();
  //   while (current != null){
  //     for (Task task: current.outEdges){
  //       task.subPredecessor();
  //
  //       if(task.getCntPredecessor == 0){
  //         q.add(task);
  //       }
  //     }
  //
  //
  //     q.poll();
  //   }

    //
    // int[] distance = new int[topSort.length];
    // int[] predecessors = new int[topSort.length];
    //
    // for (int i = 0; i < topSort.length; i++){
    //   distance[i] = Integer.MAX_VALUE;
    //   predecessors[i] = null;
    //
    // }
    //
    // distance[0] = topSort[0].getTime(); // 0
    //
    // for (int i = 0; i < topSort-1; i++){
    //   for (Task neighbor: topSort[i].outEdges){
    //     if (distance[])
    //   }

}
