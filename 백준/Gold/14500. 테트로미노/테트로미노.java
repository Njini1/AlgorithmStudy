import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static boolean[][] visit;
  static int answer = 0;
  static int[][] data = null;
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // 세로
    M = Integer.parseInt(st.nextToken()); // 가로

    data = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    visit = new boolean[N][M];
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        visit[y][x] = true;
        // 'ㅗ' 모양 빼고 다른 모양을 DFS 로 탐색 -> 한번에 그릴 수 있는 모양이기 때문에 DFS 가능
        dfs(x, y, 1, data[y][x]);
        visit[y][x] = false;
        // 'ㅗ' 모양 탐색
        checkShape(x, y);
      }
    }

    System.out.println(answer);
  }

  static void dfs(int x, int y, int cnt, int sum) {
    if (cnt >= 4) {
      answer = Math.max(answer, sum);
      return;
    }

    for (int d = 0; d < dx.length; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      if (nx < 0 || nx >= M || ny < 0 || ny >= N || visit[ny][nx])
        continue;

      visit[ny][nx] = true;
      dfs(nx, ny, cnt + 1, sum + data[ny][nx]);
      visit[ny][nx] = false;
    }
  }

  static void checkShape(int x, int y) {
    int sum = 0;
    if (x <= M - 3) {
      sum += data[y][x];
      sum += data[y][x + 1];
      sum += data[y][x + 2];

      if (y != 0) {
        answer = Math.max(answer, sum + data[y - 1][x + 1]);
      }

      if (y != N - 1) {
        answer = Math.max(answer, sum + data[y + 1][x + 1]);
      }
    }

    sum = 0;
    if (y <= N - 3) {
      sum += data[y][x];
      sum += data[y + 1][x];
      sum += data[y + 2][x];

      if (x != 0) {
        answer = Math.max(answer, sum + data[y + 1][x - 1]);
      }

      if (x != M - 1) {
        answer = Math.max(answer, sum + data[y + 1][x + 1]);
      }
    }
  }
}
