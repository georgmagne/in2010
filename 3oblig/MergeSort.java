public class MergeSort {

  public MergeSort(){

  }

  public void sortIntArr(int[] arrIn1, int[] arrIn2, int[] arrOut) {
    int i = 0;
    int j = 0;

    // Loops trough arrIn1 and arrIn2
    while (i < arrIn1.length && j < arrIn2.length) {
      if (arrIn1[i] <= arrIn2[j]) {
        arrOut[i + j] = arrIn1[i];
        i++;

      } else {
        arrOut[i + j] = arrIn2[j];
        j++;
      }
    }

    String s = "";
    for (int elem : arrIn1) {
      s += elem + ", ";
    }
    System.out.println(s);

    s = "";
    for (int elem : arrIn2) {
      s += elem + ", ";
    }
    System.out.println(s);

    s = "";
    for (int elem : arrOut) {
      s += elem + ", ";
    }
    System.out.println(s);

    while(i < arrIn1.length) {
      arrOut[i + j - 1] = arrIn1[i];
      i++;
    }

    while (j < arrIn2.length) {
      arrOut[i + j - 1] = arrIn2[j];
      j++;
    }
  }
}
