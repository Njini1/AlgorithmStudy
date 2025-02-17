import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int result = 0;
    int[] dp = new int[N + 1];
    dp[0] = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (data[i] > data[j] && dp[i] <= dp[j]) {
          dp[i] = dp[j] + 1;
          result = Math.max(result, dp[i]);
        }
      }
    }

    System.out.println(result);
  }
}