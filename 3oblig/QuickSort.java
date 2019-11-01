import java.util.Random;

public class QuickSort implements Algorithm{


  public QuickSort(){

  }

  public void sort(int[] arr){
    corrInPlaceQuickSort(arr, 0, arr.length - 1);
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

    // looping towards the index where the pivot was, from the edges to center.
    while(left <= right) {

      // pIndex is bigger than arr[left]
      // arr[left] does not move place.
      while(left <= right && arr[left] <= pIndex){
        left++;
      }

      // pIndex is less than arr[right]
      // arr[right] does not move place.
      while(right >= left && arr[right] >= pIndex){
        right--;
      }

      // Left has not passed right.
      // left and right should swap place
      if(left < right) {
        tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
      }
    }

    // Puts the pivot back to original position.
    // Now all  elems to left is lesser and all elems to right is higher.
    tmp = arr[left];
    arr[left] = arr[end];
    arr[end] = tmp;
    return left;
  }

  public void corrInPlaceQuickSort(int[] arr, int start, int end) {
    while(start < end){
      int left = inPlacePartition(arr, start, end);
      // Main.printIntArr(arr);

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
