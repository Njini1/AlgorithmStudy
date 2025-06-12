import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 친구 데이터 받기
    int N = Integer.parseInt(br.readLine());
    ArrayList<Integer>[] data = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      data[i] = new ArrayList<>();
      String row = br.readLine();
      for (int j = 0; j < N; j++) {
        if (row.charAt(j) == 'Y') {
          data[i].add(j);
        }
      }
    }

    // 가장 유명한 사람의 2-친구의 수 구하기
    int answer = 0;
    for (int i = 0; i < N; i++) {
      boolean[] tmp = new boolean[N];
      for (Integer friendsIdx1 : data[i]) {
        tmp[friendsIdx1] = true;
        for (Integer friendsIdx2 : data[friendsIdx1]) {
          tmp[friendsIdx2] = true;
        }
      }

      int count = -1;
      for (int j = 0; j < N; j++) {
        if (tmp[j])
          count += 1;
      }

      answer = Math.max(answer, count);
    }

    System.out.println(answer);
  }
}
