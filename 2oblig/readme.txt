README for Oblig 2 IN2010

Project files are kept in /txt directory.
Main method takes this into consideration.
To run a different file either add it to the /txt directory or edit Oblig2.java.main:7 to fit your file.

1. To compile and run in bash:
    javac *.java && java Oblig2 <filename.txt>

2. Oblig2.java contains the main method.

3. The program assumes that the input file has the right formatting i.e.
  First line contains a number representing the number of tasks in project.
  Second line is blank, no info.
  The third and following lines contains info about the tasks, info is separated by whitespace:
  ID NAME TIME MANPOWER LIST_DESCRIBING_DEPENDENCY_IDS_TERMINATED_BY_0

  The ids in the list with dependency edges are separated by space, and terminated by 0. 0 is not an ID.

4. The way my implementation finds tasks latestStart i think is not the best way
  It needs references to the given task's parent tasks.
  Which in my mind technically makes the graph undirected, while it should be implemented as directed.
  I have been told this may be done using the topological sort and traversing from the last task to the first acording to the topsort.
  Due to time constraints i was not able to properly explore this solution, and had to implement inEdges to the task objects.
  The rest of my implementation is not using the direct references to parent tasks.

5. I have only properly checked the status of buildhouse1.txt, buildhouse2.txt and examplefigure.txt.
  And i am quite sure my implementation simulate this three project correct.
  When running buildrail.txt and buildgarage.txt i dont get anything unexpected.
  Therefore i assume they are correct aswell. As i dont have any reason to why they should not be.

6. My implementation of the cycle finder is infuenced by the following code:
  https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleInDirectedGraph.java

  I was able to detect a cycle in my topological sort method, but not the actual tasks in the cycle.
  Therefore my TaskGraph.hasCycle() and its helping method dfs(Task, HashSet, HashSet, HashSet) is infuenced by the code specified in the link above.
