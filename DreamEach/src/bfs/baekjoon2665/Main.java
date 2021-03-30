package bfs.baekjoon2665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 *  ���ͽ�Ʈ��� Ǯ� ��
 *  �׷��� ���� 0-1�� �Ǿ� �ֱ� ������ bfs �� Ǯ� 
 */

class Point {
	int x;
	int y;
	int black;
	
	Point (int x, int y, int black) {
		this.x = x;
		this.y = y;
		this.black = black;
	}
}

public class Main {
	public static int N;
	public static int [][] mat;
	public static boolean [][] isVisited;
	public static int answer = Integer.MAX_VALUE;
	
	public static int dx[] = {0, 1, 0 , -1}; // �� �� �� �� 
	public static int dy[] = {-1, 0, 1, 0};
	
			
	public static void main(String[] args) throws IOException {
		// declare
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer (br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		mat = new int [N+1][N+1];
		isVisited = new boolean [N+1][N+1];
		
		// input
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				if ((temp.charAt(j) - '0') == 1) {
					mat[i][j+1] = 0;	// 1�� 0���� 
				} else {
					mat[i][j+1] = 1;	// 0�� 1�� �ݴ�� ���� -> �׷��� ���� ���� ������ 0�̶� ���� ���� 
				}
			}			
		}
		
		// 0-1 bfs 
		Deque<Point> dq = new ArrayDeque<>();	// ��ǥ + black ��
		dq.addFirst(new Point(1,1,mat[1][1]) );
		
		while (!dq.isEmpty()) {
			Point cur = dq.pollFirst();
			int x = cur.x;
			int y = cur.y;
			int black = cur.black;
			isVisited[y][x] = true;
			
			if (x == N && y == N) {
				if (black < answer) {
					answer = black;	// ������ �� �� ���� ����� ������ ������ ���� 
				}
			}
			
			for (int i = 0; i < 4; i++) {
				// 4���� ���⿡ ���� ���� 
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 1 || ny < 1 || nx >N || ny >N) 
					continue;
				if (isVisited[ny][nx])
					continue;
				
				if (mat[ny][nx] == 0) {
					dq.addFirst(new Point(nx, ny, black));		// 0 �� ��� ť �տ� �ֱ� 
				} else if (mat[ny][nx] == 1) {
					dq.addLast(new Point(nx, ny, black+1));		// 1 �� ��� ������+1 ť �ڿ� �ֱ� 
				}
				
			}
		}
		
		// solution
		System.out.println(answer);
	}
}