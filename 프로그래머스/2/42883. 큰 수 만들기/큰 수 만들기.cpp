#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>

using namespace std;

int maximum=0;

string game(string number, int k){
    
    stack<char>st;
    string answer="";
    
    for(auto c: number){
        while(true){
            if(st.empty()) break;
            if(st.top()>=c) break;       
            if(k<=0) break;
            
            st.pop();
            k--;
        }
        st.push(c);
    }
    
    while(k>0){
        st.pop();
        k--;
    }
    
    while(!st.empty()){
        answer+= st.top();
        st.pop();
    }
    
    reverse(answer.begin(), answer.end());
    
    return answer;
}

string solution(string number, int k) {
    string answer = game(number,k);
    
    return answer;
}