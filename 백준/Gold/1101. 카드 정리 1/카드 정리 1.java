import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 박스의 개수
    int M = Integer.parseInt(st.nextToken()); // 색상의 개수

    int[][] data = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = Integer.MAX_VALUE;
    for (int jokerIdx = 0; jokerIdx < N; jokerIdx++) {
      int cnt = 0;
      boolean[] boxes = new boolean[M];
      for (int idx = 0; idx < N; idx++) {
        if (jokerIdx == idx)
          continue;

        int tmp = 0;
        int colorIdx = 0;
        for (int i = 0; i < M; i++) {
          // 여러 개가 들어 있을 경우
          if (data[idx][i] > 0) {
            colorIdx = i;
            tmp++;
          }
          if (tmp > 1) {
            cnt++;
            break;
          }
        }
        // 하나의 색깔만 있는 데 아직 해당 색깔의 박스가 있을 경우
        if (tmp == 1 && boxes[colorIdx])
          cnt++;

        // 하나의 색깔만 있는 데 아직 해당 색깔의 박스가 없을 경우
        if (tmp == 1 && !boxes[colorIdx]) {
          boxes[colorIdx] = true;
        }
      }

      answer = Math.min(answer, cnt);
    }

    System.out.println(answer);
  }
}
