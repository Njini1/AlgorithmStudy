import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
  static String[] data;
  static String result;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) {
      data = br.readLine().split(" ");

      result = "no";
      checkWord(data[0], data[1], data[2]);
      sb.append("Data set " + i + ": " + result + "\n");
    }

    System.out.println(sb.toString());
  }

  public static void checkWord(String w1, String w2, String w3) {
    Queue<int[]> wordIndex = new ArrayDeque<>();
    wordIndex.add(new int[] { 0, 0, 0 }); // 첫번째 단어 인덱스, 두번째 단어 인덱스, 세번째 단어 인덱스
    boolean[][] visit = new boolean[w1.length() + 1][w2.length() + 1];
    visit[0][0] = true;
      
    while (!wordIndex.isEmpty()) {
      int[] index = wordIndex.poll();

      if (index[2] == w3.length()) {
        result = "yes";
        return;
      }

      if (index[0] < w1.length() && !visit[index[0] + 1][index[1]] && w1.charAt(index[0]) == w3.charAt(index[2])) {
        wordIndex.add(new int[] { index[0] + 1, index[1], index[2] + 1 });
        visit[index[0] + 1][index[1]] = true;
      }

      if (index[1] < w2.length() && !visit[index[0]][index[1] + 1] && w2.charAt(index[1]) == w3.charAt(index[2])) {
        wordIndex.add(new int[] { index[0], index[1] + 1, index[2] + 1 });
        visit[index[0]][index[1] + 1] = true;
      }
    }
  }
}
