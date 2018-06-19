package baekjoon1260;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int [][] vPath; 				// path matrix
	public static boolean [] isVisited_d;	// depth first
	public static boolean [] isVisited_b;	// width first
	public static Queue<Integer> q;
	public static int N;							// vertex
	public static int M;							// line
	public static int V;							// start

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		V = in.nextInt();
		
		vPath = new int[N+1][N+1];
		isVisited_d = new boolean[N+1];
		isVisited_b = new boolean[N+1];
		q = new LinkedList<>();
		
		for (int i =0; i < M; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			vPath[x][y]++;
			vPath[y][x]++;
		}
		// init end
		
		DFS (V);
		System.out.println();
		BFS (V);
		// output end 
	}
	public static void DFS (int n) {
		System.out.print(n + " ");
		isVisited_d[n] = true;
		for (int i = 1; i <= N; i++) {
			if (vPath[n][i] > 0 && !isVisited_d[i]) {
				DFS(i);
			}
		}
	}
	public static void BFS (int n) {
		q.add(n);	// start index 	
		isVisited_b[n] = true;
		
		while (!q.isEmpty()) {
			int temp = q.poll();			
			System.out.print(temp+" ");
			
			for (int i = 1; i <= N; i++) {
				if (vPath[temp][i] > 0 && !isVisited_b[i]) {
					q.add(i);
					isVisited_b[i] = true;
				}
			}
		}
	}
}
