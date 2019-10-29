import java.util.Arrays;

public class MergeSort {

  public MergeSort(){

  }


  public int[] mergesort(int[] arr) {
      System.out.println(Arrays.toString(arr));
      if (arr.length == 1){
        return arr;
      }
      int middle = (arr.length + 1) / 2;
      int[] lowArr = Arrays.copyOfRange(arr, 0, middle);
      int[] highArr = Arrays.copyOfRange(arr, middle, arr.length);

      lowArr = mergesort(lowArr);
      highArr = mergesort(highArr);
      return merge(lowArr, highArr, arr);
  }


  public int[] merge(int[] arrIn1, int[] arrIn2, int[] arrOut) {
    int i = 0;
    int j = 0;

    // Loops trough arrIn1 and arrIn2
    while (i < arrIn1.length && j < arrIn2.length) {
      // arrIn1 elem i <= arrIn2 elem j
      // arrIn1 elem i is next elem to arrOut
      if (arrIn1[i] <= arrIn2[j]) {
        arrOut[i + j] = arrIn1[i];
        i++;

      // arrIn2 elem j <= arrIn1 elem i
      // arrIn2 elem J is next elem to arrOut
      } else {
        arrOut[i + j] = arrIn2[j];
        j++;

      }
    }

    // getting last elem to arrOut
    while(i < arrIn1.length) {
      arrOut[i + j] = arrIn1[i];
      i++;
    }

    while (j < arrIn2.length) {
      arrOut[i + j] = arrIn2[j];
      j++;
    }
    return arrOut;
  }
}
