import java.util.*;
import java.io.*;

class Solution {
    
    int answer=Integer.MAX_VALUE;
     
    void bfs(int n, int[][] wires, int exclude){
        
        boolean[] isVisited = new boolean[n+1];
        
        List<List<Integer>> lists = new ArrayList<>();
        
        for(int i=0; i<=n; i++) lists.add(new ArrayList<>());
        
        for(int i=0; i<wires.length; i++){
            if(i==exclude) continue;
            int first=wires[i][0];
            int second=wires[i][1];
            
            lists.get(first).add(second);
            lists.get(second).add(first);
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        int cnt=1;            
        q.offer(1);
        isVisited[1]=true;
        
        while(!q.isEmpty()){
            int curQ=q.poll();
            
            for(int edge : lists.get(curQ)){
                if(isVisited[edge]) continue;
                
                isVisited[edge]=true;
                cnt++;
                q.offer(edge);
            }
        }
        int absValue=Math.abs(n-2*cnt);
        answer=Math.min(answer,absValue); 
    }
    
    public int solution(int n, int[][] wires) {
        
        for(int i=0; i<n; i++) bfs(n,wires,i);
        
        return answer;
    }
}