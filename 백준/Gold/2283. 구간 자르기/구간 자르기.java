import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 구간 개수
    int K = Integer.parseInt(st.nextToken()); // 남는 부분의 총 합

    int[] data = new int[1000001]; // 구간 데이터
    int maxEnd = 0; // 마지막 끝 점
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int sp = Integer.parseInt(st.nextToken());
      int ep = Integer.parseInt(st.nextToken());
      maxEnd = Math.max(maxEnd, ep);
      for (int j = sp + 1; j <= ep; j++) {
        data[j] += 1;
      }
    }

    // 구간의 누적 합을 구함
    for (int i = 1; i < maxEnd + 1; i++) {
      data[i] += data[i - 1];
    }

    int lp = 0; // 왼쪽 시작 지점
    int rp = 0;
    boolean success = false;
    while (lp < maxEnd) {
      int sp = lp;
      int ep = maxEnd + 1;
      while (sp < ep) {
        int mp = (sp + ep) / 2;

        // 남은 부분의 합이 K보다 클 경우
        if (data[mp] - data[lp] > K) {
          ep = mp;
        } else if ((data[mp] - data[lp] < K)) {
          sp = mp + 1;
        } else {
          success = true;
          rp = mp;
          for (int i = mp; i > lp; i--) {
            if (data[i] - data[lp] != K)
              break;
            rp = i;
          }
          break;
        }
      }

      if (success)
        break;
      lp++;
    }

    if (success) {
      System.out.println(lp + " " + rp);
    } else {
      System.out.println("0 0");
    }

  }
}