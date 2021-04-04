package dijkstra.baekjoon18352;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18352
 * 1. 다익스트라 알고리즘 
 * 2. BFS 로도 풀수 있지 않을까?
 * 3. K 값을 갖는 최단거리의 도착지 정보를 출력하면 됨 
 * 
 * @author GitDeveloperKim
 *
 */
class Point implements Comparable<Point> {
	int node;
	int cost;

	Point(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compareTo(Point o) {
		return this.cost - o.cost;
	}

}

public class Main {
	public static int N; // 도시의 갯수 2<= N <= 30_0000
	public static int M; // 도로의 갯수 1<= M <= 100_0000
	public static int K; // 거리의 정보 1<= K <= 300_0000
	public static int X; // 출발도시의 번호 1<= x <= N

	public static int INF = 200_0000;

	public static ArrayList<ArrayList<Point>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Point(b, 1));
		}

		int[] result = dstra(X);
		
		// Arrays.sort(result,1,N+1);	// 이거 아님 
		int count = 0;
		for (int i = 1; i <= N; i++) {	// 이거 오름차순 말하는 거임 
			if (result[i] == K) {
				System.out.println(i);
				count++;
			}
		}
		if (count == 0)
			System.out.println(-1);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int[] dstra(int start) {
		int[] d = new int[N + 1];
		// 거리정보 초기화
		for (int i = 0; i <= N; i++) {
			d[i] = INF;
		}

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			int cur_node = cur.node;
			int cur_cost = cur.cost;

			if (d[cur_node] < cur_cost)
				continue;

			for (int i = 0; i < graph.get(cur_node).size(); i++) {
				Point next = graph.get(cur_node).get(i);
				int next_node = next.node;
				int next_cost = next.cost;
				int cost = cur_cost + next_cost;

				if (d[next_node] > cost) {
					d[next_node] = cost;
					pq.add(new Point(next_node, cost));
				}
			}
		}

		return d;
	}

}
