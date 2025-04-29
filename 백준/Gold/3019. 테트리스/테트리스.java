import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[] height = null;
  static int C = 0;

  public static void main(String[] args) throws IOException {
    int answer = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());

    height = new int[C];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      height[i] = Integer.parseInt(st.nextToken());
    }

    switch (P) {
      case 1:
        answer = shape1();
        break;
      case 2:
        answer = shape2();
        break;
      case 3:
        answer = shape3();
        break;
      case 4:
        answer = shape4();
        break;
      case 5:
        answer = shape5();
        break;
      case 6:
        answer = shape6();
        break;
      case 7:
        answer = shape7();
        break;
    }

    System.out.println(answer);
  }

  // | 자 모양
  public static int shape1() {
    int count = 0;
    // 일자 모양 -> 모든 열에 가능
    count += C;

    // 누워있는 모양 -> 4칸이 연속으로 같은 값인 열에만 가능
    count += sameSpace(4);

    return count;
  }

  // ㅁ 모양
  public static int shape2() {
    int count = 0;

    // 2칸이 연속으로 같은 값인 열에만 가능
    count += sameSpace(2);

    return count;
  }

  // _|- 모양
  public static int shape3() {
    int count = 0;

    // _|- , 첫칸, 두번째칸이 연속으로 같은 값이고 세번째 칸은 한칸 더 높은 열에 가능
    for (int i = 0; i <= C - 3; i++) {
      if ((height[i] == height[i + 1]) && (height[i] + 1 == height[i + 2])) {
        count++;
      }
    }

    // 90도 회전
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] == height[i + 1] + 1) {
        count++;
      }
    }

    return count;
  }

  // -|_ 모양
  public static int shape4() {
    int count = 0;

    // 첫칸이 한칸 더 높고 두번째, 세번째칸이 연속으로 같은 값인 열에 가능
    for (int i = 0; i <= C - 3; i++) {
      if ((height[i] == height[i + 1] + 1) && (height[i + 1] == height[i + 2])) {
        count++;
      }
    }

    // 90도 회전
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] + 1 == height[i + 1]) {
        count++;
      }
    }

    return count;
  }

  // ㅗ 모양
  public static int shape5() {
    int count = 0;

    // 3칸이 연속으로 같은 값인 열에만 가능
    count += sameSpace(3);

    // 90도 회전, 첫칸보다 두번째칸이 한 칸 더 높음
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] + 1 == height[i + 1]) {
        count++;
      }
    }

    // 180도 회전, 첫번째, 세번째가 두번째보다 한 칸 더 높음
    for (int i = 0; i <= C - 3; i++) {
      if ((height[i] == height[i + 2]) && (height[i] == height[i + 1] + 1)) {
        count++;
      }
    }

    // 270도 회전, 첫번째가 두번째보다 한 칸 더 높음
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] == height[i + 1] + 1) {
        count++;
      }
    }

    return count;
  }

  // __| 모양
  public static int shape6() {
    int count = 0;

    // 연속 3칸 같은 값
    count += sameSpace(3);

    // 90도 회전, 연속 2칸 같은 값
    count += sameSpace(2);

    // 180도 회전, 첫칸이 한칸 더 낮고 두번째, 세번째칸이 연속으로 같은 값인 열에 가능
    for (int i = 0; i <= C - 3; i++) {
      if ((height[i] + 1 == height[i + 1]) && (height[i + 1] == height[i + 2])) {
        count++;
      }
    }

    // 270도 회전, 첫번째가 두번째보다 두 칸 더 높음
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] == height[i + 1] + 2) {
        count++;
      }
    }

    return count;
  }

  // |__ 모양
  public static int shape7() {
    int count = 0;

    // 연속 3칸 같은 값
    count += sameSpace(3);

    // 90도 회전, 두번째가 첫번째보다 두 칸 더 높음
    for (int i = 0; i <= C - 2; i++) {
      if (height[i] + 2 == height[i + 1]) {
        count++;
      }
    }

    // 180도 회전, 세번째칸이 한칸 더 낮고 첫번째, 두번째칸이 연속으로 같은 값인 열에 가능
    for (int i = 0; i <= C - 3; i++) {
      if ((height[i] == height[i + 1]) && (height[i + 1] == height[i + 2] + 1)) {
        count++;
      }
    }

    // 270도 회전, 2칸 연속 같은 값
    count += sameSpace(2);

    return count;
  }

  // num 개의 칸이 연속으로 같은 값인 열에만 가능
  public static int sameSpace(int num) {
    int count = 0;
    for (int i = 0; i <= C - num; i++) {
      boolean value = true;
      for (int j = i; j < i + num - 1; j++) {
        if (height[j] != height[j + 1]) {
          value = false;
          break;
        }
      }
      if (value)
        count++;
    }

    return count;
  }
}
