package segmenttree.baekjoon1395;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1395
 * ���� ����
 * 1. ���׸�Ʈ Ʈ�� 
 * 2. lazy propagation ��, ��� ������ 
 * 3. ����ġ, xor ���� ���� -> ���� - ĳ�ð�  
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
		if (lazy[node] != 0) {	// 0 �̸� ������Ʈ �ʿ� ����, �ι� lazy ������Ʈ�� ������Ʈ ���Ѵ�?! 
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
			// ������ ����� ��� 
			return;
		}		
		if (l <=s && e <= r) {
			// lazy propagation 
			// tree[node] = (e -s+1) - tree[node];	// ������ ũ�� - ������ ����
			lazy[node] = ++lazy[node]%2;			// lazy ���� ����ġ ��ó�� ���� 
			propagation(node, s, e);
			return;
		} 
		int mid = (s+e)/2;
		update (2*node, s, mid, l, r);
		update (2*node+1, mid+1, e, l, r);
		
		tree[node] = tree[2*node] + tree[2*node+1];
	}
	
	public static int query (int node, int s, int e, int l, int r) {
		propagation (node, s, e);	//  �������� ������� 
		if (r < s || e < l)
			return 0;
		if (l <=s && e <= r) {
			return tree[node];
		}
		int mid = (s+e) /2;
		return query (2*node, s, mid, l, r) + query(2*node+1, mid+1, e, l, r);
	}
}
