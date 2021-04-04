package segmenttree.baekjoon12899;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 1. 세그먼트 트리 
 * 2. 세그먼트 트리의 최적의 트리 갯수 
 * 3. 배열로 200_0001 개의 수에 대한 인덱스를 지정 
 * 4. 그 값이 하나씩 들어올때 마다 1씩 증가 
 * 5. 증가한 수를 구간 트리에 업데이트 시켜주어 
 * 6. 자식 노드의 왼쪽과 비교하여 순차(k)와 비교 
 * 7. 왼쪽으로 탐색할지 오른쪽으로 탐색할지 찾는다 
 * 
 * @author GitDeveloperKim 
 *
 */
public class Main {
	public static int N;
	public static int [] tree; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		tree = new int [1 << (my_log(200_0000)+1)];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			if (c == 1) {
				update (1, 1, 200_0002, x, 1);
			} else {
				// kth 
				int answer = kth(1, 1, 200_0002, x);
				sb.append(answer+"\n");
				update (1, 1, 200_0002, x, -1);				
			}
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	public static void update (int node, int start, int end, int x, int value) {
		if (x < start || end < x )
			return;
		if (start == end) {
			tree[node] += value;
			return;
		} 
		int mid = (start + end)/2;
	
		update (2*node, start, mid, x, value);
		update (2*node+1, mid+1, end, x, value);
		tree[node] = tree[2*node] + tree[2*node+1];
	}
	
	public static int kth (int node, int start, int end, int k) {
		if (start == end) {
			return start;
		} else {
			int mid = (start+end)/2;
			// 왼쪽 자식 노드보다 현재값이 크거나 같다면 우측 탐색해야함 
			if (k > tree[2*node]) 
				return kth(2*node+1, mid+1, end, k-tree[node*2]);
			else 
				return kth(2*node, start, mid, k);
		}
	}
	
	public static int my_log (int x) {
		return (int)Math.ceil(Math.log10(x)/Math.log10(2));
	}

}
