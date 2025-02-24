import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[][] result = null;
  static int N, M;
  static char[][] miro = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // 세로
    M = Integer.parseInt(st.nextToken()); // 가로

    miro = new char[N][M];
    for (int i = 0; i < N; i++) {
      miro[i] = br.readLine().toCharArray();
    }

    result = new int[N][M]; // -1: 탈출 실패, 0: 탐색 x, 1: 탈출 성공
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (result[i][j] == 0) {
          dfs(j, i);
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (result[i][j] == 1)
          answer++;
      }
    }

    System.out.println(answer);
  }

  static int dfs(int r, int c) {
    if (r < 0 || r >= M || c < 0 || c >= N) {
      return 1;
    }

    if (result[c][r] != 0) {
      return result[c][r];
    }

    result[c][r] = -1;

    if (miro[c][r] == 'U') {
      result[c][r] = dfs(r, c - 1);
    } else if (miro[c][r] == 'D') {
      result[c][r] = dfs(r, c + 1);
    } else if (miro[c][r] == 'R') {
      result[c][r] = dfs(r + 1, c);
    } else if (miro[c][r] == 'L') {
      result[c][r] = dfs(r - 1, c);
    }

    return result[c][r];
  }
}