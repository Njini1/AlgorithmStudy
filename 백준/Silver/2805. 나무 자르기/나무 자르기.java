import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int start = 0;
    int end = 0;
    for (int i = 0; i < N; i++) {
      if (arr[i] > end)
        end = arr[i];
    }
    int mid = 0;

    int answer = 0;
    while (start <= end) {
      long sum = 0;
      mid = (start + end) / 2;
      for (int i = 0; i < N; i++) {
        sum += arr[i] > mid ? arr[i] - mid : 0;
      }

      if (sum >= M) {
        start = mid + 1;
        answer = mid;
      } else {
        end = mid - 1;
      }
    }

    System.out.println(answer);
  }
}