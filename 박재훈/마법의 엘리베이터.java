import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
      //0이 될때까지 일의 자리 숫자 확인
        while(storey > 0){
            int remain = storey%10;
            storey /= 10;
          //1의 자리가 5보다 작으면 버리는게 이득
            if(remain < 5){
                answer += remain;
            }else if(remain > 5){
              //5보다 크면 올리는게 이득 (ex-> 6 내리면 6번 눌러야함 올리면 4+1)
                answer += (10-remain);
                storey++;
            }else {
              //정확히 5면 그 앞자리까지 보고 올릴지 내릴지 판단
                answer += 5;
                if(storey%10 >= 5){
                    storey++;
                }
            }
        }
        return answer;
    }
}
