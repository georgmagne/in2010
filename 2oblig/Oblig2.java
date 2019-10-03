public class Oblig2 {
  public static void main(String[] args) {

    String fil = args[0];

    ReadFile fileReader = new ReadFile();
    TaskGraph testGraf = fileReader.buildGraph(fil);

    for (Task elem : testGraf.getTaskArr()){
      System.out.println(elem);
    }
  }
}
