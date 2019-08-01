package dijkstra.baekjoon1916;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

//djjkstra
public class Main {
	static int N;		// the number of city 
	static int M;		// the number of bus
	static int [][] g;	// graph vertex
	static int [] d;	// distance 
	static int src;
	static int dst;
	static final int INF = 99999999;	// Integer.MAX_VALUE 쓰면 틀렸다 나옴;;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		
		g = new int [N+1][N+1];
		d = new int [N+1];
		Arrays.fill(d, INF);
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				g[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			int s = in.nextInt();	// src
			int d = in.nextInt();	// destination
			int v = in.nextInt();	// value
			if (g[s][d] > v) {
				g[s][d] = v;	
			}
		}
		src = in.nextInt();
		dst = in.nextInt();
		
		dijkstra();
		
		System.out.println(d[dst]);
		
	}
	
	public static void dijkstra () {
		PriorityQueue <Integer> pq = new PriorityQueue<>();
		pq.add(src);
		d[src] = 0;
		
		while (!pq.isEmpty()) {
			int from = pq.poll();
			
			for (int to = 1; to < N+1; to++) {
                if (d[to] > d[from]+g[from][to]) {
                    d[to] = d[from]+g[from][to];
                    pq.add(to);//update
                }
			}
		}
	}
}