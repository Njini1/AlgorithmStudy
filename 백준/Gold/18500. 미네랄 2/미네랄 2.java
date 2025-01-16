import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static List<int[]> clusters;
  static int R, C;
  static char[][] data;
  static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    // 동굴 데이터 받기
    data = new char[R][C];
    for (int i = 0; i < R; i++) {
      data[i] = br.readLine().toCharArray();
    }

    // 막대 던지기
    int N = Integer.parseInt(br.readLine()); // 던지는 횟수
    st = new StringTokenizer(br.readLine()); // 던지는 높이
    clusters = new ArrayList<>();

    boolean left = true; // 던지는 방향
    for (int n = 0; n < N; n++) {
      int h = Integer.parseInt(st.nextToken()); // 높이
      throwStick(h, left);
      left = !left;

      // 공중에 떠 있는 클러스터 찾기
      findSkyCluster();

      // 공중에 떠 있는 클러스터 내리기
      if (!clusters.isEmpty()) {
        downCluster();
      }

      clusters.clear();
      // printCluster();
    }

    // 결과값 출력
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        sb.append(data[i][j]);
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  static void findSkyCluster() {
    boolean[][] visit = new boolean[R][C];
    for (int y = 0; y < R - 1; y++) {
      for (int x = 0; x < C; x++) {
        if (data[y][x] == 'x' && !visit[y][x]) {
          Queue<int[]> q = new ArrayDeque<>();
          List<int[]> tmp = new ArrayList<>();
          q.add(new int[] { x, y });
          tmp.add(new int[] { x, y });
          visit[y][x] = true;

          boolean skyCluster = false;
          while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
              int nx = cur[0] + dx[d];
              int ny = cur[1] + dy[d];

              if (nx < 0 || nx >= C || ny < 0 || ny >= R || visit[ny][nx] || data[ny][nx] == '.')
                continue;

              // 땅에 붙어 있음(움직일 필요x)
              if (ny == R - 1) {
                skyCluster = true;
                break;
              }

              visit[ny][nx] = true;
              q.add(new int[] { nx, ny });
              tmp.add(new int[] { nx, ny });
            }
          }

          // 공중에 떠 있는 클러스터라면
          if (!skyCluster) {
            clusters.addAll(tmp);
            for (int[] t : tmp) {
              data[t[1]][t[0]] = '.'; // 공중에 떠 있는 클러스터는 . 으로 바꿈
            }
          }
        }
      }
    }
  }

  static void throwStick(int h, boolean left) {
    if (left) {
      for (int j = 0; j < C; j++) {
        if (data[R - h][j] == 'x') {
          data[R - h][j] = '.';
          break;
        }
      }
    } else {
      for (int j = C - 1; j >= 0; j--) {
        if (data[R - h][j] == 'x') {
          data[R - h][j] = '.';
          break;
        }
      }
    }
  }

  static void downCluster() {
    boolean isDown = true;
    while (isDown) {
      for (int[] mineral : clusters) {
        int nx = mineral[0];
        int ny = mineral[1] + 1;

        if (ny >= R || data[ny][nx] == 'x') {
          isDown = false;
          break;
        }
      }

      if (isDown) {
        for (int[] mineral : clusters) {
          mineral[1]++; // 한칸씩 내림
        }
      }
    }

    // 클러스터가 내려간 위치 저장
    for (int[] mineral : clusters) {
      data[mineral[1]][mineral[0]] = 'x';
    }
  }

  static void printCluster() {
    for (int i = 0; i < R; i++) {
      System.out.println(Arrays.toString(data[i]));
    }
  }
}
