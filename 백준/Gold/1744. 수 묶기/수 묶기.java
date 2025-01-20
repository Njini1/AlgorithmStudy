import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
  static int result;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    result = 0;
    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> plusQ = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minusQ = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      int value = Integer.parseInt(br.readLine());
      if (value > 0) {
        if (value == 1) {
          result += 1;
          continue;
        }
        plusQ.add(value);

      } else {
        minusQ.add(value);
      }
    }

    calculate(plusQ);
    calculate(minusQ);

    System.out.println(result);
  }

  static void calculate(PriorityQueue<Integer> pq) {
    while (!pq.isEmpty()) {
      int cur = pq.poll();
      int next = 0;
      if (!pq.isEmpty()) {
        next = pq.poll();
      } else {
        result += cur;
        break;
      }

      result += cur * next;
    }
  }
}