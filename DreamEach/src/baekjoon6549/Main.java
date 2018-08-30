package baekjoon6549;

import java.util.Scanner;

public class Main {
	static int N = 0;
	static long [] arr;
	static int [] tree;
	static int [] answer;
	static final int INF = 0;
	
	// histogram 넓이 
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		while ((N = in.nextInt()) !=0) {
			arr = new long[N+1];
			tree = new int[4*N];
			for (int i = 1; i <= N; i++) {
				arr[i] = in.nextLong();
			}
			init (1, 1, N);
			
			long answer = query(1,1,N);
			System.out.println(answer);
		}		
	}
	
	// return min index
	static int init (int node, int start, int end) {
		if (start == end) {
			// leaf node 
			return tree[node] = start;
		} else {
			// parent node 
			int temp1 = init (node*2, start, (start+end)/2);
			int temp2 = init (node*2+1, (start+end)/2 +1, end);			
			return tree[node] = (arr[temp1] > arr[temp2])  ? temp2 : temp1;
		}
	}
	
	static int minIndex (int node, int start, int end, int left, int right) {
		if (left > end || right < start) return INF;
		if (left <= start && end <= right) {
			return tree[node];
		}
		int temp1 = minIndex (node*2, start, (start+end)/2, left, right);
		int temp2 = minIndex (node*2+1, (start+end)/2+1, end, left, right);
		
		if (temp1 == INF && temp2 == INF){
			return INF;
		} else if (temp1 == INF && temp2 != INF) {
			return temp2;
		} else if (temp1 != INF && temp2 == INF) {
			return temp1;
		}		
		return (arr[temp1] > arr[temp2])  ? temp2 : temp1;		
	}
	
	
	static long query (int root, int start, int end) {
		int index = minIndex (1, 1, N, start, end);
		long weight = arr[index] * (end - start + 1);
		
		long temp1 = 0, temp2 = 0;
		if (start <= index-1){
			temp1 = query (root, start, index-1);
		}
		if (index+1 <= end) {
			temp2 = query (root, index+1, end);
		}
		long temp = Math.max(temp1, temp2);		
		
		return Math.max(weight, temp);
	}
}