import java.util.Random;
import java.lang.Math;


public class Main {
  public static void main(String[] args) {

    Random random = new Random(100);


    int arrSize = 10;
    int[] arr = new int[10];
    for(int i = 0; i < arr.length; i++ ){
      arr[i] = random.nextInt(50);
      // System.out.println(arr[i]);
    }

    // System.out.println(arr);
    InsertionSort insert = new InsertionSort();
    insert.sortIntArr(arr);
    // System.out.println(arr);
    printIntArr(arr);


    arrSize = 10;
    int[] arr1 = new int[arrSize];
    int[] arr2 = new int[arrSize];
    int[] outArr = new int[arr1.length + arr2.length];

    Random random1 = new Random(1337);
    Random random2 = new Random(420);

    for(int i = 0; i < arrSize; i++) {
      arr1[i] = random1.nextInt(50);
      arr2[i] = random2.nextInt(50);
    }

    MergeSort merge = new MergeSort();

    System.out.println();
    System.out.println("arr1");
    printIntArr(arr1);
    System.out.println("arr2");
    printIntArr(arr2);

    merge.sortIntArr(arr1, arr2, outArr);

    System.out.println("outArr");
    printIntArr(outArr);

  }

  public static void printIntArr(int[] arr) {
    String s = "[";
    for (int elem : arr) {
      s += elem + ", ";
    }

    s += "]";

    System.out.println(s);
  }
}
