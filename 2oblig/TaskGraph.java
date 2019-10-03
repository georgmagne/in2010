public class TaskGraph {

  private Task[] tasks;

  public TaskGraph (Task[] tasks){
    this.tasks = tasks;
  }

  public Task[] getTaskArr(){
    return this.tasks;
  }
}
