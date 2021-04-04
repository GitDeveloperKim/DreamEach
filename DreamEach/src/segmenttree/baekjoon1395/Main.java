package segmenttree.baekjoon1395;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1395
 * 백준 스위
 * 1. 세그먼트 트리 
 * 2. lazy propagation 왜, 어떻게 쓰는지 
 * 3. 스위치, xor 연산 공식 -> 구간 - 캐시값  
 * tree[node] = (end - start + 1) - tree[node] 
 *  
 * @author GitDeveloperKim
 *
 */
public class Main {
	public static int N;
	public static int M;
	public static int [] tree;
	public static int [] lazy; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int [4*N];
		lazy = new int [4*N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (o == 0) {
				// update 
				update(1, 1, N, s, e);
			} else {
				// query 
				sb.append(query (1, 1, N, s, e)+"\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void propagation (int node, int s, int e) {
		if (lazy[node] != 0) {	// 0 이면 업데이트 필요 없음, 두번 lazy 업데이트면 업데이트 안한다?! 
			// lazy propagation 
			if (s != e) {				
				lazy[2*node] = ++lazy[2*node]%2;
				lazy[2*node+1] = ++lazy[2*node+1]%2;
			}
			tree[node] = (e -s+1) - tree[node];
			lazy[node] = 0;
		}
	}
	
	public static void update (int node, int s, int e, int l, int r) {
		propagation (node, s, e);
		
		if (r < s || e < l) {
			// 구간을 벗어나는 경우 
			return;
		}		
		if (l <=s && e <= r) {
			// lazy propagation 
			// tree[node] = (e -s+1) - tree[node];	// 구간의 크기 - 구간합 갯수
			lazy[node] = ++lazy[node]%2;			// lazy 값도 스위치 값처럼 연산 
			propagation(node, s, e);
			return;
		} 
		int mid = (s+e)/2;
		update (2*node, s, mid, l, r);
		update (2*node+1, mid+1, e, l, r);
		
		tree[node] = tree[2*node] + tree[2*node+1];
	}
	
	public static int query (int node, int s, int e, int l, int r) {
		propagation (node, s, e);	//  쿼리문도 해줘야함 
		if (r < s || e < l)
			return 0;
		if (l <=s && e <= r) {
			return tree[node];
		}
		int mid = (s+e) /2;
		return query (2*node, s, mid, l, r) + query(2*node+1, mid+1, e, l, r);
	}
}
