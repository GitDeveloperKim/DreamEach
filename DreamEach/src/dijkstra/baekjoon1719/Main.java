package dijkstra.baekjoon1719;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 문제 : 1719 택배 
 https://www.acmicpc.net/problem/1719
 
 참고 
 https://velog.io/@skyepodium/%EB%B0%B1%EC%A4%80-1719-%ED%83%9D%EB%B0%B0
 
 알아야 할 기술 : 
 1. 가중치 그래프일때 인접 리스트를 표현할 때 어떻게 해야하는지 
 -> 이중 연결리스트에 시작점을 첫리스트에, 도착지와 가중치를 배열로 두번째 리스트 같이 넣는다 
 2. 우선순위 큐나 정렬 알고리즘에서 람다를 이용하여 우선순위 조절하는 법 
 3. 다익스트라 알고리즘 
 4. 경로 표현 방법 
 */



public class Main {
	public static int n;								// 잡하장의 갯수 <=200
	public static int m;								// 잡하장 경로 cost <= 10000
	public static ArrayList<ArrayList<int[]>> mat;		// 가중치 그래프 
	public static int [] d;								// 최단거리 
	public static int [] path;							// 경로 추적 
	public static PriorityQueue<int[]> pq;				// 다익스트라 {다음 노드 번호, 지금까지 거쳐온 간선 cost}
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		n = in.nextInt();
		m = in.nextInt();
		
		mat = new ArrayList<ArrayList<int[]>>();
		
		for (int i = 1 ; i <= n+1; i++) {
			mat.add(new ArrayList<>());
		}
		
		d = new int [n+1];
		path = new int [300];	// 잡하장 개수 <= 200
		pq = new PriorityQueue<>((int [] a, int [] b)-> (a[1] > b[1])? 1: -1);	// 우선순위 큐 순서 교정 

		for (int i = 0; i < m; i++) {
			int src = in.nextInt();
			int dst = in.nextInt();
			int value = in.nextInt();
			
			mat.get(src).add(new int[] {dst, value});	// 양방향 그래프임 
			mat.get(dst).add(new int[] {src, value});	// 문제에서 "그 사이를 오가는데 필요한 시간" 
		}
		
		for (int i = 1; i <= n; i++) {
			dijkstra (i);	// 	1~n 노드를 시작점으로 다익스트라 최단거리를 순서대로 구한다 
		}
	}
	
	public static void dijkstra (int src) {
		pq.add(new int[] {src, 0});	// 현재 위치 + 해당 위치에서 가중치, 0 에서 시작 
		
		for (int i = 1; i < n+1; i++) {
			d[i] = Integer.MAX_VALUE;	// 각 시발점마다 초기화 
		}
		d[src] = 0;	// 시작점 
		
		while (!pq.isEmpty()) {
			int [] node = pq.poll();	// 현재 시작점 
			int cur_node = node[0];
			int cur_dist = node[1];
			
			for (int i = 0; i < mat.get(cur_node).size(); i++) {
				int next_node = mat.get(cur_node).get(i)[0];	//현재노드에서 다음 노드로 이동할때 cost
				int cost = mat.get(cur_node).get(i)[1];			//현재노드에서 다음 노드로 이동할때 cost
				if (d[next_node] > cost + cur_dist) {
					d[next_node] = cost + cur_dist;				//최단거리 배열 업데이트 
					path [next_node] = cur_node;				// 정답 출력을 위 next_node 최단거리를 위한 cur_node 인덱스 저장  
					pq.add(new int[] {next_node, d[next_node]}); // 다음노드 큐에 넣기 
				}
			}
		}
		
		// print 
		for (int i = 1; i<= n; i++) {
			if (src == i) {
				System.out.print("- ");
			} else if (path[i] == src) {
				System.out.print(i+" ");
			} else {
				int cur_node = i;
				
				while (true) {
					if (path[cur_node] == src) {
						System.out.print(cur_node+" ");
						break;
					} else {
						cur_node = path[cur_node];
					}
				}
			}
		}
		System.out.println();
	}
}
/*
for (int i = 0; i < n; i++) {
	for (int j = 0; j < mat.get(i).size(); j++) {
		System.out.println(i +" :("+mat.get(i).get(j)[0]+", "+mat.get(i).get(j)[1]+")");
	}
	System.out.println();
}*/
