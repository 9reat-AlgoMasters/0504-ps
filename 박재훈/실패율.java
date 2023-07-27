import java.util.*;
class Solution {
    static class FailRate implements Comparable<FailRate>{
      //스테이지 번호, 실패율
        int num;
        double rate;
        public FailRate(int num, double rate){
            this.num = num;
            this.rate = rate;
        }

        @Override
        public int compareTo(FailRate o) {
            if(o.rate == this.rate){
                return Integer.compare(this.num, o.num);
            }
            return Double.compare(o.rate, this.rate);
        }
    }

    public int[] solution(int N, int[] stages) {
      //정렬
        Arrays.sort(stages);
        
        PriorityQueue<FailRate> pq = new PriorityQueue<>();
      //1부터 N+1까지 도달한 인원 수 저장할 배열
        int[] reached = new int[N+2];
        int cnt = 1;
        int size = stages.length;
      //초기 prev = stages에서 가장 큰 수 => 전체 유저 중 가장 높이 간 스테이지 번호
        int prev = stages[size-1];
      //prev보다 큰 스테이지들은 아무도 도달 못함
        for(int i = N; i > prev; i--){
            reached[i] = 0;
            pq.add(new FailRate(i, 0));
        }
      //나머지 stages원소들 탐색
        for(int i = size-2; i >= 0; i--){
            int cur = stages[i];
            int limit = prev - cur;
          //스테이지 별 도달 인원 카운트, 저장
            for(int j = 0; j < limit; j++){
                int idx = prev - j;
                reached[idx] = cnt;
                if(idx <= N){
                  //idx단계 도달인원 - idx+1단계 도달인원 = idx단계 도달은 했는데 깨진 못한 인원
                    double rate = (double)(cnt-reached[idx+1]) / cnt;
                    pq.add(new FailRate(idx, rate));
                }
            }
            cnt++;
            prev = cur;
        }
      //마지막으로 본 것이 N+1 => N스테이지까지의 실패율만 보면 되므로 넘어감 
        if(prev != N+1){
            reached[prev] = cnt;
            double rate = (double)(cnt-reached[prev+1]) / cnt;
            pq.add(new FailRate(prev, rate));
        }
      //1단계부터 prev(stages에서 제일 작은 수)전 까지 실패율 0
        for(int i = 1; i < prev; i++){
            pq.add(new FailRate(i, 0));
        }
        
        int[] answer = new int[N];
        int idx = 0;
        while(!pq.isEmpty()){
            FailRate f = pq.poll();
            answer[idx++] = f.num;
        }
        return answer;
    }
}
