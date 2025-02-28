import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] aData = null;
  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    aData = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      aData[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(aData);

    StringBuilder sb = new StringBuilder();
    M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      int x = Integer.parseInt(st.nextToken());
      sb.append(binarySearch(x) + '\n');
    }

    System.out.println(sb.toString());
  }

  static String binarySearch(int target) {
    int sp = 0;
    int ep = N;

    if (target > aData[N - 1])
      return "0";

    while (sp <= ep) {
      int mid = (sp + ep) / 2;

      if (aData[mid] == target) {
        return "1";
      }

      if (aData[mid] < target) {
        sp = mid + 1;
      } else {
        ep = mid - 1;
      }
    }

    return "0";
  }
}