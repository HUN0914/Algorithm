#include <string>
#include <vector>

using namespace std;

int val=0;

void dfs(vector<int> numbers, int target, int value, int cnt){
    
    if(cnt==numbers.size()){
        if(target==value) val++;
        return;
    }
    
    dfs(numbers,target,value+numbers[cnt], cnt+1);
    dfs(numbers,target,value-numbers[cnt], cnt+1);
    
}

int solution(vector<int> numbers, int target) {
    
    dfs(numbers, target, 0, 0);
        
    int answer = val;
    return answer;
}