import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 행의 개수 <= 50
    int M = Integer.parseInt(st.nextToken()); // 열의 개수 <= 50

    ArrayList<String> data = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      data.add(br.readLine());
    }

    int K = Integer.parseInt(br.readLine()); // 0 <= 스위치 누르는 개수 <= 1000

    // 정렬
    Collections.sort(data);

    // 불 켤 수 있는 최댓값 찾기
    int answer = 0;
    String pre = "";
    boolean pass = true;
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      // 이전 값과 같다면 개수 세기
      if (pass && pre.equals(data.get(i))) {
        cnt++;
        answer = Math.max(answer, cnt);
      } else {
        // 이전 값과 같지 않다면 K의 값으로 행을 켤 수 있는 값인지 확인
        int tmp = K;
        for (int j = 0; j < M; j++) {
          // 0이라면 해당 값의 스위치를 켜야하기 때문에 적어도 1번 또는 홀수값으로 스위치를 눌러야함. 
          if (data.get(i).charAt(j) == '0')
            tmp--;
        }

        // 0인 값의 스위치를 1번씩 눌러서 키고 나머지 스위치를 누를 수 있는 값이 0보다 같거나 크고 짝수여야 다른 값을 그대로 유지시킬 수 있음.
        if (tmp >= 0 && tmp % 2 == 0) {
          // 해당 값을 K로 행을 다 켤 수 있기 때문에 비교값에 저장
          pre = data.get(i);
          cnt = 1;
          pass = true;
          answer = Math.max(answer, cnt);
        } else {
          pass = false;
        }
      }
    }

    System.out.println(answer);
  }
}
