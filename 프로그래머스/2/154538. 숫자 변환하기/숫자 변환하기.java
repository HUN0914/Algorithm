import java.util.*;

/*
전형적인 dfs 문제 같음
*/
class Solution {
    
    static int minimum=987654321;
    
    class Info {
        int value;
        int cnt;
        
        Info(int value, int cnt){
            this.value=value;
            this.cnt=cnt;
        }
    }
    
    void bfs(int x, int y, int n, int cnt){
        
        boolean isVisited[] = new boolean[1000001];
        
        Queue<Info> q = new LinkedList<>();
        
        q.offer(new Info(x,0));
        
        isVisited[x]=true;
        
        while(!q.isEmpty()){
            Info info = q.poll();
            
            if(info.value==y) minimum=Math.min(minimum, info.cnt);
            
            if(info.value*3<=y&&isVisited[info.value*3]==false){
                isVisited[info.value*3]=true;
                q.offer(new Info(info.value*3,info.cnt+1));
            }
            
            if(info.value*2<=y&&isVisited[info.value*2]==false){
                isVisited[info.value*2]=true;
                q.offer(new Info(info.value*2,info.cnt+1));
            }

            if(info.value+n<=y&&isVisited[info.value+n]==false){
                isVisited[info.value+n]=true;
                q.offer(new Info(info.value+n,info.cnt+1));
            }             
            
        }

    }
    
    public int solution(int x, int y, int n) {
        
        bfs(x,y,n,0);
        
        if(minimum==987654321) return -1;
        else return minimum;
    }
}