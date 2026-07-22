import java.util.*;

/*
철수가 가진 카드 중 모든 숫자 나눌 수 O
영희가 가진 카드 중 모든 숫자 나눌 수 X

-> 양의 정수 a
철수 배열에서의 공약수들 찾기 (배열의 최댓값 까지 기준으로 해서)
영희 배열에서의 공약수들 찾기 (배열의 최댓값 까지 기준으로 해서)

둘을 비교했을 때 같지 않은 공약수 배열 중 최댓값 찾기 (양쪽에서)

50만이면 2중 포문하면 터짐

각각 정렬하고 두 배열 비교햇을 때 같은 값 있으면 제거? -> 이 또한 2중 포문 (50만*50만)

*/

class Solution {

    int answer = 0;
    
    int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
    
    boolean isValid(int val, int[] arr){
        for(int v: arr){
            if(v%val==0) return false;
        }
        return true;
    }
    
    void game(int[] arrayA, int[] arrayB){
        
        int valueA=arrayA[0];
        int valueB=arrayB[0];
        
        for(int i=1; i<arrayA.length; i++) valueA=gcd(valueA, arrayA[i]);

        for(int i=1; i<arrayB.length; i++) valueB=gcd(valueB, arrayB[i]); 
        
        if(isValid(valueA,arrayB)) answer=Math.max(valueA,answer);
        if(isValid(valueB,arrayA)) answer=Math.max(valueB,answer);
    }
    
    public int solution(int[] arrayA, int[] arrayB) {
        
        game(arrayA,arrayB);
        return answer;
    }
}