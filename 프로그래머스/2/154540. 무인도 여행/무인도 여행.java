import java.util.*;

class Solution {
    
    int[] dy={-1,1,0,0};
    int[] dx={0,0,-1,1};
    List<Integer> li = new ArrayList<>();
    
    class Info{
        int y;
        int x;
        
        Info(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    
     void bfs(String[] maps){
        
        int garo=maps[0].length();
        int sero=maps.length;
        
        boolean[][] isVisited = new boolean[101][101];
        
        for(int i=0; i<sero; i++){
            for(int j=0; j<garo; j++){
                if(maps[i].charAt(j)!='X'){
                    if(isVisited[i][j]) continue;
                    isVisited[i][j]=true;
                    int currentVal = maps[i].charAt(j)-'0';
                    Queue<Info> q = new LinkedList<>();
                    q.offer(new Info(i,j));
                    
                    while(!q.isEmpty()){
                        Info info = q.poll();
                        
                        int y=info.y;
                        int x=info.x;
                        
                        for(int dir=0; dir<4; dir++){
                            int ny=y+dy[dir];
                            int nx=x+dx[dir];
                            
                            if(ny<0||ny>=sero||nx<0||nx>=garo) continue;
                            if(maps[ny].charAt(nx)=='X') continue;
                            if(isVisited[ny][nx]) continue;
                            
                            isVisited[ny][nx]=true;
                            currentVal+=maps[ny].charAt(nx)-'0';
                            q.offer(new Info(ny,nx));
                        }
                    }
                    li.add(currentVal);
                }
            }
        }
    }
    
    public int[] solution(String[] maps) {
        bfs(maps);
        
        if(li.size()==0) {
            int[] answer = {-1};
            return answer;
        }
        
        Collections.sort(li);
        int[] answer = new int[li.size()];
        
        for(int i=0; i<li.size(); i++) answer[i]=li.get(i);
        return answer;
    }
}