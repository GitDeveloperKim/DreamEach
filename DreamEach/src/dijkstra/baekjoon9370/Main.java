package dijkstra.baekjoon9370;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/9370
 * 1. 다익스트라 알고리즘 사용 
 * 2. 범인이 이동한 경로가 주어지고 
 * 3. 후보군의 이동경로를 갈수 있는지 여부를 확인 하는 문제 
 * 4. 시작점에서 다익스트라를 돌린 것에서 도착지의 최단 경로와 
 * 5. 범인이 이동한 경로의 더 큰 경로값을 가지는 곳에서 출발한 최단경로와 비교 
 * 6. 값이 같으면 범인이 이동한 경로이고 아니면 될수가 없다 
 * 
 * point 
 * 1. 다익스트라 알고리즘 구현하여 배열 리턴, 
 * 2. 자바 스트림을 이용하여 정렬후 출력하는 방법 
 * 
 * @author GitDeveloperKim
 *
 */
class Edge {
	int dst;
	int cost;
	
	Edge (int dst, int cost) {
		this.dst = dst;
		this.cost = cost;
	}
}

class Node implements Comparable<Node>{
	int pos;
	int cost;
	
	Node (int pos, int cost) {
		this.pos = pos;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
}

public class Main {
	public static int n, m, t;					// 노드 개수, 간선, 후보지
	public static int s, g, h;					// 시작점, 경유지 
	public static int [] d;						// 거리 정보 
	public static boolean [] isVisited;
	public static ArrayList<ArrayList<Edge>> mat;
	public static final int INF = 5000_0001; 	// 50000*1000
	public static int [] result;
	public static int [] result1;
	public static int [] result2;
	public static List <Integer> answer;
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
	    
		int T = Integer.parseInt(br.readLine());
		
		while (T-- >0) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			answer = new ArrayList<>();
			
			mat = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				mat.add(new ArrayList<>());
			}
			
			for (int i = 1; i <= m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				// 양방향 간선 
				mat.get(u).add(new Edge(v,c));
				mat.get(v).add(new Edge(u,c));
			}
			
			result = new int[t];
			for (int i = 0; i < t; i++) {
				result[i] = Integer.parseInt(br.readLine());
			}
			
			result1 = dijkstra(s);	// 시작점에서 다익스트라 
			int s2;
			if (result1[g] > result1[h]) {
				s2 = g;
			} else {
				s2 = h;
			}
			result2 = dijkstra(s2);	// 도로를 지난 후에 다익스트라

			for (int i = 0; i < t; i++) {
				int dst = result[i];// 후보지 인덱스
				if (result1[s2] + result2[dst] == result1[dst]) {
					answer.add(dst);
				}
			}
			answer.stream()
			.sorted()
			.forEach(d->System.out.print(d+" "));
			System.out.println();
		}
		bw.close();
		br.close();
	}
	
	public static int[] dijkstra (int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d = new int [n+1];
		
		// 거리 초기화 
		for (int i = 0; i <= n; i++) {
			d[i] = INF;
		}
		d[start] = 0;
		pq.add(new Node(start, d[start])); // 시작 정보
		
		while (!pq.isEmpty()) {
			Node cur_node = pq.poll();
			int cur_pos = cur_node.pos;
			int cur_cost = cur_node.cost;
			
			if (d[cur_pos]<cur_cost)
				continue;
			
			for (int i = 0; i < mat.get(cur_pos).size(); i++) {
				int next_pos = mat.get(cur_pos).get(i).dst;
				int next_cost = mat.get(cur_pos).get(i).cost;
				
				int cost = next_cost + cur_cost;
				if (d[next_pos] > cost) {
					d[next_pos] = cost;
					pq.add(new Node(next_pos, d[next_pos]));
				}
			}
		}
		
		return d;
	}
}