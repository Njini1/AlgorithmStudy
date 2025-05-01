import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[][] data = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 그림 개수
    int S = Integer.parseInt(st.nextToken()); // 판매 가능 그림 최소 S

    data = new int[N][2];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int height = Integer.parseInt(st.nextToken());
      int price = Integer.parseInt(st.nextToken());
      data[i][0] = height;
      data[i][1] = price;
    }

    Arrays.sort(data, (a, b) -> a[0] - b[0]);

    int[] dp = new int[N + 1];
    dp[0] = 0;

    for (int i = 1; i <= N; i++) {
      int idx = lowerBound(data[i - 1][0] - S);
      dp[i] = Math.max(dp[i - 1], dp[idx] + data[i - 1][1]);
    }

    System.out.println(dp[N]);
  }

  public static int lowerBound(int height) {
    int left = 0;
    int right = data.length - 1;

    while (left < right) {
      int mid = (left + right) / 2;
      if (data[mid][0] <= height) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }
}