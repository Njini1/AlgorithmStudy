import java.io.*;
import java.util.*;

public class Main {
  // 방향 설정 (북, 동, 남, 서)
  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { -1, 0, 1, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 세로 크기
    int M = Integer.parseInt(st.nextToken()); // 가로 크기

    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken()); // 초기 y 좌표
    int c = Integer.parseInt(st.nextToken()); // 초기 x 좌표
    int d = Integer.parseInt(st.nextToken()); // 초기 방향 (0: 북, 1: 동, 2: 남, 3: 서)

    int[][] map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0; // 청소한 칸의 수

    while (true) {
      // 1. 현재 위치 청소
      if (map[r][c] == 0) {
        map[r][c] = -1; // 청소 완료 표시
        answer++;
      }

      // 2. 주변 4칸 탐색
      boolean found = false;
      for (int i = 0; i < 4; i++) {
        d = (d + 3) % 4; // 반시계 방향 회전
        int nx = c + dx[d];
        int ny = r + dy[d];

        if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 0) {
          // 청소되지 않은 빈 칸 발견
          r = ny;
          c = nx;
          found = true;
          break;
        }
      }

      if (!found) {
        // 3. 청소할 빈 칸이 없는 경우 후진
        int backX = c - dx[d];
        int backY = r - dy[d];

        if (backX < 0 || backX >= M || backY < 0 || backY >= N || map[backY][backX] == 1) {
          // 후진 불가 -> 작동 멈춤
          break;
        }

        // 후진 가능
        r = backY;
        c = backX;
      }
    }

    System.out.println(answer); // 청소한 칸의 수 출력
  }
}
