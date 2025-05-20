import java.io.*;
import java.util.*;

public class Main {
    static final int PAPER_SIZE = 10;
    static int[][] data = new int[PAPER_SIZE][PAPER_SIZE];
    static int[] pCount = new int[6]; // 색종이 크기 1~5
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < PAPER_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < PAPER_SIZE; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int y, int x, int cnt) {
        if (y == PAPER_SIZE) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (answer <= cnt) return;

        if (x == PAPER_SIZE) {
            dfs(y + 1, 0, cnt); // 다음 행으로
            return;
        }

        if (data[y][x] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (pCount[size] < 5 && canAttach(size, y, x)) {
                    attachOrDetach(0, size, y, x); // 붙이기
                    pCount[size]++;
                    dfs(y, x + 1, cnt + 1);
                    attachOrDetach(1, size, y, x); // 떼기
                    pCount[size]--;
                }
            }
        } else {
            dfs(y, x + 1, cnt); // 다음 칸
        }
    }

    static boolean canAttach(int size, int y, int x) {
        if (y + size > PAPER_SIZE || x + size > PAPER_SIZE) return false;

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (data[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void attachOrDetach(int mode, int size, int y, int x) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                data[i][j] = mode;
            }
        }
    }
}
