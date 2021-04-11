package bfs.baekjoon2606;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int n;
	public static int [][] adj;
	public static int answer;
	public static boolean [] isVisited;
	public static Queue<Integer> q;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		n = in.nextInt();
		adj = new int [n+1][n+1];
		isVisited = new boolean[n+1];
		q = new LinkedList<>();
		answer = 0;
		
		int k = in.nextInt();
		for (int i= 0; i < k; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = 1;
			adj[b][a] = 1; // 연결됨을 의미 
		}
		
		for (int i = 1; i <= n; i++) {
			if (adj[1][i] == 1 && !isVisited[i]) {
				q.add(i);
				isVisited[i] = true;
				answer++;
			}
		}
		
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int i = 1; i <= n; i++) {
				if (adj[temp][i] == 1 && !isVisited[i]) {
					q.add(i);
					isVisited[i] = true;
					answer++;
				}
			}
		}
		System.out.println(answer-1);	// 자기자신을 빼야 		
	}
}
