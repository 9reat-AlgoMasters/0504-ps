import java.util.*;
class Solution {
    static class Route{
        int r, c;
        StringBuilder sb;
        public Route(int r, int c, StringBuilder sb){
            this.r = r;
            this.c = c;
            this.sb = sb;
        }
    }
  //d l r u 순(사전순 빠르게)
    static int[] dr = {1,0,0,-1};
    static int[] dc = {0,-1,1,0};
    static String answer = "z";
    static int N ,M ,K, R, C;
    static char[][] map;
    static boolean found = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        R = r;
        C = c;

        map = new char[n][m];
        map[x - 1][y - 1] = 'S';
        map[r - 1][c - 1] = 'E';

        dfs(new Route(x-1,y-1,new StringBuilder()));


        return answer.equals("z")? "impossible":answer;
    }
    static boolean checkRange(int r, int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
    static void dfs(Route cur){
      //답 찾았으면 더 돌 필요x(무조건 사전순 뒤임)
        if(found) return;
        int dist = Math.abs(cur.r - (R-1)) + Math.abs(cur.c - (C-1));
        int passed = cur.sb.length();
      //남은 거리와 지나온 거리로 K만들기 가능한 경우만
        if(dist%2 == (K-passed)%2 && dist <= K-passed){
          //도착했고 이동거리가 K면 찾음
            if(map[cur.r][cur.c] == 'E' && passed == K){
                  answer = cur.sb.toString();
                  found = true;
                
                return;
            }
          
            for(int i = 0; i < 4; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                char ch;
                if(i == 0){
                    ch = 'd';
                }else if(i == 1){
                    ch = 'l';
                }else if(i == 2){
                    ch = 'r';
                }else{
                    ch = 'u';
                }
                if(checkRange(nr, nc) && passed < K ){
                    dfs(new Route(nr,nc, cur.sb.append(ch)));
                    cur.sb.setLength(passed);
                }
            }
        }
    }
}
