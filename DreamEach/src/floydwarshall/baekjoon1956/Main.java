package floydwarshall.baekjoon1956;

import java.util.Scanner;

public class Main {
	public static final int INF = 100000000;
	public static int answer = INF;
	public static int V,E;
	public static int [][] g;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		V = in.nextInt(); E = in.nextInt();
		g = new int[V+1][V+1];
		
		for (int i = 0; i <= V; i++) {
			for (int j = 0; j <=V; j++) {
				g[i][j] = INF;
			}
		}
		
		for (int i = 0; i < E; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int value = in.nextInt();
			
			g[from][to] = value;
		}
		
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					g[i][j] = Math.min(g[i][j], g[i][k]+g[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			answer = Math.min(g[i][i], answer);
		}
				
		if (answer == INF) {
			answer = -1;
		}
		System.out.println(answer);
	}

}
