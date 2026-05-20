#include <string>
#include <vector>

using namespace std;

int cnt=-1;
int answer=0;
string target="";
string aeiou="AEIOU";

void dfs(string word, string cur){
    
    cnt++;
    
    if(word==cur){
        answer=cnt;
        return;
    }
    
    if(cur.length()>=5) return;
    
    for(int i=0; i<5; i++){
        dfs(word,cur+aeiou[i]);
    }
}
    
int solution(string word) {
    string cur="";
    dfs(word, cur);
    return answer;
}