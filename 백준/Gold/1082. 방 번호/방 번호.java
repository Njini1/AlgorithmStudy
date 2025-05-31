import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine()); // 문방구에서 파는 숫자는 0부터 N-1
    int[] rooms = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    int minI = 0; // 문방구에서 파는 숫자의 가격 중 가장 작은 것의 인덱스(가격이 같다면 숫자가 큰 것으로)
    for (int i = 0; i < N; i++) {
      rooms[i] = Integer.parseInt(st.nextToken()); // 각 숫자 i의 가격은 Pi
      if (rooms[minI] >= rooms[i]) { // 일단 비용이 가장 작으면서 숫자는 큰 수를 찾는다.
        minI = i;
      }
    }

    int M = Integer.parseInt(br.readLine()); // 숫자를 구매하기 위해 준비한 금액

    // 최대 M원을 사용해서 만들 수 있는 가장 큰 방 번호 구하기
    int maxL = M / rooms[minI]; // 최소의 가격으로 만들 수 있는 최대의 길이 방 숫자
    int minP = maxL * rooms[minI]; // 최소의 가격
    int[] answer = new int[maxL]; // 최대 길이의 최소 방 번호
    int balance = M - minP;
    for (int i = 0; i < maxL; i++) {
      answer[i] = rooms[minI];
    }


    // 최대 길이의 최소 방 번호에서 앞자리부터 큰 숫자로 바꿔가면서 가능한 지 확인
    int startIdx = 0;
    for (int i = 0; i < maxL; i++) {
      for (int j = N - 1; j >= 0; j--) {
        int tmp = rooms[j] - rooms[minI];
        if (tmp <= balance) {
          balance -= tmp;
          answer[i] = j;
          break;
        }
      }

      if (answer[startIdx] == 0) {
        balance += rooms[minI];
        startIdx++;
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = startIdx; i < maxL; i++) {
      sb.append(answer[i]);
    }

    System.out.println(sb.toString().isEmpty() ? 0 : sb.toString());

  }
}
