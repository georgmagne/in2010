import java.util.Random;
import java.lang.Math;


public class Main {
  public static void main(String[] args) {
    int arrSize = 10;
    int[] arr;

    int[] arrIncreasing;// = {1, 2, 3, 4, 5, 6, 7, 8, 10};
    int[] arrDecreasing;// = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    // Testing Insertionsort
    InsertionSort insert = new InsertionSort();

    arr = makeRndArr(arrSize, 100, 50);
    arrIncreasing = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    arrDecreasing = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    System.out.println("TESTING INSERTIONSORT");
    System.out.println("UNSORTED: ");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);

    System.out.println("\nRUNNING: ");
    System.out.println("RANDOM");
    insert.sortIntArr(arr);
    System.out.println("\nINCREASING");
    insert.sortIntArr(arrIncreasing);
    System.out.println("\nDECREASING");
    insert.sortIntArr(arrDecreasing);

    System.out.println("\nSORTED: ");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);
    System.out.println();


    // Testing Mergesort
    MergeSort mergeSorter = new MergeSort();

    arr = makeRndArr(arrSize, 100, 50);
    arrIncreasing = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    arrDecreasing = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    System.out.println("TESTING MERGESORT");
    System.out.println("UNSORTED: ");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);

    System.out.println("\nRANDOM");
    mergeSorter.mergesort(arr);
    System.out.println("\nINCREASING");
    mergeSorter.mergesort(arrIncreasing);
    System.out.println("\nDECREASING");
    mergeSorter.mergesort(arrDecreasing);

    System.out.println("\nSORTED: ");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);
    System.out.println();


    // Testing Quicksort
    QuickSort qsort = new QuickSort();

    arr = makeRndArr(arrSize, 100, 50);
    arrIncreasing = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    arrDecreasing = new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    System.out.println("TESTING QUICKSORT");
    System.out.println("UNSORTED: ");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);

    System.out.println();
    qsort.corrInPlaceQuickSort(arr, 0, arr.length-1);
    System.out.println();
    qsort.corrInPlaceQuickSort(arrIncreasing, 0, arrIncreasing.length-1);
    System.out.println();
    qsort.corrInPlaceQuickSort(arrDecreasing, 0, arrDecreasing.length-1);


    System.out.println("\nQUICKSORTED:");
    printIntArr(arr);
    printIntArr(arrIncreasing);
    printIntArr(arrDecreasing);
    System.out.println();

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
  public static void printIntArrRange(int[] arr, int start, int end){
    String s = "[";

    for(int i = start; i < end; i++){
      s += arr[i] + ", ";
    }
    s += arr[end] + "]";
    System.out.println(s);
  }
}
