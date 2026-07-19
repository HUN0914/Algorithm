import java.util.*;

/*
가로 2 
세로 n인 바닥 채울려고함.

기본 직사각형 : 가로 2, 세로 1
처음 세로로 세워서 세로2 가로1인 상태로 n 만드는거랑
+1일때, +2일때를 각각 산정해서 dfs해서 크기 구하기

dp 점화식
dp[0]=0;
dp[1]=1;
dp[2]=2
dp[3]=3;
dp[4]=5;
dp[5]=8;

(1,1,1,1,1)
(1,1,1,2)
(1,1,2,1)
(1,2,1,1)
(2,1,1,1)
(1,2,2)
(2,1,2)
(2,2,1)

dp[6]=

*/

class Solution {
    
    int[] dp= new int[60001];
    
    int dynamic(int n){
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        
        if(n==0) return 0;
        else if (n==1) return 1;
        else if (n==2) return 2;
        
        for(int i=3; i<=n; i++) {
            dp[i]=(dp[i-1]+dp[i-2])%1000000007;
        }
        
        return dp[n];
    }
    
    public int solution(int n) {

        int answer = dynamic(n);
        return answer;
    }
}