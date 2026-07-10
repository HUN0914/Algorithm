#include<iostream>
#include <vector>
#include <string>
#include <queue>

using namespace std;

/*
지뢰 *
지뢰 없는칸 : .
지뢰 있는 칸 제외 다른 모든 칸 숫자 표시되려면 최소 몇 번의 클릭?
모든숫자!

처음에 카운팅을 해두고, 늘 그 범위만큼빼고 만약 그게 0이 됐을 때 break;
그래서 처음은 8칸 다 돌아갈 때에 대해서 최소 cnt 계산을 해주고 
(이때 visitied 처리)

그다음엔 visited 안됐으면서 지뢰가 아닌 칸들에 대해 cnt 해주기

dfs로 8 방향 0 됐을 때의 최소의 수를 구하고
그거 기반으로 visited되어있을 때 숫자 세고 TOTALCNT에서 좀 지우고 차이 계산

*/
char board[301][301];
bool visitedMap[301][301];

int dy[8] = { -1,-1,-1,0,0,1,1,1 };
int dx[8] = { -1,0,1,-1,1,-1,0,1 };
int noBombCnt;
int startCnt;

queue<pair<int, int>> q;

void init(int n) {
	for (int i = 0; i < n; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < n; j++) {
			board[i][j] = s[j];
			if (board[i][j] == '.') noBombCnt++;
		}
	}

	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) visitedMap[i][j] = false;

	while (!q.empty())
	{
		q.pop();
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {

			if (board[i][j] == '*') continue;

			bool isCheck = false;
			for (int k = 0; k < 8; k++) {
				int qy = i + dy[k];
				int qx = j + dx[k];

				if (qy < 0 || qy >= n || qx < 0 || qx >= n) continue;

				if (board[qy][qx] == '*') {
					isCheck = true;
					break;
				}
			}
			if (!isCheck) {
				q.push({ i,j });
			}
		}
	}
}

int game(int n) {

	/*
	저 q에 들어간 것들에 대해 8칸 체크를 했을 때 만약 다 포함된다
	그러면 noCnt하면 됨.
	*/
	int qsize = q.size();

	for (int s = 0; s < qsize; s++) {
		int startY = q.front().first;
		int startX = q.front().second;
		q.pop();

		if (visitedMap[startY][startX]) continue;

		startCnt++;
		visitedMap[startY][startX] = true;
		noBombCnt--;

		queue<pair<int, int>> mq;

		mq.push({ startY,startX });

		while (!mq.empty()) {
			int curY = mq.front().first;
			int curX = mq.front().second;
			mq.pop();

			bool isZero = true;
			for (int i = 0; i < 8; i++) {
				int qy = curY + dy[i];
				int qx = curX + dx[i];
				if (qy < 0 || qy >= n || qx < 0 || qx >= n) continue;
				if (board[qy][qx] == '*') {
					isZero = false;
					break;
				}
			}

			if (isZero) {
				for (int i = 0; i < 8; i++) {
					int qy = curY + dy[i];
					int qx = curX + dx[i];

					if (qy < 0 || qy >= n || qx < 0 || qx >= n) continue;

					if (!visitedMap[qy][qx] && board[qy][qx] == '.') {
						visitedMap[qy][qx] = true;
						noBombCnt--;

						mq.push({ qy,qx });
					}

				}
			}
		}
	}

	return noBombCnt + startCnt;
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin >> T;
	for (test_case = 1; test_case <= T; ++test_case)
	{
		int n;
		noBombCnt = 0;
		startCnt = 0;
		cin >> n;

		init(n);
		cout << "#" << test_case << " " << game(n) << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}