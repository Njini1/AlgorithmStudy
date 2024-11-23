import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 가로
    int M = Integer.parseInt(st.nextToken());

    Character[][] map = new Character[M][N];
    for (int i = 0; i < M; i++) {
      String row = br.readLine();
      for (int j = 0; j < N; j++) {
        map[i][j] = row.charAt(j);
      }
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));
    pq.add(new int[] { 0, 0, 0 }); // x, y, 벽 깬 횟수
    boolean[][] visit = new boolean[M][N];

    int answer = 0;
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int x = cur[0];
      int y = cur[1];
      int cnt = cur[2];

      if (visit[y][x])
        continue;

      visit[y][x] = true;

      if ((y == M - 1) && (x == N - 1)) {
        answer = cnt;
        break;
      }

      for (int d = 0; d < dx.length; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[ny][nx])
          continue;

        if (map[ny][nx] == '0') {
          pq.add(new int[] { nx, ny, cnt });
        } else {
          pq.add(new int[] { nx, ny, cnt + 1 });
        }
      }
    }

    System.out.println(answer);
  }
}
