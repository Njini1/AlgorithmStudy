import java.io.*;
import java.util.*;

public class Main {
  static final int SIZE = 100_001;
  static int[] parents = null;
  static int[] count = null;
  static Map<String, Integer> friends = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int TC = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int tc = 0; tc < TC; tc++) {
      int F = Integer.parseInt(br.readLine());
      friends = new HashMap<>();
      parents = new int[SIZE * 2];
      count = new int[SIZE * 2];
      for (int i = 0; i < parents.length; i++) {
        parents[i] = i;
        count[i] = 1;
      }

      int idx = 1;
      for (int i = 0; i < F; i++) {
        String[] data = br.readLine().split(" ");
        String f1 = data[0];
        String f2 = data[1];

        if (!friends.containsKey(f1)) {
          friends.put(f1, idx++);
        }

        if (!friends.containsKey(f2)) {
          friends.put(f2, idx++);
        }

        int result = union(f1, f2);
        sb.append(result + "\n");
      }
    }

    System.out.println(sb.toString());
  }

  static int find(int x) {
    if (parents[x] == x)
      return x;

    return find(parents[x]);
  }

  static int union(String f1, String f2) {
    int x = find(friends.get(f1));
    int y = find(friends.get(f2));

    if (x != y) {
      if (x < y) { // x가 부모
        parents[y] = x;
        count[x] += count[y];
        return count[x];
      } else {
        parents[x] = y;
        count[y] += count[x];
        return count[y];
      }
    }

    return count[x];
  }
}