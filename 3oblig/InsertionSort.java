public class InsertionSort {

  public InsertionSort() {

  }

  public void sortIntArr(int[] arr) {

    // Starting iteration at second elem in int array.
    for (int i = 1; i < arr.length; i++){
      int x = arr[i]; // elem
      int j = i;      // elem index
      Main.printIntArr(arr);

      // index j > 1 and elem x < previous elem
      // While loop trough all previous elems
      while (j > 0 && x < arr[j-1]) {
        arr[j] = arr[j-1];
        j = j - 1;
      }

      // When while breaks arr[j-1] < x < arr[j+1]
      arr[j] = x;
    }
    Main.printIntArr(arr);
    return;
  }
}
