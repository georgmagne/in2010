import java.util.Random;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    int arrSize = 10;
    int[] arr;
    int[] arrIncreasing;
    int[] arrDecreasing;

    //Testing Insertionsort
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
    insert.sort(arr);
    System.out.println("\nINCREASING");
    insert.sort(arrIncreasing);
    System.out.println("\nDECREASING");
    insert.sort(arrDecreasing);

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


    int times = 3; // Number of time to check algorithm
    int size = 50000; // Size of arrays to test.

    printAllAvg(times, size);
    // printAllAvg(20, 1000000); // Very long runtime for INSERTIONSORT with this values.





  }

  public static void printAllAvg(int times, int size) {
    double time;
    double timeInc;
    double timeDec;

    InsertionSort insert = new InsertionSort();
    System.out.println("TESTING INSERTIONSORT");
    System.out.println("\nTIMES FOR INSERTIONSORT");
    System.out.println("ELEMS: " + size);
    time = avgTimeRndArr(times, size, insert);
    System.out.println("Random: "+time);
    timeInc = avgTimeIncArr(times, size, insert);
    System.out.println("Increasing: " + timeInc);
    timeDec = avgTimeDecArr(times, size, insert);
    System.out.println("Decrasing: " + timeDec);

    MergeSort merge = new MergeSort();
    System.out.println("\nTIMES FOR MERGESORT");
    System.out.println("ELEMS: " + size);
    time = avgTimeRndArr(times, size, merge);
    System.out.println("Random: "+time);
    timeInc = avgTimeIncArr(times, size, merge);
    System.out.println("Increasing: " + timeInc);
    timeDec = avgTimeDecArr(times, size, merge);
    System.out.println("Decrasing: " + timeDec);

    QuickSort quick = new QuickSort();
    System.out.println("\nTIMES FOR QUICKSORT");
    System.out.println("ELEMS: " + size);
    time = avgTimeRndArr(times, size, quick);
    System.out.println("Random: "+time);
    timeInc = avgTimeIncArr(times, size, quick);
    System.out.println("Increasing: " + timeInc);
    timeDec = avgTimeDecArr(times, size, quick);
    System.out.println("Decrasing: " + timeDec);

    System.out.println("\nTIMES FOR ARRAYS.SORT()");
    System.out.println("ELEMS: " + size);
    time = avgTimeRndArraysSort(times, size);
    System.out.println("Random: "+time);
    time = avgTimeIncArraysSort(times, size);
    System.out.println("Increasing: " + timeInc);
    time = avgTimeDecArraysSort(times, size);
    System.out.println("Decrasing: " + timeDec);
  }

  // Helper function to check for average runtimes for given selfmade algorithms.
  // Can give function specific times to test, size of array.

  // Random arrays.
  public static double avgTimeRndArr(int times, int size, Algorithm algo){
    double avg;
    int[] arr;
    long t;
    double time = 0;
    int randInt;

    for (int i = 0; i < times; i++){
      randInt = new Random().nextInt();
      arr = makeRndArr(size, randInt, Integer.MAX_VALUE);

      t = System.nanoTime();
      algo.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;

  }

  // Decreasing arrays.
  public static double avgTimeDecArr(int times, int size, Algorithm algo){
    double avg;
    int[] arr;
    long t;
    double time = 0;

    for (int i = 0; i < times; i++){

      arr = makeDecArr(size);

      t = System.nanoTime();
      algo.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;

  }

  // Increasing arrays.
  public static double avgTimeIncArr(int times, int size, Algorithm algo){
    double avg;
    int[] arr;
    long t;
    double time = 0;

    for (int i = 0; i < times; i++){

      arr = makeIncArr(size);

      t = System.nanoTime();
      algo.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;
  }

  // Functions that do the same as the above, but with Arrays.sort().
  public static double avgTimeRndArraysSort(int times, int size){
    double avg;
    int[] arr;
    long t;
    double time = 0;
    int randInt;

    for (int i = 0; i < times; i++){
      randInt = new Random().nextInt();
      arr = makeRndArr(size, randInt, Integer.MAX_VALUE);

      t = System.nanoTime();
      Arrays.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;
  }

  public static double avgTimeIncArraysSort(int times, int size){
    double avg;
    int[] arr;
    long t;
    double time = 0;


    for (int i = 0; i < times; i++){
      arr = makeIncArr(size);

      t = System.nanoTime();
      Arrays.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;
  }

  public static double avgTimeDecArraysSort(int times, int size){
    double avg;
    int[] arr;
    long t;
    double time = 0;

    for (int i = 0; i < times; i++){

      arr = makeDecArr(size);

      t = System.nanoTime();
      Arrays.sort(arr);
      time += (System.nanoTime() - t) / 1000000.0;

    }
    avg = time / times;
    return avg;
  }

  // Helper functions to create and print arrays.
  public static int[] makeIncArr(int size){
    int[] arr = new int[size];

    for (int i = 0; i < arr.length; i++){
      arr[i] = i;
    }
    return arr;
  }

  public static int[] makeDecArr(int size){
    int[] arr = new int[size];
    size--;

    for (int i = 0; i < arr.length-1; i++){
      arr[i] = size;
      size--;
    }
    return arr;
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
