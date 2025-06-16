import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static final int SIZE = 10001;
  static boolean[] primeNumer = new boolean[SIZE];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 소수 구하기
    eratosthenes();

    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < T; i++) {
      int value = Integer.parseInt(br.readLine());
      // 골드바흐 파티션 계산
      int left = value / 2;
      int right = value / 2;
      while (true) {
        // 소수인 지 확인
        if (!primeNumer[left] && !primeNumer[right]) {
          sb.append(left + " " + right + '\n');
          break;
        }

        left--;
        right++;
      }
    }

    System.out.println(sb.toString());
  }

  static void eratosthenes() {
    primeNumer[1] = true;
    for (int i = 2; i * i < SIZE; i++) {
      if (!primeNumer[i]) {
        for (int j = i * i; j < SIZE; j += i) {
          primeNumer[j] = true;
        }
      }
    }
  }
}
