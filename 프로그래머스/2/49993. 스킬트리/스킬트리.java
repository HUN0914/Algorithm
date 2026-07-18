import java.util.*;

/*
반례찾기만 하면됨
skill의 크기만큼 반복문을 돌리는데
나온 글자 하나가 선행되어야 할 글자가 있는지?
그럼 진짜 그 영어 한글자 -'0'에 대한 boolean 배열 만들고
string 배열 하나 더 만들어서 그 스킬에 대한 선행되어야 할 영어들 (직전)값까지 다 받아놓은 상태에서
검사하면서 그게 다 있으면 넘어가기, 아니면 체크 안하기
*/

class Solution {

    boolean[] isVisited= new boolean[26];
    String[] alphabet= new String[26];
    
    void init(String skill){
        String previous="";
        
        for(char c : skill.toCharArray()){
            alphabet[c-'A']=previous;
            previous+=c;
        }
    }
    
    boolean check(String skillTree){
        isVisited = new boolean[26];
        
        for( char current : skillTree.toCharArray()){
            String pre = alphabet[current-'A'];
            
            if(pre == null) continue;
            
            for(char preVal : pre.toCharArray()){
                if(!isVisited[preVal-'A']) return false;
            }
            
            isVisited[current-'A'] = true;
        }
        
        return true;
    }

    public int solution(String skill, String[] skill_trees) {
    
        int answer = 0;
        init(skill);
        
        for(String skillTree : skill_trees){
            if(check(skillTree)) answer++;
        }
        return answer;
    }
}