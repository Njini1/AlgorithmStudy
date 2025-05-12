import java.io.*;
import java.util.*;

public class Main {
  static int[] parents = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); // 사람의 수
    int M = Integer.parseInt(st.nextToken()); // 파티의 수

    // 처음에는 자기 자신을 부모로
    parents = new int[N + 1];
    for (int i = 0; i <= N; i++) {
      parents[i] = i;
    }

    // 진실을 아는 사람들
    st = new StringTokenizer(br.readLine());
    int cnt = Integer.parseInt(st.nextToken());
    for (int i = 0; i < cnt; i++) {
      int idx = Integer.parseInt(st.nextToken());
      parents[idx] = 0; // 진실을 아는 사람은 부모를 0으로 만들기
    }

    // 파티 참여자 받기
    int answer = 0;
    ArrayList<Integer>[] data = new ArrayList[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      data[i] = new ArrayList<>();
      int firstData = Integer.parseInt(st.nextToken());
      data[i].add(firstData);
      for (int j = 1; j < size; j++) {
        int value = Integer.parseInt(st.nextToken());
        data[i].add(value);
        union(firstData, value);
      }
    }

    // 갈 수 있는 파티 계산
    for (ArrayList<Integer> party : data) {
      boolean go = true;
      for (Integer person : party) {
        if (find(parents[person]) == 0) {
          go = false;
          break;
        }
      }
      if (go)
        answer++;
    }

    System.out.println(answer);
  }

  static int find(int x) {
    if (parents[x] != x) {
      return parents[x] = find(parents[x]);
    } else {
      return parents[x];
    }
  }

  static void union(int a, int b) {
    int parentA = find(parents[a]);
    int parentB = find(parents[b]);

    if (parentA != parentB) {
      if (parentA < parentB) {
        parents[parentB] = parentA;
      } else {
        parents[parentA] = parentB;
      }
    }
  }
}
