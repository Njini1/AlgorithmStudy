import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // H, W, X, Y
    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());
    int Y = Integer.parseInt(st.nextToken());

    int[][] arrB = new int[H + X][W + Y];
    for (int i = 0; i < H + X; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < W + Y; j++) {
        arrB[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int cnt = 0;
    int[][] result = new int[H][W];
    for (int y = 0; y < H; y++) {
      for (int x = 0; x < W; x++) {
        result[y][x] = arrB[y][x];
      }
    }

    // H, W, X, Y
    for (int y = 0; y < H + X; y++) {
      for (int x = 0; x < W + Y; x++) {
        if (y >= X && y < H && x < W && x >= Y) {
          result[y][x] = arrB[y][x] - result[y - X][x - Y];
        }
      }
    }

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println();
    }
  }
}