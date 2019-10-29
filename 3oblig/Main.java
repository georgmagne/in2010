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
    // printIntArr(arr);


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
    

    MergeSort mergeSorter = new MergeSort();

    mergeSorter.mergesort(arr1);
    System.out.println("arr1");
    printIntArr(arr1);

  }

  public static void printIntArr(int[] arr) {
    String s = "[";
    for (int i = 0; i < arr.length - 1; i++) {
      s += arr[i] + ", ";
    }

    s += arr[arr.length-1] + "]";

    System.out.println(s);
  }
}
