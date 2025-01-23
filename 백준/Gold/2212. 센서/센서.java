import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine()); // 센서 개수
    int K = Integer.parseInt(br.readLine()); // 집중국 개수
    int[] sensors = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      sensors[i] = Integer.parseInt(st.nextToken());
    }

    if (sensors.length == 1 || N <= K) {
      System.out.println(0);
      System.exit(0);
    }

    Arrays.sort(sensors);
    int[] diff = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      diff[i] = sensors[i + 1] - sensors[i];
    }

    Arrays.sort(diff);
    int ansewr = 0;
    for (int i = 0; i <= N - K - 1; i++) {
      ansewr += diff[i];
    }

    System.out.println(ansewr);
  }
}
