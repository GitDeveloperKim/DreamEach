package treeDP.baekjoon15681;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 정점 U를 루트로 하는 서브트리에 속한 정점의 수 출력 
 * @author GitDeveloperKim
 *
 */
public class Main {
	public static int N;	// 트리의 정점의 수
	public static int R;	// 루트의 번호 
	public static int Q;	// 쿼리의 수 
	public static ArrayList <ArrayList<Integer>> tree;
	public static int [] size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		tree = new ArrayList<>();
		size = new int [N+4];
		
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			tree.get(u).add(v);
			tree.get(v).add(u);
		}
		
		dfs (R, 0);
		
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(size[q]).append("\n");
			//System.out.println(size[q]);
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	} // end of Main 
	
	public static int dfs (int cur, int prev) {
		size[cur] = 1;
		
		for (int i = 0; i < tree.get(cur).size(); i++) {
			int next = tree.get(cur).get(i);
			if (next == prev)
				continue;
			size[cur] += dfs(next, cur);
		}
		
		return size[cur];
	}
}
