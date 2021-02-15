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
1. �˾ƾ��� ��
-> LinkedList ������ vs ArrayList ���� �� �ӵ� 
-> BufferedReader, BufferedWriter ���� 
-> ���ͽ�Ʈ�� �˰��򿡼� ����ġ�� ���
-> comparable �켱���� ���� �ϱ� (��������)

���� ����Ʈ
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
		
		adj = new List [V+1];			// �׷��� ��� 
		dist = new int [V+1];			// �Ÿ� ���� ���� 	
		
		for (int i=1; i<V+1; i++) {
			adj[i] = new ArrayList<>();	// list �迭 �ʱ�ȭ 
			dist[i] = INF;				// linked list �� ���� �ð��� �����ɸ� (ArrayList < LinkedList)
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
		dist[src] = 0;	// �ʱⰪ 
		
		if (src == dst) {
			return;
		}
		pq.add(new Node(0,src));
		
		while (!pq.isEmpty()) {			
			Node temp = pq.poll();
			int cost = temp.value;		// ������ġ���� ��� ���� 
			int here = temp.src;		// ���� ��� 
			
			if (dist[here]<cost) continue; 	// ����ȭ
			if (isVisited[here]) continue;	// ����ȭ
			isVisited[here] = true;
			
			for (int i = 0; i < adj[here].size() ; i++) {
				DistNode a = (DistNode)adj[here].get(i);
				int nextCost = a.weight;					
				int nextDist = cost + nextCost;	// ���� ��ο� ������ �� ��� ���� 
				if (dist[a.dst] > nextDist) {	// ���� 
					dist[a.dst] = nextDist;
					pq.add(new Node(nextDist, a.dst));	// �켱 ���� ť�� �־��ش� 
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
			return (this.value - node.value);	// value ������ ���� ���� (�ڽ� - ���� ���) 
		}
	}
}
