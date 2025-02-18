import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] data = new int[N];
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int point = 0;
    int[] tmp = new int[N];
    tmp[0] = data[0];
    for (int i = 1; i < N; i++) {
      if (tmp[point] < data[i]) {
        tmp[++point] = data[i];
      } else {
        int left = 0;
        int right = point;
        int idx = 0;
        while (left <= right) {
          int mid = (left + right) / 2;
          int minValue = Integer.MAX_VALUE;

          if (tmp[mid] == data[i]) {
            idx = mid;
            tmp[mid] = data[i];
            break;
          } else if (tmp[mid] > data[i]) {
            right = mid - 1;
            if (tmp[mid] < minValue) {
              minValue = tmp[mid];
              idx = mid;
            }
          } else {
            left = mid + 1;
          }
        }
        tmp[idx] = data[i];
      }
    }

    System.out.println(point + 1);
  }
}