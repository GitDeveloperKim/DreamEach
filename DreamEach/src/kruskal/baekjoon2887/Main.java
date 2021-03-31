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
 * �˾ƾ� �� �˰��� : ũ�罺�� 
 * ������ �����ϱ� ����, ���� ��α� ���� 
 * n^2 �� �̿��Ͽ� ����ġ�� ����ϸ� 100_0000 * 100_0000 = 100���� �ǹǷ� ������ 
 * https://steady-coding.tistory.com/117
 * https://dev-jk.tistory.com/29
 */

// �༺ ��ǥ 
class Point {
	int x;
	int y;
	int z; 
	int i;	// �༺ �ε��� 
	
	Point (int x, int y, int z, int i) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.i = i;
	}
}

// �༺ �ε��� �� ����ġ�� ���� ���� 
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

// 2887 �༺ �ͳ� 
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
		
		// x ������ �̿��� ���� 
		Arrays.sort(arr, (p1,p2)-> p1.x - p2.x);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].x - arr[i].x;
			
			edge.add(new Edge(start, end, weight));	// ������� ���������� �̿��Ͽ� ���� 
		}
		
		// y ������ �̿��� ���� 
		Arrays.sort(arr, (p1,p2)-> p1.y - p2.y);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].y - arr[i].y;
			
			edge.add(new Edge(start, end, weight)); // ������� ���������� �̿��Ͽ� ���� 
		}
		
		// z ������ �̿��� ���� 
		Arrays.sort(arr, (p1,p2)-> p1.z - p2.z);
		for (int i = 0; i < N-1; i++) {
			int start = arr[i].i;
			int end = arr[i+1].i;
			int weight = arr[i+1].z - arr[i].z;
			
			edge.add(new Edge(start, end, weight)); // ������� ���������� �̿��Ͽ� ���� 
		}
		
		Collections.sort(edge);	// ����ġ�� �̿��� ���� 
		
		for (int i = 0; i < edge.size(); i++) {
			int s = edge.get(i).s;
			int e = edge.get(i).e;
			
			if (find(s) != find(e)) {
				// ���ʷ� ����Ŭ�� ��������� �� Ȯ���ϸ鼭 �ּҰ����� ���� 
				// �ߺ� ���� �ɷ����� 
				union (s, e);
				answer += edge.get(i).w;				
			}
		}
		System.out.println(answer);	// �����ϱ� ����Ƿ� ���� ��α� Ȯ���Ͽ� ǰ 
		
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
