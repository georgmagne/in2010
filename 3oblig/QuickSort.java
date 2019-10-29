import java.util.Random;

public class QuickSort {


  public QuickSort() {

  }

  // public int inPlacePartition(int[] arr, int a, int b) {
  //   int r = new Random().nextInt(arr.length);
  //
  //   // System.out.println("r: " + r);
  //   // System.out.println(arr[arr.length-1]);
  //   // System.out.println(arr[r]);
  //
  //
  //   int tmp = arr[arr.length - 1];
  //   arr[arr.length - 1] = arr[r];
  //   arr[r] = tmp;
  //   // System.out.println(arr[arr.length-1]);
  //   // System.out.println(arr[r]);
  //   int p = arr[r];
  //   int l = 0;
  //   r = arr.length - 1;
  //
  //   while(l <= r) {
  //     while(l <= r && arr[l] <= p){
  //       l ++;
  //     }
  //
  //     while(r >= l && arr[r] >= p) {
  //       r --;
  //     }
  //
  //     if(l < r) {
  //       tmp = arr[l];
  //       arr[l] = arr[r];
  //       arr[r] = tmp;
  //     }
  //   }
  //   tmp = arr[l];
  //   arr[l] = arr[r];
  //   arr[r] = arr[l];
  //
  //
  //   return l;
  // }

  public int inPlacePartition(int[] arr, int start, int end){
    int r = new Random().nextInt(end-start);
    System.out.println("random index "+ r);
    int tmp = arr[end];
    arr[end] = arr[r];
    arr[r] = tmp;

    int pIndex = arr[end];
    int left = start;
    int right = end - 1;


    while(left <= right) {
      System.out.println("left "+left);
      System.out.println("right " +  right);
      System.out.println("arr right " + arr[right]);
      System.out.println("arr left " + arr[left]);

      while(left <= arr[right] && arr[left] <= pIndex){
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
  public void inPlaceQuickSort(int[] arr, int start, int end) {
    if (start >= end) {
      return;
    }

    int left = inPlacePartition(arr, start, end);
    inPlaceQuickSort(arr, start, left - 1 );
    inPlaceQuickSort(arr, left + 1 , end);
    return;
  }


}
