# 문제 정보
# 경주로 부지는 N x N 크기의 정사각형 격자 형태
# 각 격자는 0(비어 있음) 또는 1(벽)로 채워져있음
# 좌측 상단 (0, 0)은 출발점이고 우측 하단 (N - 1, N - 1)은 도착점이다.
# 상, 하, 좌, 우로 인접한 두 빈 칸을 연결하여 건설
# 벽이 있는 칸에는 경주로 건설 불가
# 직선 도로(100원) = 상하 / 좌우로 연결한 경주로
# 코너(500원) = 두 직선 도로가 서로 직각으로 만나는 지점
# 경주로 건설 시 필요한 최소 비용 계산

# 입력 정보
# board -> 2차원 정사각 배열
# 출발점과 도착점 칸의 원소는 항상 0

# 반환 정보
# answer -> 경주로 건설 시 필요한 최소 비용

# 풀이 순서
# 1. BFS의 방식으로 길찾기를 한다.
# 2. 3차원 배열인 cost_board에 해당 칸에 대해 최소 금액을 갱신해나간다.
# 3. 도착 지점의 금액을 출력한다.

from collections import deque

INF = float('inf')

def solution(board):
    answer = 0
    N = len(board)
    def bfs():
        dr = [-1, 0, 1, 0]
        dc = [0, 1, 0, -1]
        
        cost_board = [[[INF for _ in range(4)] for _ in range(N)] for _ in range(N)]
        dq = deque()
        dq.append((0, 0, 0, -1))
        cost_board[0][0] = [0, 0, 0, 0]
        
        while dq:
            cr, cc, cw, cd = dq.popleft()
            for d in range(4):
                nr, nc = cr + dr[d], cc + dc[d]
                if cd == -1 or cd == d:
                    nw = cw + 100
                else:
                    nw = cw + 600
                nd = d
                    
                if is_valid(nr, nc) and not board[nr][nc]:
                    if cost_board[nr][nc] == INF:
                        cost_board[nr][nc][nd] = nw
                        dq.append((nr, nc, nw, nd))
                    else:
                        if cost_board[nr][nc][nd] > nw:
                            cost_board[nr][nc][nd] = nw
                            dq.append((nr, nc, nw, nd))
        return min(cost_board[N - 1][N - 1])
                
    def is_valid(r, c):
        return 0 <= r < N and 0 <= c < N
    
    answer = bfs()
    
    return answer
