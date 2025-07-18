# [Gold III] 공주님의 정원 - 2457 

[문제 링크](https://www.acmicpc.net/problem/2457) 

### 성능 요약

메모리: 54968 KB, 시간: 512 ms

### 시간 및 난이도

- 중
- 3시간 이상

### 풀이 리뷰

- “5월 8일 피어서 6월 13일 지는 꽃은 5월 8일부터 6월 12일까지는 꽃이 피어 있고, 6월 13일을 포함하여 이후로는 꽃을 볼 수 없다는 의미”
→ 문제를 확인을 제대로 안하여 5월 8일부터 6월13일까지 꽃이 피어있다고 생각하여 품. 이후 예외 케이스가 나오면서 변경
- [5, 8, 6, 13] 이라는 값을 하나씩 저장하면서 월, 날짜를 따로 저장하고 월끼리 날짜끼리 비교함 → 다른 풀이를 보니 “월 * 100 + 일” 계산한 값으로 꽃이 피는 날 지는날 2개의 값만 저장함.
- 다른 사람 코드를 보니 나의 2중 for문도 필요없음. 한 번의 for문만 돌리고 마지막 꽃이 지는 날보다 탐색값의 꽃이 피는 날이 더 크다면, 마지막 꽃이 지는 날에 이전까지의 가장 늦은 꽃을 저장. 이러한 방식으로 한번의 for문으로 계속 탐색

### 풀이 키워드

- 정렬

### 풀이 방법

- 데이터를 꽃이 피는 월, 꽃이 피는 일, 꽃이 지는 월, 꽃이 지는 일 순으로 정렬
- 하나씩 꽃을 탐색하면서(idx) 마지막으로 꽃이 지는 날짜(endMonth, endDay)를 기준으로 다음 탐색값(tmp)의 꽃이 피는 날보다 작거나 같고 꽃이 지는 날보다 크면 계속 다음값 탐색. 그 중 꽃이 지는 날이 가장 늦은 것으로 선택해 endMonth, endDay에 저장
- 현재 기준값에서 다음 탐색값들 중 위의 조건에 해당되는 것이 없다면(cnt == 0) 그냥 기준값 idx를 + 1함. cnt > 0 보다 크다면 기준 값을 이미 탐색한 값을 제외 하고 idx = tmp + 1로 저장.
- 그리고 endMonth가 11보다 크다면 이미 답을 구한 것이므로 종료

### 시간 복잡도

- O(N*N) = 최악일 경우 O(100,000 * 100, 000)

### 노션 링크
https://www.notion.so/2457-212e550432f4802baf2ffe850a493143

### 분류

그리디 알고리즘, 정렬

### 제출 일자

2025년 6월 15일 18:42:01

### 문제 설명

<p>오늘은 공주님이 태어난 경사스러운 날이다. 왕은 이 날을 기념하기 위해 늘 꽃이 피어있는 작은 정원을 만들기로 결정했다.</p>

<p>총 N개의 꽃이 있는 데, 꽃은 모두 같은 해에 피어서 같은 해에 진다. 하나의 꽃은 피는 날과 지는 날이 정해져 있다. 예를 들어, 5월 8일 피어서 6월 13일 지는 꽃은 5월 8일부터 6월 12일까지는 꽃이 피어 있고, 6월 13일을 포함하여 이후로는 꽃을 볼 수 없다는 의미이다. (올해는 4, 6, 9, 11월은 30일까지 있고, 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며, 2월은 28일까지만 있다.)</p>

<p>이러한 N개의 꽃들 중에서 다음의 두 조건을 만족하는 꽃들을 선택하고 싶다.</p>

<ol>
	<li>공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.</li>
	<li>정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다. </li>
</ol>

<p>N개의 꽃들 중에서 위의 두 조건을 만족하는, 즉 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 꽃들을 선택할 때, 선택한 꽃들의 최소 개수를 출력하는 프로그램을 작성하시오. </p>

### 입력 

 <p>첫째 줄에는 꽃들의 총 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 각 꽃이 피는 날짜와 지는 날짜가 주어진다. 하나의 날짜는 월과 일을 나타내는 두 숫자로 표현된다. 예를 들어서, 3 8 7 31은 꽃이 3월 8일에 피어서 7월 31일에 진다는 것을 나타낸다. </p>

### 출력 

 <p>첫째 줄에 선택한 꽃들의 최소 개수를 출력한다. 만약 두 조건을 만족하는 꽃들을 선택할 수 없다면 0을 출력한다.</p>

