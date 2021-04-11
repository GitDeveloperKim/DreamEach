package segmenttree.baekjoon9345;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 9345
public class Main {
	public static int N;		// DVD들의 수 
	public static int K;		// 사건의 수
	public static int T;		// 테스트 케이스 
	public static int [] arr;
	public static int [][] seg;	// max = 1, min = 0
		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		
		while (T-- >0) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N];
			seg = new int[4*N][2];
			
			for (int i = 0; i <N; i++) {
				arr[i] = i;	// reference
			}
			
			init (1,0,N-1,0); // min
			init (1,0,N-1,1); // mmax
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int Q = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				if (Q == 0) {					
					update (1, B, arr[A], 0,N-1,0);	// min
					update (1, A, arr[B], 0,N-1,0);	// min
										
					update (1, B, arr[A], 0,N-1,1);	// max
					update (1, A, arr[B], 0,N-1,1);	// max
					
					swap (A,B);
				} else {
					int min = query (1, 0, N-1, A, B, 0); // min
					int max = query (1, 0, N-1, A, B, 1); // max
					
					if (min == A && max == B) {
						System.out.println("YES");
					} else {
						System.out.println("NO");
					}
				}
				
			}			
		}
	}
	
	public static int init (int node_index, int s, int e, int isMax) {
		if (s == e) {
			return seg[node_index][isMax]=s;
		} else {
			int mid = (s+e)/2;
			int left = init(node_index*2, s, mid, isMax);
			int right = init(node_index*2+1,mid+1, e, isMax);
			int result = 0;
			if (isMax == 1) {
				result =  Math.max(left, right);
			} else {
				result = Math.min(left, right);
			}
			return seg[node_index][isMax] = result;
		}
	}
	
	public static int query (int node_index, int s, int e, int l, int r, int isMax) {
		
		// max
		if (r< s || e < l) {
			return (isMax==1)?Integer.MIN_VALUE:Integer.MAX_VALUE;
		} else if (l <= s && e <= r) {
			return seg[node_index][isMax];
		} 
		
		int mid = (s+e)/2;
		int query = 0;
		int left = query(node_index*2, s, mid, l, r, isMax);
		int right = query(node_index*2+1, mid+1, e, l, r, isMax);
		if (isMax==1) {
			query = Math.max(left, right);
		} else {
			query = Math.min(left, right);
		}
		
		return query;
	}
	
	public static int update (int node_index, int target_index, int val, int s, int e, int isMax) {
		if ( !(s<= target_index && target_index <=e) ) { 
			return seg[node_index][isMax];
		}
		
		if (s == e) {
			return seg[node_index][isMax] = val;
		}
		
		int mid = (s+e)/2;
		
		update (node_index*2, target_index, val, s, mid, isMax);
		update (node_index*2+1, target_index, val, mid+1, e, isMax);
		
		if (isMax==1) {
			return seg[node_index][isMax] = Math.max(seg[node_index*2][isMax], seg[node_index*2+1][isMax]);
		} else {
			return seg[node_index][isMax] = Math.min(seg[node_index*2][isMax], seg[node_index*2+1][isMax]);
		}
	}
	
	public static void swap (int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
