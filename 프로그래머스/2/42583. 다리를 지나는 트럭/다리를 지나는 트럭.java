import java.util.*;

class Solution {
    
    class Info {
        int enterTime;
        int weight;
        
        Info(int enterTime, int weight){
            this.enterTime=enterTime;
            this.weight=weight;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Info> bridge = new LinkedList<>();
        
        int time=0;
        int index=0;
        int currentWeight=0;
        
        while(index < truck_weights.length || !bridge.isEmpty()){
            time++;
            
            if(!bridge.isEmpty() && time - bridge.peek().enterTime == bridge_length){
                currentWeight-=bridge.poll().weight;
            }
            
            if(index < truck_weights.length && bridge.size()< bridge_length &&
              currentWeight + truck_weights[index] <= weight) {
                bridge.offer(new Info(time, truck_weights[index]));
                currentWeight += truck_weights[index];
                index++;
            }
        }
        
        return time;
    }
}