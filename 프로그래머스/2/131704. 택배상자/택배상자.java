import java.util.*;

/*
처음 벨트는 1,2,3, ... 이상태로 옴
오더에 맞게 써야 하기 떄문에 [4,3,2,1,5] 처음 숫자만큼 빼주고
이만큼 임시 리스트 박스에 저장 <이건 stack> 
꺼냈을때 안맞을 경우 return 

임시 리스트 컨테이너 top


오더 사이즈라는 큰 반복문 안에서
현재 담아야하는 cnt도 선언해주고 (초기1)
여기서 맞지 않을 경우 값을 계속 담아주는 로직을 추가
*/

class Solution {
    
    public int solution(int[] order) {
        int answer = 0;
        int cnt=1;
        
        Stack<Integer> st = new Stack<>();
        for(int target : order){
            
            while(cnt < target){
                st.push(cnt);
                cnt++;
            }
            
            if(cnt==target){
                cnt++;
                answer++;
            }else if (!st.isEmpty() && st.peek()== target){
                st.pop();
                answer++;
            }else {
                break;
            }
        }
        
        return answer;
    }
}