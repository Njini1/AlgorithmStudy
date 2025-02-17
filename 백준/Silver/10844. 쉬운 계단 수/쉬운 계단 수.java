import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static final int MOD = 1000000000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[10];

    for (int i = 1; i <= 9; i++) {
      dp[i] = 1;
    }

    for (int i = 1; i < N; i++) {
      int[] tmp = new int[10];

      tmp[0] = dp[1] % MOD;

      for (int j = 1; j <= 8; j++) {
        tmp[j] = (dp[j - 1] + dp[j + 1]) % MOD;
      }

      tmp[9] = dp[8] % MOD;

      dp = tmp;
    }

    int sum = 0;
    for (int i = 0; i < dp.length; i++) {
      sum = (sum + dp[i]) % MOD;
    }

    System.out.println(sum);
  }
}