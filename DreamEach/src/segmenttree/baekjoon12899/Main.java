package segmenttree.baekjoon12899;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 1. ���׸�Ʈ Ʈ�� 
 * 2. ���׸�Ʈ Ʈ���� ������ Ʈ�� ���� 
 * 3. �迭�� 200_0001 ���� ���� ���� �ε����� ���� 
 * 4. �� ���� �ϳ��� ���ö� ���� 1�� ���� 
 * 5. ������ ���� ���� Ʈ���� ������Ʈ �����־� 
 * 6. �ڽ� ����� ���ʰ� ���Ͽ� ����(k)�� �� 
 * 7. �������� Ž������ ���������� Ž������ ã�´� 
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
			// ���� �ڽ� ��庸�� ���簪�� ũ�ų� ���ٸ� ���� Ž���ؾ��� 
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
