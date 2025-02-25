import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    if (N >= K) {
      System.out.println(N - K);
      return;
    }

    int endValue = K * 2;
    boolean[] visit = new boolean[endValue];
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // [탐색 번호, 초]
    pq.add(new int[] { N, 0 });

    while (!pq.isEmpty()) {
      int[] data = pq.poll();

      if (data[0] == K) {
        System.out.println(data[1]);
        break;
      }
        
      if (visit[data[0]])
        continue;

      visit[data[0]] = true;

      int v1 = data[0] * 2;
      if (v1 < endValue && !visit[v1]) {
        pq.add(new int[] { v1, data[1] });
      }

      int v2 = data[0] - 1;
      if (v2 > 0 && !visit[v2]) {
        pq.add(new int[] { v2, data[1] + 1 });
      }

      int v3 = data[0] + 1;
      if (v3 < endValue && !visit[v3]) {
        pq.add(new int[] { v3, data[1] + 1 });
      }
    }
  }
}
