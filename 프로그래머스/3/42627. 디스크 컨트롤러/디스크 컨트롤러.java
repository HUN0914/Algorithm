import java.util.*;

/*
작업 번호
작업 요청 시각
작업 소요 시간 저장해두는 대기 큐

우선순위 방향 : 작업 소요시간 빠른 것 > 작업 요청 시각 빠른 것 > 작업 번호 작은 것

스위칭 하는데 걸리는 시간은 X

jobs에 들어가 있는 것 : [작업 요청 되는 시점, 소요 시각]

jobs 번호 자체가 작업 번호

1번 작업의 시작시간이 0초가 될 수도 있음 => 무조건0번 작업이 먼저 시작 X

1. 우선순위큐에 위의 시점을 기준으로 값을 넣기
2. 큐에 값을 넣고 값을 꺼낼 때마다 cnt에 꺼낸 큐에 시간 값을 넣고, 그 큐에서 꺼낸 값 중 cnt- 작업 요청 시각을 arr에 넣어주기 
3. 배열에 그 값 다 더해서 평균 더하기 (jobs.크기만큼)
*/

class Solution {
    
    class Info{
        int runningTime;
        int requestTime;
        int number;
        
        Info(int runningTime, int requestTime, int number){
            this.runningTime=runningTime;
            this.requestTime=requestTime;
            this.number=number;
        }
    }
    
    int cal(int[][] jobs){
        
        int len=jobs.length;
        int totalTime=0;
        int result=0;
        
        int[] arr = new int[len];
        
        PriorityQueue<Info> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1.runningTime!=o2.runningTime) return Integer.compare(o1.runningTime, o2.runningTime);
            if(o1.requestTime!=o2.requestTime) return Integer.compare(o1.requestTime, o2.requestTime);
            if(o1.number!=o2.number) return Integer.compare(o1.number,o2.number);
            
            return 0;
        });
        
        int curCnt=0;
        int checkSizing=0;
        boolean[] isVisited = new boolean[len];
        
        while(true){
            
            if(checkSizing==len) break;
            
            for(int i=0; i<len; i++){
                
                if(isVisited[i]) continue;
                int requestTime=jobs[i][0];
                int runningTime=jobs[i][1];
                
                if(requestTime<=curCnt) {
                    pq.offer(new Info(runningTime,requestTime,i));
                    isVisited[i]=true;
                    checkSizing++; 
                }                
            }
 
            if(!pq.isEmpty()){
                
                Info info =  pq.poll();
                int runTime = info.runningTime;
                int reqTime = info.requestTime;
                int num = info.number;

                if(curCnt<reqTime) curCnt=reqTime;

                curCnt+=runTime;

                arr[num] = curCnt - reqTime;            
            } else curCnt++;            
            
        }
        
        if(!pq.isEmpty()){
            while(!pq.isEmpty()){
                
                Info info =  pq.poll();
                int runTime = info.runningTime;
                int reqTime = info.requestTime;
                int num = info.number;               
                
                if(curCnt<reqTime) curCnt=reqTime;

                curCnt+=runTime;

                arr[num] = curCnt - reqTime;   
            }
        }
        
        for(int i=0; i<len; i++) {
            System.out.println(arr[i]);
            result+=arr[i];
        }
        
        result/=len;
        
        return result;
    }
    
    public int solution(int[][] jobs) {
        int answer = cal(jobs);
        return answer;
    }
}