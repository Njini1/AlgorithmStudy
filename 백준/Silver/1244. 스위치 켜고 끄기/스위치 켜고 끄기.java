import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] onOff;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    // 스위치 불 켜짐 여부 값 저장
    N = Integer.parseInt(br.readLine()); // 스위치 개수
    onOff = new int[N + 1];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 1; i <= N; i++) {
      onOff[i] = Integer.parseInt(st.nextToken());
    }

    // 학생의 성별과 받은 수 저장
    int M = Integer.parseInt(br.readLine()); // 학생수
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int gender = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      // 스위치 불 변환
      if (gender == 1) {
        // 남자이면
        changeMale(num);
      } else {
        changeFemale(num);
      }
    }

    // 출력
    for (int i = 1; i <= N; i++) {
      sb.append(onOff[i]).append(" ");
      if (i % 20 == 0) {
        sb.append("\n");
      }
    }
    System.out.println(sb.toString());
  }

  // 성별이 남자일 때 스위치 변경
  private static void changeMale(int num) {
    for (int i = num; i <= N; i += num) {
      onOff[i] = onOff[i] == 1 ? 0 : 1;
    }
  }

  // 성별이 여자일 때 스위치 변경
  private static void changeFemale(int num) {
    int i = 1;
    onOff[num] = onOff[num] == 1 ? 0 : 1;
    while (true) {
      if ((num - i) < 1 || (num + i) > N || (onOff[num - i] != onOff[num + i])) {
        break;
      }
      onOff[num - i] = onOff[num - i] == 1 ? 0 : 1;
      onOff[num + i] = onOff[num + i] == 1 ? 0 : 1;
      i++;
    }
  }

}