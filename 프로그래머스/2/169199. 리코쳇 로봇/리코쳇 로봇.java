import java.util.*;

class Solution {
    
    int[] dy={-1,1,0,0};
    int[] dx={0,0,-1,1};
    
    class Location{
        int y;
        int x;
        
        Location(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    
    public int solution(String[] board) {
        
        int row = board.length;
        int col = board[0].length();
        
        int startY=-1;
        int startX=-1;
        int goalY=-1;
        int goalX=-1;
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                
                if(board[i].charAt(j) =='R'){
                    startY = i;
                    startX = j;
                }
                
                if(board[i].charAt(j)=='G'){
                    goalY=i;
                    goalX=j;
                }
            }
        }
        
        int[][] distance = new int[row][col];
        
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++) distance[i][j]=-1;
        
        Queue<Location> q = new LinkedList<>();
        
        q.offer(new Location(startY, startX));
        distance[startY][startX]=0;
        
        while(!q.isEmpty()){
            
            Location current = q.poll();
            
            int y=current.y;
            int x= current.x;
            
            if (y==goalY&&x==goalX) return distance[y][x];
            
            for(int k=0; k<4; k++){
                int ny=y;
                int nx=x;
                
                while(true){
                    
                    int nextY = ny + dy[k];
                    int nextX = nx + dx[k];
                    
                    if(nextY<0 || nextX< 0 || nextY >= row || nextX>= col) break;
                    
                    if(board[nextY].charAt(nextX) == 'D') break;
                    
                    ny=nextY;
                    nx=nextX;
                }
                
                if(ny==y&&nx==x) continue;
                
                if(distance[ny][nx]==-1){
                    distance[ny][nx]=distance[y][x]+1;
                    q.offer(new Location(ny,nx));
                }
            }
        }
        
        return -1;
    }
}