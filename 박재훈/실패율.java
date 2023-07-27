import java.util.*;
class Solution {
    static class FailRate implements Comparable<FailRate>{
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
        Arrays.sort(stages);
        
        PriorityQueue<FailRate> pq = new PriorityQueue<>();
        int[] reached = new int[N+2];
        int cnt = 1;
        int size = stages.length;
        int prev = stages[size-1];
        for(int i = N; i > prev; i--){
            reached[i] = 0;
            pq.add(new FailRate(i, 0));
        }
        for(int i = size-2; i >= 0; i--){
            int cur = stages[i];
            int limit = prev - cur;
            for(int j = 0; j < limit; j++){
                int idx = prev - j;
                reached[idx] = cnt;
                if(idx <= N){
                    double rate = (double)(cnt-reached[idx+1]) / cnt;
                    pq.add(new FailRate(idx, rate));
                }
            }
            cnt++;
            prev = cur;
        }
        if(prev != N+1){
            reached[prev] = cnt;
            double rate = (double)(cnt-reached[prev+1]) / cnt;
            pq.add(new FailRate(prev, rate));
        }
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
