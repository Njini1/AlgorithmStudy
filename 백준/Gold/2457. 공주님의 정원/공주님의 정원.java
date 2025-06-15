import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 데이터 받기
    int N = Integer.parseInt(br.readLine()); // 꽃들의 총 개수
    int[][] data = new int[N][4];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 4; j++) {
        data[i][j] = Integer.parseInt(st.nextToken()); // 3 8 7 31은 꽃이 3월 8일에 피어서 7월 31일에 진다
      }
    }

    // 정렬, [A, B, C, D], A부터 작은 순대로 정렬
    Arrays.sort(data, (a, b) -> {
      if (a[0] == b[0]) {
        if (a[1] == b[1]) {
          if (a[2] == b[2]) {
            return a[3] - b[3];
          }
          return a[2] - b[2];
        }
        return a[1] - b[1];
      }
      return a[0] - b[0];
    });

    // 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때, 선택한 꽃들의 최소 개수 계산
    int answer = 0;
    int endMonth = 3;
    int endDay = 1;
    int idx = 0;
    int tmp = 0;
    while (idx < N) {
      int cnt = 0;
      int maxMoth = 0;
      int maxDay = 0;
      for (int i = idx; i < N; i++) {
        if ((data[i][0] < endMonth) || ((data[i][0] == endMonth) && (data[i][1] <= endDay))) {
          if ((data[i][2] > endMonth) || ((data[i][2] == endMonth) && (data[i][3] > endDay))) {
            cnt++;
            tmp = i;
            if (maxMoth < data[i][2]) {
              maxMoth = data[i][2];
              maxDay = data[i][3];
            } else if ((maxMoth == data[i][2]) && (maxDay < data[i][3])) {
              maxMoth = data[i][2];
              maxDay = data[i][3];
            }
          }
        } else {
          break;
        }
      }

      if (cnt == 0) {
        idx++;
      } else {
        answer++;
        endMonth = maxMoth;
        endDay = maxDay;
        idx = tmp + 1;
      }

      if (endMonth > 11) {
        break;
      }
    }

    if (endMonth <= 11) {
      answer = 0;
    }
    System.out.println(answer);
  }
}
