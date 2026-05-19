#include <string>
#include <vector>
#include <map>
#include <iostream>
#include <algorithm>

using namespace std;

bool comp(string a, string b) {
    if(a.size()<=b.size())
        return true;
    else
        return false;
}


void getComb(int startIdx, int maxIdx, string str, vector<string> &comb) {
        
    for(int i=startIdx; i<maxIdx; i++) {
        comb.push_back(str+to_string(i));
        getComb(i+1, maxIdx, str+to_string(i), comb);
    }
}


bool minTest(string a, string b) {
    // a가 b에 포함 안되는지 테스트
    // String.find()를 쓰면 테스트 18, 19, 20, 25, 28이 틀려버림
    int sameCnt = 0;
    for(int i=0; i<a.size(); i++) {
        for(int j=0; j<b.size(); j++) {
            if(a[i]==b[j]) {
                sameCnt++;
            }
        }
    }
    
    
    if(sameCnt==a.size() || sameCnt==b.size()) {
        return false;
    } else {
        return true;
    }
    
}
int solution(vector<vector<string>> relation) {
    int answer = 0;
    
    vector<string> keyList; // 후보키 저장소
    vector<string> comb; // 조합 저장소
    bool uniqFlag = true; // 유일성 만족 여부
    bool minFlag = true; // 최소성 만족 여부
    int maxIdx = relation[0].size(); // 컬럼의 개수(=탐색 가능한 컬럼의 최대 인덱스 0~(maxIdx-1)
    
    getComb(0, maxIdx, "", comb); // comb에 조합들 저장
    sort(comb.begin(), comb.end(), comp); // 적은 개수의 컬럼 조합들을 먼저 수행해줘야하기에 정렬 해줌.
    map<string, bool> m; // 선택된 컬럼값들을 모두 붙여 저장하는 저장소 (유일성 판별에 사용함)
    
    // 1. 조합을 가지고 모든 튜플들을 돌며 해당 컬럼들을 붙여 문자열을 만듬
    for(int i=0; i<comb.size(); i++) {
        string result=comb[i]; // 붙일 컬럼들을 나타내는 문자열
        
        for(int rowIdx=0; rowIdx<relation.size(); rowIdx++) { // 모든 튜플들에 대해 반복
            string str = ""; // 해당 튜플의 컬럼들을 붙인 문자열을 저장할 변수
            for(int colIdx=0; colIdx<result.size(); colIdx++) {
                str+=relation[rowIdx][result[colIdx]-'0']; // 해당 튜플의 컬럼값을 변수에 붙임
            }

            if(m.find(str) != m.end()) { // 이미 존재하는 문자열이라면
                uniqFlag=false;
                break;
            } else { // 새로운 문자열이라면
                m.insert({str, true});
            }
    }
        
        // (최소성)
        for(int idx=0; idx<keyList.size(); idx++) {
            
            // minTest: 두 문자열중 하나가 다른 문자열에 모두 포함되면 안되기에 검사함(최소성)
            if(!minTest(result, keyList[idx])) {
                minFlag = false;
                break;
            }
        }
                
                
        // 2. 최소성, 유일성 만족시 answer에 더함. 후보키 등록
        if(uniqFlag && minFlag) {
            keyList.push_back(result);
            answer++;
        }
        uniqFlag = true;
        minFlag = true;
        m.clear();
    }
    
    return answer;
}