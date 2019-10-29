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

    int[] quickArr = makeRndArr(10, 300, 50);

    QuickSort qsort = new QuickSort();

    printIntArr(quickArr);
    qsort.corrInPlaceQuickSort(quickArr, 0, quickArr.length-1);
    printIntArr(quickArr);


  }

  public static int[] makeRndArr(int size, int seed, int range) {
    int[] arr = new int[size];
    Random r = new Random(seed);

    for (int i = 0; i < size; i++){
      arr[i] = r.nextInt(range);
    }

    return arr;
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
