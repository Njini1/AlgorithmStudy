import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 집의 개수
    int C = Integer.parseInt(st.nextToken()); // 공유기의 개수

    List<Integer> points = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      int tmp = Integer.parseInt(br.readLine());
      points.add(tmp);
    }

    int answer = 0;
    Collections.sort(points);
    int start = 1;
    int end = points.get(N - 1) - points.get(0);
    while (start <= end) {
      int mid = (start + end) / 2;
      int idx = 0;
      int cnt = 1;
      int preIdx = points.get(0);
      for (int i = 1; i < N; i++) {
        if (points.get(i) - preIdx >= mid) {
          cnt++;
          preIdx = points.get(i);
        }

        if (cnt >= C) {
          answer = Math.max(answer, mid);
          start = mid + 1;
          break;
        }
      }

      if (cnt < C) {
        end = mid - 1;
      }

    }

    System.out.println(answer);

  }
}