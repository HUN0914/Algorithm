#include <string>
#include <vector>

using namespace std;

int N=0;
int answer=0;
int col[12];

bool check(int row){
    for(int i=0; i<row; i++){
        if(col[row]== col[i] || abs(col[row]- col[i]) == row-i) return false;
        
    }
    return true;
}

void nqueen(int row){
    if(row==N){
        answer++;
        return;
    }else{
        for(int i=0; i<N; i++){
            col[row]=i;
            if(check(row)) nqueen(row+1);
        }
    } 
}

int solution(int n) {
    N=n;
    nqueen(0);
    return answer;
}