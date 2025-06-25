import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };
  static boolean[][] visit = null;
  static int[][] avgData = null;
  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // 세로
    M = Integer.parseInt(st.nextToken()); // 가로

    // 화면 데이터 받기
    avgData = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int v3 = Integer.parseInt(st.nextToken());
        avgData[i][j] = (v1 + v2 + v3) / 3;
      }
    }

    int T = Integer.parseInt(br.readLine()); // 경계값
    visit = new boolean[N][M];
    int answer = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (!visit[y][x] && avgData[y][x] >= T) {
          visit[y][x] = true;
          dfs(x, y, T);
          answer++;
        }

      }
    }

    System.out.println(answer);

  }

  static void dfs(int x, int y, int T) {
    for (int d = 0; d < dx.length; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      if (nx < 0 || nx >= M || ny < 0 || ny >= N || visit[ny][nx] || avgData[ny][nx] < T)
        continue;

      visit[ny][nx] = true;
      dfs(nx, ny, T);
    }
  }
}
