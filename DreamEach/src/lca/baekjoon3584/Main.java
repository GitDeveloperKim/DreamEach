package lca.baekjoon3584;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 백준 3584
/**
 * 루트가 1이면 dfs로 depth를 구한다 
 * 없으면 leaf에서 루트까지 타고 올라가며 depth를 구한다
 * @author default
 *
 */
public class Main {
	public static int T;
	public static int N;
	public static int W;
	public static int [] tree;
	public static ArrayList<Integer> [] adj;
	public static int [] parent;	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			adj = new ArrayList[N+1];
			parent = new int[N+1];
			
			for (int i = 0; i <= N; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a가 b의 부모 노드 
				adj[a].add(b);
				parent[b] = a;	// b의 부모 a 
			}
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			int s_depth = getDepth(start);
			int e_depth = getDepth(end);
			
			int answer = solve (start, s_depth, end, e_depth);
			System.out.println(answer);
			
		}
	}
	
	public static int solve (int start, int s_depth, int end, int e_depth) {
		int result = 0;
		
		if (s_depth > e_depth) {
			while (s_depth != e_depth) {
				s_depth--;
				start = parent[start];
			}
		} else if (s_depth < e_depth) {
			while (s_depth != e_depth) {
				e_depth--;
				end = parent[end];
			}
		}
		
		while (start != end) {
			start = parent[start];
			end = parent[end];
		}		
		return start;
	}
	
	
	public static int getDepth (int s) {
		int result = 0;
		int cur = s;
		while (cur != 0) {
			result++;
			cur = parent[cur];
		}
		return result;
	}
}
