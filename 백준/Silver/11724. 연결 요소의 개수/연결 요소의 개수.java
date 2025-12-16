import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int count = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            // 새 연결요소 시작
            count++;
            visited[i] = true;
            stack.push(i);

            while (!stack.isEmpty()) {
                int cur = stack.pop();
                for (int nxt : graph[cur]) {
                    if (visited[nxt]) continue;
                    visited[nxt] = true;
                    stack.push(nxt);
                }
            }
        }

        System.out.println(count);
    }
}
