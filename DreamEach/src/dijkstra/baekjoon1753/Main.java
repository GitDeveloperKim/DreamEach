package dijkstra.baekjoon1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
1. 알아야할 것
-> LinkedList 썼을때 vs ArrayList 썼을 때 속도 
-> BufferedReader, BufferedWriter 사용법 
-> 다익스트라 알고리즘에서 가지치기 방법
-> comparable 우선순위 설정 암기 (오름차순)

참고 사이트
https://dragon-h.tistory.com/20

https://www.acmicpc.net/problem/1753
 * 
 */
public class Main {

	public static final int INF = 10000000;	// 10*20000 = 200000 <= INF
	public static int V,E,K;	// Vertex, Edge, start point	(1<=V<=20,000, 1<=E<=300,000 1<=K<=V)
	public static List [] adj;
	public static int [] dist;
	public static int dst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(br.readLine());
		
		adj = new List [V+1];			// 그래프 경로 
		dist = new int [V+1];			// 거리 정보 저장 	
		
		for (int i=1; i<V+1; i++) {
			adj[i] = new ArrayList<>();	// list 배열 초기화 
			dist[i] = INF;				// linked list 를 쓰면 시간이 오래걸림 (ArrayList < LinkedList)
		}
		
		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new DistNode(v,w));
		}				
		
		dijkstra (K);
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t< V+1; t++) {			
			if (dist[t] == INF) sb.append("INF\n");
			else sb.append(dist[t]+"\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static void dijkstra (int src) {
		boolean [] isVisited = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();	
		dist[src] = 0;	// 초기값 
		
		if (src == dst) {
			return;
		}
		pq.add(new Node(0,src));
		
		while (!pq.isEmpty()) {			
			Node temp = pq.poll();
			int cost = temp.value;		// 현재위치에서 비용 정보 
			int here = temp.src;		// 현재 노드 
			
			if (dist[here]<cost) continue; 	// 최적화
			if (isVisited[here]) continue;	// 최적화
			isVisited[here] = true;
			
			for (int i = 0; i < adj[here].size() ; i++) {
				DistNode a = (DistNode)adj[here].get(i);
				int nextCost = a.weight;					
				int nextDist = cost + nextCost;	// 다음 경로에 도착할 때 비용 정보 
				if (dist[a.dst] > nextDist) {	// 갱신 
					dist[a.dst] = nextDist;
					pq.add(new Node(nextDist, a.dst));	// 우선 순위 큐에 넣어준다 
				}
			}
		}
	}
	
	static class DistNode {
		int dst;
		int weight;
		DistNode (int dst, int weight) {
			this.dst = dst;
			this.weight = weight;
		}
	}
	
	static class Node implements Comparable<Node>{		
		int value;
		int src;
		
		Node (int value, int src) {
			this.value = value;
			this.src = src;
		}
		@Override
		public int compareTo(Node node) {			
			return (this.value - node.value);	// value 값으로 오름 차순 (자신 - 다음 노드) 
		}
	}
}
