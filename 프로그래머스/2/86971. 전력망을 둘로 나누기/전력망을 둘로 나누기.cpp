#include <string>
#include <vector>
#include <queue>

using namespace std;

/*
전력망을 2개로 나눴을 때 가장 둘이 비슷하게 된 케이스 조사
저기 wires에서 하나씩만 빼고 계산 (8개 원소)을 하고
그룹 몇개로 나오는지 조사
*/

int mini=1e9;

bool isVisited[1000];

void bfs(int n, vector<vector<int>> line){
    
    int cnt=1;
    
    queue<int> q;
    
    q.push(1);
    isVisited[1]=true;
    
    while(!q.empty()){
        
        
        int cur=q.front();
        q.pop();
        
        for(int k=0; k<line[cur].size(); k++){
            
            int value=line[cur][k];
            
            if(isVisited[value]) continue;
            
            isVisited[value]=true;
            
            q.push(value);
            
            cnt++;
        }
        
    }
    
    mini= min(mini, abs((n-cnt)-cnt));
}

void cal(int n, vector<vector<int>> wires) {
    
    for(int i=0; i<wires.size(); i++){
        
        vector<vector<int>> line(n+1);
        
        for(int j=0; j<wires.size(); j++){
            
            if(i==j) continue;
            
            int fir=wires[j][0];
            int sec=wires[j][1];
            
            line[fir].push_back(sec);
            line[sec].push_back(fir);
        }
        
        for(int i=0; i<n+1; i++) isVisited[i]=false;
        
        bfs(n, line);
    }
    
}

int solution(int n, vector<vector<int>> wires) {
    
    cal(n, wires);

    return mini;
}