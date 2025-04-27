import java.util.*;

class Solution {
    public static class Task implements Comparable<Task> {
        int num;
        int startTime;
        int time;

        public Task(int num, int startTime, int time) {
            this.num = num;
            this.startTime = startTime;
            this.time = time;
        }

        @Override
        public int compareTo(Task t) {
            if (this.time == t.time) {
                return this.startTime - t.startTime;
            }
            return this.time - t.time;
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청 시간 순 정렬

        PriorityQueue<Task> waitQ = new PriorityQueue<>();
        int time = 0; // 현재 시간
        int sum = 0;  // 요청부터 종료까지 걸린 시간 총합
        int jobIdx = 0; // jobs 배열 인덱스
        int num = 0; // 작업 번호
        int count = 0; // 처리한 작업 수

        while (count < jobs.length) {
            // 현재 시각에 요청한 작업들 waitQ에 추가
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= time) {
                waitQ.offer(new Task(num++, jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }

            if (!waitQ.isEmpty()) {
                Task curTask = waitQ.poll();
                time += curTask.time; // 현재 시간에 소요시간 더함
                sum += (time - curTask.startTime); // 요청부터 끝날 때까지 걸린 시간
                count++;
            } else {
                // 대기열이 비어있으면 시간 한 칸 증가 (새로운 작업 기다리기)
                time++;
            }
        }

        answer = sum / jobs.length;
        return answer;
    }
}
