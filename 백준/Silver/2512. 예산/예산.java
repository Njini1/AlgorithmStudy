import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    int M = Integer.parseInt(br.readLine());

    Arrays.sort(data);
    int start = Math.min(data[0], M / N);
    int end = data[N - 1];
    int answer = 0;
    while (start <= end) {
      int mid = (start + end) / 2;
      int sum = 0;
      for (int i = 0; i < N; i++) {
        sum += data[i] > mid ? mid : data[i];
      }

      if (sum <= M) {
        start = mid + 1;
        answer = mid;
      } else {
        end = mid - 1;
      }
    }

    System.out.println(answer);
  }
}
