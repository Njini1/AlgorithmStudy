import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

    Arrays.sort(data);
    int result = 0;
    for (int idx = 0; idx < N; idx++) {
      int start = 0;
      int end = N - 1;

      while (start < end) {
        if (start == idx) {
          start += 1;
          continue;
        } else if (end == idx) {
          end -= 1;
          continue;
        }

        int sum = data[start] + data[end];
        if (sum == data[idx]) {
          result++;
          break;
        } else if (sum >= data[idx]) {
          end -= 1;
        } else {
          start += 1;
        }

      }
    }

    System.out.println(result);

  }
}