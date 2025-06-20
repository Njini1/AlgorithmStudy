import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = br.readLine();
    int q = Integer.parseInt(br.readLine()); // 질문의 수

    int size = input.length();
    int[][] data = new int[size + 1][26];
    for (int i = 1; i <= size; i++) {
      int value = input.charAt(i - 1) - 'a';
      data[i] = Arrays.copyOf(data[i - 1], 26);
      data[i][value] += 1;
    }

    StringBuilder sb = new StringBuilder();
    // input의 l번째 문자부터 r번째 문자 사이에 a가 몇 번 나타나는지 구함.
    for (int i = 0; i < q; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int target = st.nextToken().charAt(0) - 'a';
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken()) + 1;

      int result = 0;
      if (r > size) {
        result = data[r - 1][target] + 1 - data[l][target];
      } else {
        result = data[r][target] - data[l][target];
      }

      sb.append(result).append("\n");
    }

    System.out.println(sb.toString());
  }
}
