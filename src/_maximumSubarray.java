import java.util.Scanner;

class _maximumSubarray {
  static Scanner sc = new Scanner(System.in);

  public static void main(String args[]) {
    int size = sc.nextInt();
    int arr[] = readArray(size);

    Tuple tup = findMaxSub(arr, 0, size - 1);

    System.out.println(tup.sum + " running from index: " + tup.x + " to " + tup.y);

    sc.close();
  }

  private static Tuple findMaxSub(int arr[], int start, int end) {
    if (start == end) {
      return new Tuple(start, end, arr[start]);
    }

    int mid = (end + start) / 2;
    Tuple L = findMaxSub(arr, start, mid);
    Tuple R = findMaxSub(arr, mid + 1, end);
    Tuple M = Max_Crossing_Sub(arr, start, mid, end);

    if (M.sum >= L.sum && M.sum >= R.sum)
      return new Tuple(M.x, M.y, M.sum);

    else if (L.sum >= M.sum && L.sum >= R.sum)
      return new Tuple(L.x, L.y, L.sum);

    else
      return new Tuple(R.x, R.y, R.sum);
  }

  private static Tuple Max_Crossing_Sub(int arr[], int start, int mid, int end) {
    int left_sum = Integer.MIN_VALUE, right_sum = Integer.MIN_VALUE;

    int left = 0, right = 0;
    int sum = 0;

    for (int i = mid; i >= start; i--) {
      sum += arr[i];

      if (sum >= left_sum) {
        left_sum = sum;
        left = i;
      }

    }

    sum = 0;

    for (int i = mid + 1; i <= end; i++) {
      sum += arr[i];

      if (sum >= right_sum) {
        right_sum = sum;
        right = i;
      }

    }

    int total_sum = left_sum + right_sum;
    return new Tuple(left, right, total_sum);

  }

  private static int[] readArray(int size) {
    int arr[] = new int[size];

    for (int i = 0; i < size; i++) {
      arr[i] = sc.nextInt();
    }

    return arr;
  }

  static class Tuple {
    int x, y, sum;

    Tuple(int x, int y, int sum) {
      this.x = x;
      this.y = y;
      this.sum = sum;
    }
    
  }
}
