#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

map<string,int> m;

void dfs(string order, string cur, int idx, int target){
    
    if(cur.length()==target){
        m[cur]++;
        return;
    }
    
    for(int i=idx; i<order.length(); i++){
        dfs(order, cur+order[i], i+1, target);
    }
    
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    
    for(int i=0; i<orders.size(); i++){
        sort(orders[i].begin(), orders[i].end());
    }
    
    for(int c : course){
        m.clear();
        
        for(string order : orders){
            if(order.length() < c) continue;
            
            dfs(order, "", 0, c);
        }
        
        int maxCnt=0;
        
        for(auto now : m){
            if(now.second>=2){
                maxCnt=max(maxCnt, now.second);
            }
        }
        
        for(auto now : m){
            if(now.second==maxCnt&&now.second>=2) answer.push_back(now.first);
        }
        
    }
    sort(answer.begin(), answer.end());
    
    return answer;
}