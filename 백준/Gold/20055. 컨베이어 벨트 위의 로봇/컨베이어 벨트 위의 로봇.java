import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] data;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()) * 2;
    K = Integer.parseInt(st.nextToken());

    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int zeroCnt = 0;
    int upIdx = 0;
    int downIdx = N / 2 - 1;
    int step = 0;
    boolean go = true;
    boolean[] robotsLocation = new boolean[N];
    while (zeroCnt < K) {
      if (upIdx == 0) {
        upIdx = N - 1;
      } else {
        upIdx--;
      }
      if (downIdx == 0) {
        downIdx = N - 1;
      } else {
        downIdx--;
      }

      if (robotsLocation[downIdx]) {
        robotsLocation[downIdx] = false;
      }

      for (int i = N / 2 - 1; i >= 0; i--) {
        int curIdx = (upIdx + i) % N;
        int nextIdx = (curIdx + 1) % N;
        if (robotsLocation[curIdx] && !robotsLocation[nextIdx] && data[nextIdx] > 0) {
          robotsLocation[curIdx] = false;
          robotsLocation[nextIdx] = true;
          data[nextIdx]--;

          if (data[nextIdx] == 0) {
            zeroCnt++;
          }
        }
      }

      if (robotsLocation[downIdx]) {
        robotsLocation[downIdx] = false;
      }

      if (go && !robotsLocation[upIdx] && data[upIdx] > 0) {
        robotsLocation[upIdx] = true;
        data[upIdx]--;

        if (data[upIdx] == 0) {
          zeroCnt++;
        }
      }

      step++;
    }

    System.out.println(step);
  }
}