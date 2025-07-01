import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int X = Integer.parseInt(st.nextToken());
    int Y = Integer.parseInt(st.nextToken());

    int gcd = findGcd(X, Y);
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= gcd; i++) {
      if (gcd % i == 0) {
        sb.append(i + " " + X / i + " " + Y / i).append('\n');
      }
    }

    System.out.println(sb.toString());
  }

  static int findGcd(int num1, int num2) {
    if (num1 % num2 == 0) {
      return num2;
    }

    return findGcd(num2, num1 % num2);
  }
}
