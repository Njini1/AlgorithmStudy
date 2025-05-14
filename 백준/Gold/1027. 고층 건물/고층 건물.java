import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] building = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      building[i] = Integer.parseInt(st.nextToken());
    }

    int answer = 0;
    // 보이는 고층 건물 개수 계산
    for (int mid = 0; mid < N; mid++) {
      int cnt = 0;

      // 가운데를 기준으로 왼쪽은 앞보다 기울기가 작아야 보임
      double pre = Double.POSITIVE_INFINITY;
      for (int left = mid - 1; left >= 0; left--) {
        double cur = slope(mid, building[mid], left, building[left]);
        if (pre > cur) {
          cnt++;
        }
        pre = Math.min(pre, cur);
      }

      // 가운데를 기준으로 오른쪽은 앞보다 기울기가 커야 보임
      pre = Double.NEGATIVE_INFINITY;
      for (int right = mid + 1; right < N; right++) {
        double cur = slope(mid, building[mid], right, building[right]);
        if (pre < cur) {
          cnt++;
        }
        pre = Math.max(pre, cur);
      }

      answer = Math.max(answer, cnt);
    }
    System.out.println(answer);
  }

  static double slope(int x1, int y1, int x2, int y2) {
    return (double) (y2 - y1) / (x2 - x1);
  }
}

    /*
     * 기울기가 크면 보이고 작으면 안보임
     * 1부터 N까지 하나씩 중심으로 정하고 중심을 기준으로 각각 기울기를 배열에 저장.
     * 이후 기준부터 왼쪽 오른쪽을 나눠 탐색하고 선택한 번호의 기울기보다 앞에 있는 건물들의 기울기가 낮을 경우는 보임. 높으면 안보임
     * 즉, 기준 건물과 선택한 건물에 있는 건물들의 기울기는 낮아야함.
     */

    /*
     * 가운데를 기준으로 왼쪽은 앞보다 기울기가 작아야 보임, 오른쪽은 앞보다 기울기가 커야 보임
     * (0, 1), (1, 2), (2, 7), (3, 3), (4, 2)
     * 3, 5, 0, -4, -2.5
     */
