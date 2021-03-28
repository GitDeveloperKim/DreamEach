package dijkstra.baekjoon1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 1504 
// https://www.acmicpc.net/problem/1504
class Node implements Comparable<Node> {
	int dst;
	int val;
	
	// Constructor 
	Node (int dst, int val) {
		this.dst = dst;
		this.val = val;
	}

	@Override
	public int compareTo(Node next) {
		return this.val - next.val;
	}
}

public class Main {
	public static int N;						// 노드
	public static int E;						// 간선 
	public static ArrayList<Node> g [];			// 경로
	public static int [] d;						// 최단경로  
	public static int dst1;						// 경유지1
	public static int dst2;						// 경유지2
	public static final int INF = 200_000_001;	// c_max * edge_max = 1000 * 200000
	
	public static void main(String[] args) throws IOException {
		// declare
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st;

		
		// input
		st = new StringTokenizer (br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		d = new int [N+1];
		g = new ArrayList[N+1];
		for (int i = 0;i <= N; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int e = 1; e <= E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프 
			g[a].add(new Node(b,c));
			g[b].add(new Node(a,c));
		}
		st = new StringTokenizer(br.readLine());
		dst1 = Integer.parseInt(st.nextToken());
		dst2 = Integer.parseInt(st.nextToken());
		
		// do algo
		int a = sol(1, dst1) + sol(dst1,dst2) + sol(dst2, N);	// 1->dst1->dst2->N
		int b = sol(1, dst2) + sol(dst2, dst1) + sol(dst1, N);	// 1->dst2->dst1->N
		// 무한대가 3번 더해질 수 있음 
		int answer = (a>=INF && b>=INF) ? -1 :Math.min(a,b);
		System.out.println(answer);
	}
	
	// 다익스트라 알고리즘 
	public static int sol (int s, int e) {
		
		// 거리 초기화 
		for (int i = 0; i <= N; i++) {
			d[i] = INF;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(s,0));
		d[s] = 0;
		
		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int cur_pos = cur_node.dst;
			int cur_cost = cur_node.val;
			
			if (d[cur_pos] < cur_cost)
				continue;
			
			for (int i = 0; i < g[cur_pos].size(); i++) {
				int next_pos = g[cur_pos].get(i).dst;
				int next_cost = cur_cost + g[cur_pos].get(i).val;
				
				if (d[next_pos] > next_cost) {
					d[next_pos] = next_cost;
					pq.add(new Node(next_pos, next_cost));
				}
			}
		}
		
		return d[e];
	}
}
