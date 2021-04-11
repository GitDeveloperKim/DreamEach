package bfs.baekjoon1261;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 0-1 bfs 를 이용한 문제풀이
 * 배열에 값이 0과 1만 존재하면 
 * Deque를 이용하여 성능 개선을 할 수 있다 
 * 0은 맨앞에 넣고 1은 맨 뒤에 넣는다
 * 빼는건 앞에서만 빼기  
 * @author default
 *
 */
class Node {
	int x;
	int y;
	int sum;
	Node (int x, int y, int sum) {
		this.x = x;
		this.y = y;
		this.sum = sum;
	}
}

public class Main {
	public static int N;	// 높이 
	public static int M;	// 너비 
	public static int [][] input;
	public static boolean [][] visited;
	public static Deque<Node> dq;
	
	public static int [] x = {1,0,-1,0};
	public static int [] y = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		input = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			String temp = br.readLine();
			for (int j = 1; j <= M; j++) {
				input[i][j] = temp.charAt(j-1)-'0';
			}
		}
		sb.append(bfs()+"\n");
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static int bfs () {
		int answer = 0;
		dq = new ArrayDeque<>();
		dq.add(new Node(1,1,input[1][1]));
		visited[1][1] = true;
		
		while (!dq.isEmpty()) {
			Node cur= dq.pollFirst();
			answer = cur.sum;
			
			int nx = cur.x;
			int ny = cur.y;
			
			if (nx == M && ny == N) {
				return answer;
			}
			for (int i = 0; i < 4; i++) {
				nx = cur.x + x[i];
				ny = cur.y + y[i];
				
				if (nx < 1 || nx > M || ny < 1 || ny > N)
					continue;				
				if (visited[ny][nx]) 
					continue;
				
				visited[ny][nx] = true;
				if (input[ny][nx] == 1) {
					dq.addLast(new Node(nx,ny,answer+input[ny][nx]));
				} else {
					dq.addFirst(new Node(nx,ny,answer+input[ny][nx]));
				}
			}
		}
		
		return answer;
	}
}
