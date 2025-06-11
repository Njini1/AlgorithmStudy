import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static final int PACKAGE_SIZE = 6;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] packages = new int[M * 2];
    int[] piece = new int[M];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int d1 = Integer.parseInt(st.nextToken());
      int d2 = Integer.parseInt(st.nextToken());
      packages[i] = d1;
      packages[i + M] = d2 * PACKAGE_SIZE;
      piece[i] = d2;
    }

    Arrays.sort(packages);
    Arrays.sort(piece);

    int result1 = packages[0] * (N / PACKAGE_SIZE + (N % PACKAGE_SIZE == 0 ? 0 : 1));
    int result2 = (packages[0] * (N / PACKAGE_SIZE)) + (piece[0] * (N % PACKAGE_SIZE));
    System.out.println(Math.min(result1, result2));
  }
}
