import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] parent = null;
  static int[] parentPrice = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 학생 수 N (1 ≤ N ≤ 10,000)
    int M = Integer.parseInt(st.nextToken()); // 친구관계 수 M (0 ≤ M ≤ 10,000)
    int k = Integer.parseInt(st.nextToken()); // 가지고 있는 돈 k (1 ≤ k ≤ 10,000,000)

    // 친구비 데이터 받기
    parent = new int[N + 1];
    parentPrice = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      parent[i] = i; // 부모 초기화
      parentPrice[i] = Integer.parseInt(st.nextToken());
    }

    // 친구 관계 데이터 받기
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int f1 = Integer.parseInt(st.nextToken());
      int f2 = Integer.parseInt(st.nextToken());

      if (f1 != f2)
        union(f1, f2);
    }

    int sum = 0;
    for (int i = 0; i <= N; i++) {
      sum += parentPrice[i];
    }

    if (sum <= k) {
      System.out.println(sum);
    } else {
      System.out.println("Oh no");
    }
  }

  static void union(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);

    if (parent[parentA] == parent[parentB])
      return;

    if (parentPrice[parentA] < parentPrice[parentB]) {
      parent[parentB] = parentA;
      parentPrice[parentB] = 0;
    } else {
      parent[parentA] = parentB;
      parentPrice[parentA] = 0;
    }
  }

  static int find(int x) {
    if (parent[x] == x) {
      return parent[x];
    }

    return parent[x] = find(parent[x]);
  }
}