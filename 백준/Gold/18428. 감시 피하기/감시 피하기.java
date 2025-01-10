import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };
  static int N;
  static String[][] data;
  static ArrayList<int[]> xPoints;
  static ArrayList<int[]> tPoints;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    data = new String[N][N];
    xPoints = new ArrayList<>();
    tPoints = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      String row = br.readLine();
      data[i] = row.split(" ");
      for (int j = 0; j < N; j++) {
        if (data[i][j].equals("X")) {
          xPoints.add(new int[] { j, i }); // x, y
        } else if (data[i][j].equals("T")) {
          tPoints.add(new int[] { j, i });
        }
      }
    }

    // combination(data.length, 0, 0, new int[3]);
    combination(xPoints.size(), 0, 0, new boolean[xPoints.size()]);
    System.out.println("NO");
  }

  public static void combination(int dc, int r, int idx, boolean[] visit) {
    if (r == 3) {
      for (int i = 0; i < dc; i++) {
        if (visit[i]) {
          // System.out.print(i + " ");
          int x = xPoints.get(i)[0];
          int y = xPoints.get(i)[1];
          data[y][x] = "O";
        }
      }

      if (watchStudent()) {
        System.out.println("YES");
        System.exit(0);
      }

      for (int i = 0; i < dc; i++) {
        if (visit[i]) {
          int x = xPoints.get(i)[0];
          int y = xPoints.get(i)[1];
          data[y][x] = "X";
        }
      }
      return;
    }

    for (int i = idx; i < dc; i++) {
      if (!visit[i]) {
        visit[i] = true;
        combination(dc, r + 1, i + 1, visit);
        visit[i] = false;
      }
    }
  }

  public static boolean watchStudent() {
    boolean result = true;
    for (int[] p : tPoints) {
      int x = p[0];
      int y = p[1];
      for (int d = 0; d < 4; d++) {
        int nx = x;
        int ny = y;
        while (true) {
          nx += dx[d];
          ny += dy[d];

          if (nx < 0 || nx >= N || ny < 0 || ny >= N || data[ny][nx].equals("O"))
            break;

          if (data[ny][nx].equals("S")) {
            result = false;
            break;
          }
        }

      }
    }
    return result;

  }
}
