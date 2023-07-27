class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        long sum1 = 0, sum2 = 0;
        int size = queue1.length;
        for(int i = 0; i < size; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
      //두 큐 합 더해서 홀수면 어차피 같게 못 만듦
        if((sum1 + sum2) % 2 == 1){
            answer = -1;
        }else{
            int idx1 = 0, idx2 = 0;
            long half = (sum1 + sum2) / 2; 
          //queue1을 기준으로 queue1에 target만큼 더해줘야 합을 같게 할 수 있음
            long target = half - sum1;
          //queue1 합 변화량
            long exchangeValue = 0;
          //queue1 합이 half될 때까지 반복
            while(target != exchangeValue){
              //각 큐의 인덱스가 자신의 큐, 반대쪽 큐 원소들을 모두 돌았는데 half로 못만들었으면 방법 없는 것
                if(idx1 > 2 * size || idx2 > 2 * size){
                    answer = -1;
                    break;
                }
              //queue1에서 더 빼줘야함
                if(target < exchangeValue){
                  //먼저 queue1에서 탐색, 다 봤으면 queue2에서 탐색
                    if(idx1 < size){
                        exchangeValue -= queue1[idx1++];
                    }else{
                        exchangeValue -= queue2[idx1++%size];
                    }
                }else{
                    if(idx2 < size){
                        exchangeValue += queue2[idx2++];
                    }else{
                        exchangeValue += queue1[idx2++%size];
                    }
                }
                answer++;
            }
        }
        
        
        return answer;
        
    }
}
