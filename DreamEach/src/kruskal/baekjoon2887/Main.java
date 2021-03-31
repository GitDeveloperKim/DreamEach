package kruskal.baekjoon2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  @author ergla88@gmail.com
 * url : https://www.acmicpc.net/problem/2887
 * 알아야 할 알고리즘 : 크루스컬 
 * 개념을 이해하기 힘듬, 여러 블로그 참조 
 * n^2 를 이용하여 가중치를 계산하면 100_0000 * 100_0000 = 100억이 되므로 터진다 
 * https://steady-coding.tistory.com/117
 * https://dev-jk.tistory.com/29
 */

// 행성 좌표 
class Point {
	int x;
	int y;
	int z; 
	int i;	// 행성 인덱스 
	
	Point (int x, int y, int z, int i) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.i = i;
	}
}

// 행성 인덱스 간 가중치로 간선 정보 
class Edge implements Comparable<Edge>{
	int s; 	// start node index
	int e; 	// end node index
	int w; 	// weight 
	Edge (int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.w - o.w;
	}
}

// 2887 행성 터널 
public class Main {
	public static int N;
	public static Point [] arr;
	public static List <Edge> edge;
	public static int [] parent;
	public static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st; 
		
		N = Integer.parseInt(br.readLine());
		arr = new Point [N];
		parent = new int [N];
		edge = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			arr[i] = new Point (x,y,z,i);
		}
		
		// x 정보를 이용해 정렬 
		Arrays.sort(arr, (p1,p2)-> p1.x - p2.x);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].x - arr[i].x;
			
			edge.add(new Edge(start, end, weight));	// 만들어진 간선정보를 이용하여 연결 
		}
		
		// y 정보를 이용해 정렬 
		Arrays.sort(arr, (p1,p2)-> p1.y - p2.y);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].y - arr[i].y;
			
			edge.add(new Edge(start, end, weight)); // 만들어진 간선정보를 이용하여 연결 
		}
		
		// z 정보를 이용해 정렬 
		Arrays.sort(arr, (p1,p2)-> p1.z - p2.z);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].z - arr[i].z;
			
			edge.add(new Edge(start, end, weight)); // 만들어진 간선정보를 이용하여 연결 
		}
		
		Collections.sort(edge);	// 가중치를 이용해 정렬 
		
		for (int i = 0; i < edge.size(); i++) {
			int s = edge.get(i).s;
			int e = edge.get(i).e;
			
			if (find(s) != find(e)) {
				// 차례로 사이클이 만들어지는 지 확인하면서 최소값으로 연결 
				// 중복 값을 걸러낸다 
				union (s, e);
				answer += edge.get(i).w;				
			}
		}
		System.out.println(answer);	// 이해하기 힘드므로 여러 블로그 확인하여 품 
		
	}
	public static int find (int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}	
	}
	
	public static void union (int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a!=b) {
			parent[b] = a;
		}
	}
}
