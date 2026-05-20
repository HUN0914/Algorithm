#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

set<string> res;

bool isOkay(int n){
    if(n<2) return false;
    
    for(int i=2; i*i<=n; i++){
        if(n%i==0) return false;
    }
    return true;
}

bool isVisited[8];

void dfs(string val, string numbers){
    
    for(int i=0; i<numbers.size(); i++){
        
        if(isVisited[i]) continue;
        if(val[0]=='0') continue;
        
        isVisited[i]=true;
        
        res.insert(val+numbers[i]);
        
        dfs(val+numbers[i], numbers);
        
        isVisited[i]=false;
    }
}

int cal(string numbers){
    
    int result=0;
    
    dfs("", numbers);
    
    for(auto p: res){
        int val=stoi(p);
        if(isOkay(val)) result++;
    }
    
    return result;
}

int solution(string numbers) {
    int answer=cal(numbers);
    return answer;
}