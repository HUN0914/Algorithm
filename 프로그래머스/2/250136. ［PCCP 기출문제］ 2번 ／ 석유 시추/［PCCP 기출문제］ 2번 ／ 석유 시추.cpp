#include <string>
#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;

int dy[4]={-1,1,0,0};
int dx[4]={0,0,-1,1};

bool visited[501][501];

struct INFO{
    int y;
    int x;
};

void bfs(int sy, int sx, vector<vector<int>>& land, vector<int>& colSum){
    
    int n = land.size();
    int m = land[0].size();
    
    queue<INFO> q;
    q.push({sy,sx});
    
    visited[sy][sx]=true;
    
    int cnt=1;
    
    set<int> cols;
    cols.insert(sx);
    
    while(!q.empty()){
        
        int y=q.front().y;
        int x=q.front().x;
        q.pop();
        
        for(int i=0; i<4; i++){
            
            int ny=y+dy[i];
            int nx=x+dx[i];
            
            if(ny<0 || nx<0 || ny>=n || nx>=m) continue;
            if(visited[ny][nx]) continue;
            if(land[ny][nx]==0) continue;
            
            visited[ny][nx]=true;
            
            q.push({ny,nx});
            
            cnt++;
            
            cols.insert(nx);
        }
    }
    
    for(int c : cols){
        colSum[c]+=cnt;
    }
}

int solution(vector<vector<int>> land) {
    
    int n = land.size();
    int m = land[0].size();
    
    vector<int> colSum(m,0);
    
    for(int y=0; y<n; y++){
        for(int x=0; x<m; x++){
            
            if(visited[y][x]) continue;
            if(land[y][x]==0) continue;
            
            bfs(y,x,land,colSum);
        }
    }
    
    int answer=0;
    
    for(int i=0; i<m; i++){
        answer=max(answer,colSum[i]);
    }
    
    return answer;
}