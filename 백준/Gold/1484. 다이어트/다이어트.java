import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    List<Integer> answer = new ArrayList<>();
    int total = 0;
    int curValue = 1;
    int startIdx = 1;
    while (true) {
      curValue = curValue + 2;
      int curIdx = (curValue + 1) / 2;
      if (curValue >= N) {
        if (curValue == N) {
          answer.add(curIdx);
        }
        break;
      }

      total += curValue;
      if (total == N) {
        answer.add(curIdx);
      }
      while (total > N) {
        startIdx += 2;
        total -= startIdx;
        if (total == N) {
          answer.add(curIdx);
        }
      }
    }

    if (answer.isEmpty()) {
      System.out.println(-1);
    } else {
      Collections.sort(answer);
      for (Integer a : answer) {
        System.out.println(a);
      }
    }
  }
}
