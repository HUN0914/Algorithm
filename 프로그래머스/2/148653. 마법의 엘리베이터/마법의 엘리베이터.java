import java.util.*;
import java.io.*;

/*
10의 0승부터 무제한으로 가능
근데 우리는 0층을 가도록 설계
마이너스를 할 때에는 늘 절댓값 0보다 작아지면 안됨

그리디?
1. 일단 storey의 값이 10의 몇승까지 담아지는지 계산
2. 맨 처음 숫자가 5 up down인지 보기 (5는 중간값)
이렇게 풀면 또 안됨

2990이라 치면
3000 만들고 0만드는게 쉬움

1. 일단 맨 마지막 숫자가 5 초과인지 아닌지 확인
그에 따라 0부터 만들어줌.

2. 
1. 처음에 storey값에 해당하는 10의 배열을 지정
2. dfs로 10의 
이거 그리디임
1부터 *10 해주면서 쭉 올라가다가 그 값이 작아지면 그때 break;
*/

class Solution {
    
    int greedy(int storey){
        
        int result=0;
        //처음 10으로 나머지 구하고 (%) 그 값 기준 1로 몫 구해주면 됨
        int sumVal=10;
        int divVal=1;
        
        while(divVal<=storey){
            int value=(storey%sumVal)/divVal;
            if(value>5 || (value == 5 && (storey / sumVal) %10 >= 5)){
                result+=10-value;
                storey+=sumVal;
            }
            else {
                result+=value;
            }
            
            sumVal*=10;
            divVal*=10;
        }
        
        return result;
    }
    
    public int solution(int storey) {
        int answer = greedy(storey);
        return answer;
    }
}