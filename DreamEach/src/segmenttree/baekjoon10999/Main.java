package segmenttree.baekjoon10999;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	public static long [] input;
	public static long [] tree;
	public static long [] lazy;
	public static int N;	// ���� ���� 
	public static int M;	// ������ �Ͼ�� Ƚ�� 
	public static int K;	// ������ ���� ���ϴ� Ƚ�� 
	
	public static void main(String[] args) throws IOException {
		// �Է� 
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// Ʈ���� ũ��� ������ �����ұ� 
		input = new long[N+1];
		tree = new long [N*4];
		lazy = new long [N*4];
		
		for (int i = 1; i <= N; i++) {
			long temp = Long.parseLong(br.readLine());
			input[i] = temp;
		}
		
		make_tree (1, 1, N);
		
		for (int i = 1; i <= (M+K); i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if (cmd == 1) {
				// ������ ���ϱ� ������Ʈ  
				int val = Integer.parseInt(st.nextToken());	// long���� �޾ƾ� �ϴ°� �ƴѰ�?		
				update (1, val, 1, N, s, e);
			} else if (cmd == 2) {
				// ����ϱ� 
				sb.append(query_tree (1, 1,N, s, e) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static long make_tree (int node_index, int start, int end) {
		if (start == end) {
			// leaf_node
			return tree[node_index] = input[start];	// �Է°��� ���� �� Ʈ���� �־���� 
		} else {
			int mid = (start+ end)/2;
			return tree[node_index] = make_tree(node_index*2, start, mid) + make_tree(node_index*2+1, mid+1, end);
		}
	}
	
	public static void propagation (int node_index, int s, int e) {
		if (lazy[node_index] != 0) {
			// ������ ��찡 ����
			if (s != e) {
				// ���� ��尡 �ƴ� ��� 
				lazy[node_index * 2] += lazy[node_index];
				lazy[(node_index * 2) + 1] += lazy[node_index];
			}
			tree[node_index] += ((e-s+1) * lazy[node_index]);
			lazy[node_index] = 0;
		}
	}
	
	public static void update (int node_index, long val, int s, int e, int l, int r) {
		// ������Ʈ ���� lazy ���� �ֳ� ���Ѵ� 
		propagation (node_index, s, e);
		if (e < l || s > r) {
			return ;	// 0 ???? 
		}
		if (l <= s && e <= r) {
			// lazy ���� �ְ� propagation�� �ҷ��� �Ǵ� ���� (���簪 ������Ʈ ���ְ� �ڽĵ� lazy�� �ֱ�)
			tree[node_index] += ((e-s+1) * val);			
			if (s!= e) {
				lazy [node_index*2] += val;
				lazy [node_index*2+1] += val;
			}
			return;
		}
		int mid = (s+e)/2;
		update (node_index*2, val, s, mid, l, r);
		update (node_index*2+1, val, mid+1, e, l, r);
		tree[node_index] = tree[node_index * 2] + tree[node_index * 2 + 1];
		
	}
	public static long query_tree (int node_index, int s, int e, int l, int r) {
		propagation(node_index, s, e);
		if (e < l || s > r) {
			return 0;
		} 
		if (l <= s && e <= r) {
			return tree[node_index];
		}
		int mid = (s+e)/2;
		return query_tree(node_index*2, s, mid, l, r) + query_tree(node_index*2+1,mid+1, e, l, r);
	}
}
