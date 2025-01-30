import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Integer> leftPq = new PriorityQueue<>(Collections.reverseOrder()); // 왼쪽에서는 가장 큰 걸 뽑음
    PriorityQueue<Integer> rightPq = new PriorityQueue<>(); // 오른쪽에서는 가장 작은 걸 뽑음
    leftPq.offer(-20000);
    rightPq.offer(20000);

    int cnt = 0;
    boolean left = true;
    while (cnt++ < N) {
      PriorityQueue<Integer> tmpPq = new PriorityQueue<>();
      int value = Integer.parseInt(br.readLine());
      tmpPq.offer(value);
      tmpPq.offer(leftPq.poll());
      tmpPq.offer(rightPq.poll());

      if (left) {
        leftPq.offer(tmpPq.poll());
        leftPq.offer(tmpPq.poll());
        rightPq.offer(tmpPq.poll());
      } else {
        leftPq.offer(tmpPq.poll());
        rightPq.offer(tmpPq.poll());
        rightPq.offer(tmpPq.poll());
      }

      sb.append(leftPq.peek() + "\n");
      left = !left;
    }

    System.out.println(sb.toString());
  }

}