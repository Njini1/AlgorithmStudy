import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 데이터 받기
    int N = Integer.parseInt(br.readLine()); // 꽃들의 총 개수
    int[][] data = new int[N][2];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 2; j++) {
        data[i][j] = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken()); // 월에 100을 곱하고 일을 더함
      }
    }

    // 정렬, 꽃이 피는 날짜에서 지는 날짜 순으로 오름차순 정렬
    Arrays.sort(data, (a, b) -> {
      if (a[0] == b[0]) {
        return a[1] - b[1];
      }
      return a[0] - b[0];
    });

    // 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때, 선택한 꽃들의 최소 개수 계산
    int answer = 0;
    int endDate = 301;
    int idx = 0;
    int tmp = 0;
    while (idx < N) {
      int cnt = 0;
      int maxDate = 0;
      for (int i = idx; i < N; i++) {
        if ((data[i][0] <= endDate) && (data[i][1] > endDate)) {
          cnt++;
          tmp = i;
          if (maxDate < data[i][1]) {
            maxDate = data[i][1];
          }

        } else if (data[i][0] > endDate) {
          break;
        }
      }

      if (cnt == 0) {
        idx++;
      } else {
        answer++;
        endDate = maxDate;
        idx = tmp + 1;
      }

      if (endDate > 1130) {
        break;
      }
    }

    if (endDate <= 1130) {
      answer = 0;
    }
    System.out.println(answer);
  }
}
