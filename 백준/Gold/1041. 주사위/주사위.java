import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static final int DICE_SIZE = 6;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long answer = 0;
    int min1 = Integer.MAX_VALUE;
    int min2 = Integer.MAX_VALUE;
    int min3 = Integer.MAX_VALUE;
    int midMin = Integer.MAX_VALUE;
    int sum = 0;

    // 데이터 받기
    long N = Integer.parseInt(br.readLine()); // N×N×N크기의 정육면체
    int[] nums = new int[DICE_SIZE]; // 주사위의 숫자들
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < DICE_SIZE; i++) {
      nums[i] = Integer.parseInt(st.nextToken()); // A: 0, B: 1, C: 2, D: 3, E: 4, F: 5
      min1 = Math.min(min1, nums[i]);
      sum += nums[i];
    }

    // N = 1일 때는 5개의 합으로 구함
    if (N == 1) {
      answer = sum;
      for (int i = 0; i < DICE_SIZE; i++) {
        answer = Math.min(answer, sum - nums[i]);
      }

      System.out.println(answer);
      System.exit(0);
    }

    // A를 위로 향했을 떄 가운데의 숫자 중 2개를 선택했을 때의 최소 합
    midMin = Math.min(midMin, nums[1] + nums[2]);
    midMin = Math.min(midMin, nums[1] + nums[3]);
    midMin = Math.min(midMin, nums[2] + nums[4]);
    midMin = Math.min(midMin, nums[3] + nums[4]);

    // 2개의 합 최솟값 구하기
    // AB/AC/AD/AE/DE/CE/BC/BD/BF/DF/EF/CF => 01/02/03/04 /34/24/12/13/ 15/35/45/25
    for (int i = 1; i <= 4; i++) {
      min2 = Math.min(min2, nums[0] + nums[i]);
    }
    for (int i = 1; i <= 4; i++) {
      min2 = Math.min(min2, nums[5] + nums[i]);
    }
    min2 = Math.min(midMin, min2);

    // 3개의 합 최솟값 구하기
    // 1. A는 필수, BC/CE/DE/BD 중 선택
    // 2. F는 필수, BC/CE/DE/BD
    min3 = Math.min(min3, nums[0] + midMin);
    min3 = Math.min(min3, nums[5] + midMin);

    // 보이는 5개의 면에 쓰여 있는 수의 합의 최솟값을 계산
    long count3 = min3 * 4;
    long count2 = (8 * N - 12) * min2;
    long count1 = (5 * N * N - 16 * N + 12) * min1;
    answer = count1 + count2 + count3;
    System.out.println(answer);
  }
}

/*
 * 
 * 3개가 최소값
 * 1. A는 필수, BC/CE/DE/BD 중 선택
 * 2. F는 필수, BC/CE/DE/BD
 * 
 * 2개가 최소값
 * 1. AB/AC/AD/AE/DE/EC/BC/BD/FB/FD/FE/FC
 * 
 * 1개가 최솟값
 * 1. A/B/C/D/E/F
 * 
 * N = 1, 5개의 합
 * N = 2, 3개 합은 4개, 2개 합은 2개
 * N = 3, 3개 합은 4개, 2개 합은 12개, 1개 합은 9개
 * N = 4, 3개 합은 4개, 2개 합은 20개, 1개 합은 28개 -> 52개
 * n = 5, 3개 합은 4개, 2개 합은 28개, 1개 합은 41개
 * => 3개 합은 4개 고정, 2개 합은 (N - 2) * 4 + (N - 1) * 4 = 4N - 8 + 4N - 4 = 8N - 12,
 * 1개 합은 (N - 2) * (N - 2) + (N - 1) * (N - 2) * 4 = N2 - 4N + 4 + 4N2 -12N + 8
 * = 5N2 - 16N + 12
 */