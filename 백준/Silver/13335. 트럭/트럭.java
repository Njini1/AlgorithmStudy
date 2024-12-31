import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    int[] data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < W; i++) {
      q.add(0);
    }

    int sum = 0;
    int time = 0;
    int idx = 0;
    while (idx != N) {

      sum -= q.poll();
      if (sum + data[idx] > L) {
        q.add(0);
      } else {
        q.add(data[idx]);
        sum += data[idx];
        idx++;
      }

      time++;
    }
    time += q.size();

    System.out.println(time);
  }
}
