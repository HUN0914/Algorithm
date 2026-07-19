import java.util.*;
class Solution {
    
    int answer=0;
    
    void game(int[] scoville, int K){
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int val : scoville) {
            pq.offer(val);
        }
        
        while(!pq.isEmpty()){
            int val = pq.poll();
            if(val<K){
                if(pq.isEmpty()){
                    answer=-1;
                    return;
                }
                int nextVal= pq.poll();
                int resultVal=val+(2*nextVal);
                pq.offer(resultVal);
                answer++;
            }
        }
    }
    
    public int solution(int[] scoville, int K) {
        game(scoville, K);
        return answer;
    }
}