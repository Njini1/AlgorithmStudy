import java.util.*;

class Solution {
  public ArrayList<int[]> solution(int n, int[][] build_frame) {
    boolean[][][] map = new boolean[n + 1][n + 1][2]; // [기둥, 보]

    for (int[] frame : build_frame) { // [x, y, a, b] - (x, y) : 기둥, 보를 설치 또는 삭제할 교차점의 좌표, a: 0은 기둥, 1은 보, b: 0은 삭제,
                                      // 1은 설치
      int x = frame[0];
      int y = frame[1];
      int type = frame[2]; // 0: 기둥, 1: 보
      int cmd = frame[3]; // 0: 삭제, 1: 설치
      if (type == 0) { // 기둥이고
        if (cmd == 0) { // 삭제라면
          map[y][x][0] = false;
          if (!canDelete(n, map)) {
            map[y][x][0] = true;
          }
        } else {
          if (checkBuildPillar(n, map, x, y)) {
            map[y][x][0] = true;
          }
        }
      } else { // 보이고
        if (cmd == 0) { // 삭제라면
          map[y][x][1] = false;
          if (!canDelete(n, map)) {
            map[y][x][1] = true;
          }
        } else {
          if (checkBuildBeam(map, x, y)) {
            map[y][x][1] = true;
          }
        }
      }
    }

    // 결과 계산
    ArrayList<int[]> answer = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        if (map[i][j][0] == true) {
          answer.add(new int[] { j, i, 0 });
        }
        if (map[i][j][1] == true) {
          answer.add(new int[] { j, i, 1 });
        }
      }
    }

    Collections.sort(answer, (a, b) -> {
      if (a[0] == b[0]) {
        if (a[1] == b[1]) {
          return a[2] - b[2];
        }
        return a[1] - b[1];
      }
      return a[0] - b[0];
    });

    return answer;
  }

  public boolean canDelete(int n, boolean[][][] map) {
    for (int ny = 0; ny <= n; ny++) {
      for (int nx = 0; nx <= n; nx++) {
        if (map[ny][nx][0] && !checkBuildPillar(n, map, nx, ny))
          return false;
        if (map[ny][nx][1] && !checkBuildBeam(map, nx, ny))
          return false;
      }
    }
    return true;
  }

  public boolean checkBuildPillar(int n, boolean[][][] map, int x, int y) {
    // 기둥은 바닥 위에 있거나, 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    if (y == 0 || (x > 0 && map[y][x - 1][1]) || map[y][x][1] || (y > 0 && map[y - 1][x][0]))
      return true;

    return false;
  }

  public boolean checkBuildBeam(boolean[][][] map, int x, int y) {
    // 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    if ((y > 0 && map[y - 1][x][0] || map[y - 1][x + 1][0])
        || (x > 0 && map[y][x - 1][1] && map[y][x + 1][1]))
      return true;

    return false;
  }
}
