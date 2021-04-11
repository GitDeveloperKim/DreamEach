package disjointset.baekjoon1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	public static int N;
	public static int M;
	public static int [] parent;
	public static List<Integer> []adj;
	public static List <Integer> dst;
	
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new List[N+1];
		dst = new ArrayList<> ();
		parent = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					adj[i].add(j);
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			dst.add(Integer.parseInt(st.nextToken()));
		}
		
		boolean answer = true; 
		
		for (int i = 0; i < dst.size()-1; i++) {
			int p1 = dst.get(i);
			int p2 = dst.get(i+1);
			if (find(p1) != find(p2)) {
				answer = false;
				break;
			}
		}
		
		System.out.println(answer? "YES":"NO");
	}
	
	public static int find (int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}		
	}
	
	public static void union (int x, int y) {
		x = find (x);
		y = find (y);
		
		if (x < y) 
			parent[y] = x;
		else 
			parent[x] = y;
	}

}
