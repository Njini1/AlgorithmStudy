import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static class Water {
    int wA;
    int wB;
    int wC;

    public Water(int wA, int wB, int wC) {
      this.wA = wA;
      this.wB = wB;
      this.wC = wC;
    }
  }

  static List<Integer> answer = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int wA = Integer.parseInt(st.nextToken());
    int wB = Integer.parseInt(st.nextToken());
    int wC = Integer.parseInt(st.nextToken());

    Queue<Water> data = new ArrayDeque<>();
    data.add(new Water(0, 0, wC));
    boolean[][][] visit = new boolean[wA + 1][wB + 1][wC + 1];
    while (!data.isEmpty()) {
      Water water = data.poll();
      int a = water.wA;
      int b = water.wB;
      int c = water.wC;

      if (visit[a][b][c])
        continue;

      visit[a][b][c] = true;

      if (a == 0) {
        answer.add(c);
      }

      // A -> B
      if (a + b > wB) {
        data.add(new Water(a - (wB - b), wB, c)); // 물을 옮길 때 물이 넘치는 경우
      } else {
        data.add(new Water(0, a + b, c));
      }
      // A -> C
      if (a + c > wC) {
        data.add(new Water(a - (wC - c), b, wC));
      } else {
        data.add(new Water(0, b, a + c));
      }
      // B -> A
      if (b + a > wA) {
        data.add(new Water(wA, b - (wA - a), c));
      } else {
        data.add(new Water(a + b, 0, c));
      }
      // B -> C
      if (b + c > wC) {
        data.add(new Water(a, b - (wC - c), wC));
      } else {
        data.add(new Water(a, 0, b + c));
      }
      // C -> A
      if (a + c > wA) {
        data.add(new Water(wA, b, c - (wA - a)));
      } else {
        data.add(new Water(a + c, b, 0));
      }

      // C -> B
      if (b + c > wB) {
        data.add(new Water(a, wB, c - (wB - b)));
      } else {
        data.add(new Water(a, b + c, 0));
      }
    }

    Collections.sort(answer);

    StringBuilder sb = new StringBuilder();
    for (Integer a : answer) {
      sb.append(a + " ");
    }

    System.out.println(sb.toString());
  }
}