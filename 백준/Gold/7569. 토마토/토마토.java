import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = { 0, 0, -1, 1 }; // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
  static int[] dy = { -1, 1, 0, 0 };
  static int[] dh = { -1, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken()); // 가로
    int N = Integer.parseInt(st.nextToken()); // 세로
    int H = Integer.parseInt(st.nextToken()); // 높이

    // 토마토 데이터 입력
    Queue<int[]> tomatoQ = new ArrayDeque<>();
    int[][][] data = new int[H][N][M];
    int zeroCnt = 0;
    for (int h = 0; h < H; h++) {
      for (int n = 0; n < N; n++) {
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++) {
          data[h][n][m] = Integer.parseInt(st.nextToken()); // 1: 익은 토마토, 0: 안 익은 토마토, -1: 토마토 없음
          if (data[h][n][m] == 1) {
            tomatoQ.add(new int[] { m, n, h }); // 가로 세로 높이
          } else if (data[h][n][m] == 0) {
            zeroCnt++;
          }
        }
      }
    }
      
    if (tomatoQ.isEmpty()) {
      System.out.println(-1);
      return;
    }
    if (N * M * H == zeroCnt) {
      System.out.println(0);
      return;
    }

    int answer = 0;
    while (!tomatoQ.isEmpty()) {

      if (zeroCnt == 0)
        break;

      int size = tomatoQ.size();
      answer++;
      for (int i = 0; i < size; i++) {
        int[] tomato = tomatoQ.poll();

        // 상 하 좌 우 탐색
        for (int d = 0; d < dx.length; d++) {
          int nx = tomato[0] + dx[d];
          int ny = tomato[1] + dy[d];
          int nh = tomato[2];

          if (nx < 0 || nx >= M || ny < 0 || ny >= N || data[nh][ny][nx] != 0)
            continue;

          tomatoQ.add(new int[] { nx, ny, nh });
          data[nh][ny][nx] = 1;
          zeroCnt--;
        }

        // 앞 뒤 탐색
        for (int d = 0; d < dh.length; d++) {
          int nx = tomato[0];
          int ny = tomato[1];
          int nh = tomato[2] + dh[d];

          if (nh < 0 || nh >= H || data[nh][ny][nx] != 0) {
            continue;
          }

          tomatoQ.add(new int[] { nx, ny, nh });
          data[nh][ny][nx] = 1;
          zeroCnt--;
        }
      }
    }

    if (zeroCnt != 0) {
      answer = -1;
    }

    System.out.println(answer);
  }
}
