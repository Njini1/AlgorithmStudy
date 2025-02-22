import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static char[][] map = null;
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };
  static boolean result = false;

  static class Point {
    int x;
    int y;
    int count;

    Point(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }

    public void print() {
      System.out.println("color, x, y :" + x + ", " + y + ", " + count);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 세로
    int M = Integer.parseInt(st.nextToken()); // 가로

    Point rp = null, bp = null;
    map = new char[N][M];
    for (int i = 0; i < N; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 'R') {
          rp = new Point(j, i, 0);
          map[i][j] = '.';
        }

        if (map[i][j] == 'B') {
          bp = new Point(j, i, 0);
          map[i][j] = '.';
        }
      }
    }

    boolean[][][][] visit = new boolean[N][M][N][M]; // [빨간.y][빨간.x][파란.y][파란.x]
    visit[rp.y][rp.x][bp.y][bp.x] = true;
    Queue<Point[]> points = new ArrayDeque<>();
    points.add(new Point[] { rp, bp });

    int answer = Integer.MAX_VALUE;
    while (!points.isEmpty()) {
      Point[] data = points.poll();
      Point curRp = data[0];
      Point curBp = data[1];

      // 이동이 10회 이상일 경우
      if (curRp.count >= 10)
        continue;

      for (int d = 0; d < dx.length; d++) {
        boolean hollR = false; // 빨간 구슬이 빠졌는지 저장
        boolean hollB = false; // 파란 구슬이 빠졌는지 저장

        // 빨간 구슬 파란 구슬 동시에 빠질 경우
        result = false;
        Point moveR = move(curRp, d);
        hollR = result;

        result = false;
        Point moveB = move(curBp, d);
        hollB = result;

        // 빨간 구슬만 빠졌을 경우
        if (hollR && !hollB) {
          answer = Math.min(answer, moveR.count);
          continue;
        }

        // 아무것도 안 빠진 경우
        if (!hollR && !hollB) {
          // 움직인 빨간 구슬과 파란 구슬이 같은 위치에 있는 경우
          if ((moveR.x == moveB.x) && (moveR.y == moveB.y)) {
            if ((d == 0 && curBp.y > curRp.y) || (d == 1 && curBp.y <= curRp.y) || (d == 2 && curBp.x > curRp.x)
                || (d == 3 && curBp.x <= curRp.x)) { // 빨간 구슬이 먼저 움직임 -> 파란 구슬이 한 칸 뒤로 이동
              moveB.x -= dx[d];
              moveB.y -= dy[d];
            } else {
              moveR.x -= dx[d];
              moveR.y -= dy[d];
            }
          }

          if (!visit[moveR.y][moveR.x][moveB.y][moveB.x]) {
            visit[moveR.y][moveR.x][moveB.y][moveB.x] = true;
            points.add(new Point[] { moveR, moveB });
          }
        }

      }
    }

    if (answer == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(answer);

    }
  }

  static Point move(Point p, int d) {
    int nx = p.x;
    int ny = p.y;

    while (true) {
      nx += dx[d];
      ny += dy[d];

      if (map[ny][nx] == '#') {
        return new Point(nx - dx[d], ny - dy[d], p.count + 1);
      }

      if (map[ny][nx] == 'O') {
        result = true;
        return new Point(nx - dx[d], ny - dy[d], p.count + 1);
      }
    }
  }
}
