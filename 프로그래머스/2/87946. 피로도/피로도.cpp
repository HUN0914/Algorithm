#include <string>
#include <vector>

using namespace std;

bool isVisited[5001];

int maximum=0;

void dfs(int k, vector<vector<int>>dungeons, int cnt) {
    
    maximum=max(maximum,cnt);
    
    for(int i=0; i<dungeons.size(); i++){
        if(isVisited[i]) continue;
        if(k<dungeons[i][0]) continue;
        
        isVisited[i]=true;
        
        dfs(k-dungeons[i][1], dungeons, cnt+1);
        
        isVisited[i]=false;
    }
    
}

int solution(int k, vector<vector<int>> dungeons) {
    
    dfs(k,dungeons,0);
    int answer = maximum;
    return answer;
}