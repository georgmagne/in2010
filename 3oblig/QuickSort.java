import java.util.Random;

public class QuickSort {


  public QuickSort() {

  }

  public int inPlacePartition(int[] arr, int start, int end){
    // Main.printIntArr(arr);
    int r = new Random().nextInt(end-start) + start; // + start to access the correct subarray duting recursion.

    // Moving the choosen pivot to the back of subarray.
    int tmp = arr[end];
    arr[end] = arr[r];
    arr[r] = tmp;

    int pIndex = arr[end];
    int left = start;
    int right = end - 1;

    while(left <= right) {
      while(left <= right && arr[left] <= pIndex){
        left++;
      }

      while(right >= left && arr[right] >= pIndex){
        right--;
      }

      if(left < right) {
        tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
      }
    }

    tmp = arr[left];
    arr[left] = arr[end];
    arr[end] = tmp;
    return left;
  }

  public void corrInPlaceQuickSort(int[] arr, int start, int end) {
    while(start < end){
      int left = inPlacePartition(arr, start, end);
      Main.printIntArr(arr);

      if (left - start < end - left){
        corrInPlaceQuickSort(arr, start, left - 1);
        start = left + 1;
      }
      else {
        corrInPlaceQuickSort(arr, left + 1, end);
        end = left - 1;
      }
    }
  }



}
