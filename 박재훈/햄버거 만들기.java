import java.util.*;
class Solution {
    static class BurgerStatus{
      //재료 번호, 햄버거 순서 유지 중인지, 유지 중이라면 몇단계까지 쌓였는지 
        int ingredient;
        boolean canMakeHamburger;
        int cnt;
        public BurgerStatus(int ingredient, boolean canMakeHamburger, int cnt){
            this.ingredient = ingredient;
            this.canMakeHamburger = canMakeHamburger;
            this.cnt = cnt;
        }
    }
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<BurgerStatus> stack = new Stack<>();
        int len = ingredient.length;
        for(int i = 0; i < len; i++){
            if(stack.isEmpty()){
                stack.push(new BurgerStatus(ingredient[i], false, 0));
            }else{
              //가장 최근 재료와 햄버거 제작 상태 확인
                BurgerStatus recent = stack.peek();
                boolean canMakeHamburger = recent.canMakeHamburger;
                int cnt = recent.cnt;
              //빵이 나오면 햄버거 만들 준비완료, 단계리셋
                if(recent.ingredient == 1){
                    canMakeHamburger = true;
                    cnt = 0;
                }

              //다음 재료보고 햄버거 순서 지키고 있는지 확인
                if(canMakeHamburger && check(recent.ingredient, ingredient[i])){
                    cnt++;
                }else{
                    canMakeHamburger = false;
                    cnt = 0;
                }
                
                stack.push(new BurgerStatus(ingredient[i], canMakeHamburger, cnt));
              //1 2 3 1순으로 쌓였으면 스택에서 제거
                if(cnt == 3){
                    answer++;
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                }
            }
        }
        return answer;
    }
    static boolean check(int front, int back){
        int temp = front + 1;
        if(temp == 4){
            temp = 1;
        }
        return temp == back;
    }
}
