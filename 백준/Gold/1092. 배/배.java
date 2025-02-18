import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    Integer[] crane = new Integer[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      crane[i] = Integer.parseInt(st.nextToken());
    }

    int M = Integer.parseInt(br.readLine());
    Integer[] boxArr = new Integer[M];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      boxArr[i] = Integer.parseInt(st.nextToken());
    }

    // 크레인과 박스를 내림차순 정렬
    Arrays.sort(crane, (a, b) -> b - a);
    Arrays.sort(boxArr, (a, b) -> b - a);

    // 가장 강한 크레인이 가장 무거운 박스를 옮길 수 없는 경우 -> 불가능 (-1 출력)
    if (crane[0] < boxArr[0]) {
      System.out.println(-1);
      return;
    }

    int time = 0;
    int boxIdx = 0; // 현재 옮겨야 할 박스의 인덱스
    boolean[] moved = new boolean[M]; // 박스 이동 여부
    int movedCount = 0; // 옮긴 박스 개수

    while (movedCount < M) {
      int craneIdx = 0; // 현재 크레인
      while (craneIdx < N && boxIdx < M) {
        if (!moved[boxIdx] && crane[craneIdx] >= boxArr[boxIdx]) {
          moved[boxIdx] = true;
          movedCount++;
          craneIdx++;
        }
        boxIdx++;
      }
      time++;
      boxIdx = 0; // 다시 처음부터 탐색
    }

    System.out.println(time);
  }
}
