import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
  static final int PLAYER_NUM = 11;
  static int answer;
  static ArrayList<int[]>[] players = null;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int TC = Integer.parseInt(br.readLine());
    // 테스트 케이스
    for (int tc = 0; tc < TC; tc++) {
      answer = 0;
      // 11명의 선수 능력 데이터 받기
      players = new ArrayList[PLAYER_NUM];
      for (int pn = 0; pn < PLAYER_NUM; pn++) {
        players[pn] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int position = 0; position < PLAYER_NUM; position++) {
          int value = Integer.parseInt(st.nextToken());
          if (value > 0) {
            players[pn].add(new int[] { position, value });
          }

        }
      }

      // 선발 명단 탐색
      for (int[] player : players[0]) {
        boolean[] selected = new boolean[PLAYER_NUM];
        int sum = 0;
        selected[player[0]] = true;
        bruteforce(1, selected, sum + player[1]);
      }

      System.out.println(answer);
    }
  }

  static void bruteforce(int playerIdx, boolean[] selected, int sum) {
    if (playerIdx == PLAYER_NUM) {
      answer = Math.max(answer, sum);
      return;
    }

    for (int[] player : players[playerIdx]) {
      if (!selected[player[0]]) {
        selected[player[0]] = true;
        bruteforce(playerIdx + 1, selected, sum + player[1]);
        selected[player[0]] = false;
      }
    }
  }
}
