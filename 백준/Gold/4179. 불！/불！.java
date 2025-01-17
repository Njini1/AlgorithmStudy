import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int R, C;
  static char[][] miro;
  static Queue<int[]> fireQ;
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    fireQ = new ArrayDeque<>();
    miro = new char[R][C];
    int[] jp = new int[2];
    for (int y = 0; y < R; y++) {
      miro[y] = br.readLine().toCharArray();
      for (int x = 0; x < C; x++) {
        if (miro[y][x] == 'J') {
          jp[0] = x;
          jp[1] = y;
          miro[y][x] = '.';
        } else if (miro[y][x] == 'F') {
          fireQ.add(new int[] { x, y });
        }
      }
    }

    // 불을 먼저 확산 뒤 지훈 사방탐색
    boolean isAnswer = false;
    int count = 0;
    Queue<int[]> jq = new ArrayDeque<>();
    boolean[][] jVisit = new boolean[R][C];
    jq.add(jp);
    jVisit[jp[1]][jp[0]] = true;
    moveJ: while (!jq.isEmpty()) {
      spreadFire();
      int size = jq.size();
      count++;
      for (int i = 0; i < size; i++) {
        int[] ji = jq.poll();
        for (int d = 0; d < dx.length; d++) {
          int nx = ji[0] + dx[d];
          int ny = ji[1] + dy[d];

          if (!checkRange(nx, ny)) {
            isAnswer = true;
            break moveJ;
          }

          if (miro[ny][nx] == '.' && !jVisit[ny][nx]) {
            jVisit[ny][nx] = true;
            jq.add(new int[] { nx, ny });
          }
        }
      }
    }

    System.out.println(isAnswer ? count : "IMPOSSIBLE");
  }

  static void spreadFire() {
    boolean[][] visit = new boolean[R][C];
    int size = fireQ.size();
    for (int i = 0; i < size; i++) {
      int[] fire = fireQ.poll();
      visit[fire[1]][fire[0]] = true;
      for (int d = 0; d < dx.length; d++) {
        int nx = fire[0] + dx[d];
        int ny = fire[1] + dy[d];

        if (!checkRange(nx, ny) || miro[ny][nx] == 'F' || miro[ny][nx] == '#' || visit[ny][nx])
          continue;

        visit[ny][nx] = true;
        miro[ny][nx] = 'F';
        fireQ.add(new int[] { nx, ny });
      }
    }
  }

  static boolean checkRange(int x, int y) {
    if (x < 0 || x >= C || y < 0 || y >= R)
      return false;
    return true;
  }

  static void printMiro() {
    for (int i = 0; i < R; i++) {
      System.out.println(miro[i]);
    }
  }
}
