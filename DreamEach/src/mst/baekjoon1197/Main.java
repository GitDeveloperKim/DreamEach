package mst.baekjoon1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int src;
	int dst; 
	int weight;
	
	Node (int s, int d, int w) {
		src = s;
		dst = d;
		weight = w;
	}

	@Override
	public int compareTo(Node o) {		
		return this.weight - o.weight;
	}
}

public class Main {
	public static int V; // 1<= V <= 10000
	public static int E; // 1<= E <= 100000
	public static int A;
	public static int B;
	public static int C; // -1000000 <= C <= 1000000
	public static int answer;
	public static int [] parent;
	
	// find
	public static int find (int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x] = find(parent[x]);	// 트리 갱신
		}		
	}
	
	// union
	public static void union (int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y) {
			parent[y] = x;	// 합치기 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		List<Node> arr = new ArrayList<>();
		parent = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;	// 초기화 부모는 자기자신 가리키기 
		}
		

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arr.add(new Node(A,B,C));
		}
		Collections.sort(arr);
		
        answer = 0;
        
        // kruskal
		for (Node node : arr) {
			if (find(node.src) != find(node.dst)) {
				answer += node.weight;
				union(node.src, node.dst);
			}
		}
		System.out.println(answer);
		
		br.close();
	}

}
