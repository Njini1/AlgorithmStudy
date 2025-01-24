import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    long minNum = Long.parseLong(st.nextToken());
    long maxNum = Long.parseLong(st.nextToken());

    long answer = maxNum - minNum + 1;
    long num = 2;
    boolean[] visit = new boolean[(int) answer];
    while (num * num <= maxNum) {
      long power = num * num;
      long startNum = minNum / power;
      startNum = startNum * power >= minNum ? startNum : startNum + 1;
      while (startNum * power <= maxNum) {
        long value = startNum * power;
        int tmp = (int) (value - minNum);
        if (!visit[tmp]) {
          answer--;
          visit[tmp] = true;
        }
        startNum++;
      }
      num++;
    }

    System.out.println(answer);
  }
}
