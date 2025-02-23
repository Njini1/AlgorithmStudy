import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    List<int[]> data = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      data.add(new int[] { a, b });
    }

    Collections.sort(data, (o1, o2) -> o1[0] - o2[0]);
    int[] bData = new int[N];
    for (int i = 0; i < N; i++) {
      bData[i] = data.get(i)[1];
    }

    int[] dp = new int[N];
    for (int i = 0; i < N; i++) {
      dp[i] = 1;
    }

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] >= dp[i] && bData[i] > bData[j]) {
          dp[i] = dp[j] + 1;
        }
      }
    }

    int maxV = 0;
    for (int i = 0; i < N; i++) {
      maxV = Math.max(maxV, dp[i]);
    }

    System.out.println(N - maxV);
  }
}
