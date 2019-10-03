public class TaskGraph {

  private Task[] tasks;

  public TaskGraph (Task[] tasks) {
    this.tasks = tasks;
  }

  public Task[] getTaskArr() {
    return this.tasks;
  }

  public void resetVisit() {
    for (Task elem: tasks){
      elem.unVisit();
    }
  }

  public void DFS(Task t) {
    t.visit();

    for(Task elem: tasks){
      if(!elem.isVisited()){
        DFS(elem);
      }
    }
  }

  public boolean hasCycle() {
    for (Task t: tasks) {
      if(!t.isVisited()){
        DFS(t);
      }
    }
  }


}
