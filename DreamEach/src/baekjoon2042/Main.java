package baekjoon2042;

import java.util.Scanner;

public class Main {
	static int N,M,K;			// 수의 개수, 변경 회수, 구간의 합 출력 회수
	static long [] array;		// input array
	static long [] tree;		// segment tree
	static long [] answer;	// answer array
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();	M = in.nextInt();	K = in.nextInt();		
		array = new long [N];		
		tree = new long [4*N];		// 가장 가까운 2의 거듭제곱으로 n을 올림한뒤 2를 곱함(종만북), 귀찮으면 4n
		answer = new long[K]; 
		
		for (int i = 0; i < N ; i++) {
			array[i] = in.nextLong();
		}
		init (1, 0, N-1);
		
		int k = 0;
		for (int i = 0; i < M+K; i++) {
			int a = in.nextInt();								// command 
						
			if (a == 1) {
				// 업데이트 
				int b = in.nextInt();							// index
				long c = in.nextLong();						// value								
				update (1, 0, N-1, b-1, c-array[b-1]);
				array[b-1] = c;									// update org array
			
			} else if (a == 2){
				// 구간합 출력
				int b = in.nextInt();							// start
				int c = in.nextInt();							// end				
				answer[k++] = sum(1, 0, N-1, b-1, c-1);
			}
		}
		// answer 
		for (int i = 0; i < k; i++) {
			System.out.println(answer[i]);
		}
		
	}
	
	static long init (int node, int start, int end) {
		if (start == end) {
			// leaf node
			return tree[node] = array[start];
		} else {
			// not leaf node
			return tree[node] = init (node*2, start, (start+end)/2) + init (node*2+1, (start+end)/2+1, end);	// init left child node and right child node 
		}
	}
	
	static long sum (int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		} 
		if (left <= start && end <= right) {
			return tree[node];
		}
		return sum (node*2, start, (start+end)/2, left, right) + sum (node*2+1, (start+end)/2+1, end, left, right);	 //sum left child node and right child node
	}
	
	static void update (int node, int start, int end, int index, long diff) {
		if (index < start || index > end) 
			return;
		tree[node] += diff;
		
		if (start != end) {
			update (node*2, start, (start+end)/2, index, diff);			// left child update
			update (node*2+1, (start+end)/2+1, end, index, diff);		// right child update
		}
		
	}
}
