import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
  static int[] dy = { -1, 1, 0, 0 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken()); // 세로
    int N = Integer.parseInt(st.nextToken()); // 가로
    int K = Integer.parseInt(st.nextToken()); // K개의 직사각형

    boolean[][] paper = new boolean[M][N];
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      int lx = Integer.parseInt(st.nextToken()); // 왼쪽 위 꼭짓점.
      int ly = Integer.parseInt(st.nextToken());

      int rx = Integer.parseInt(st.nextToken()); // 오른쪽 위 꼭짓점
      int ry = Integer.parseInt(st.nextToken());

      for (int y = ly; y < ry; y++) {
        for (int x = lx; x < rx; x++) {
          paper[y][x] = true;
        }
      }
    }

    int count = 0;
    ArrayList<Integer> areas = new ArrayList<>();
    ArrayDeque<int[]> points = new ArrayDeque<>();
    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        if (paper[y][x])
          continue;

        count++;
        points.add(new int[] { x, y });
        int areaSize = 1;
        paper[y][x] = true;
        while (!points.isEmpty()) {
          int[] point = points.poll();

          for (int d = 0; d < dx.length; d++) {
            int nx = point[0] + dx[d];
            int ny = point[1] + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || paper[ny][nx])
              continue;

            points.add(new int[] { nx, ny });
            paper[ny][nx] = true;
            areaSize++;
          }
        }
        areas.add(areaSize);

      }
    }
      
    Collections.sort(areas);

    StringBuilder sb = new StringBuilder();
    sb.append(count).append('\n');
    for (int i = 0; i < areas.size(); i++) {
      sb.append(areas.get(i));
      if (i + 1 < areas.size())
        sb.append(' ');
    }
    sb.append('\n');

    System.out.print(sb.toString());
  }
}
