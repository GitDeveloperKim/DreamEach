package segmenttree.baekjoon2357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N; // 1<= N <= 100000
	public static int M; // 1<= M <= 100000
	public static int [] arr;
	public static int [] min_tree;
	public static int [] max_tree;
	public static List<int[]> range;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		min_tree = new int [4*N];
		max_tree = new int [4*N];
		range = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			range.add(temp);
		}
		init_min (1, 1, N);
		init_max (1, 1, N);
		
		for (int [] temp: range) {
			System.out.println(search_min(1,1,N,temp[0],temp[1]) + " " + search_max(1,1,N,temp[0],temp[1])) ;
		}
	}
	
	public static int init_min (int node, int start, int end) {
		if (start == end) {
			return min_tree[node] = arr[start];
		} else {
			return min_tree[node] = Math.min(init_min (node*2, start, (start+end)/2), init_min(node*2+1, (start+end)/2+1, end)); 
		}
	}
	
	public static int init_max (int node, int start, int end) {
		if (start == end) {
			return max_tree[node] = arr[start];
		} else {
			return max_tree[node] = Math.max(init_max (node*2, start, (start+end)/2), init_max (node*2+1, (start+end)/2+1, end)); 
		}
	}
	
	public static int search_min (int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		} 
		if (left <= start && end <= right) {
			return min_tree[node];
		}
		
		int temp1 = search_min(node*2, start, (start+end)/2, left, right);
		int temp2 = search_min(node*2+1, (start+end)/2+1, end, left, right);
		return Math.min(temp1, temp2);
	}
	
	public static int search_max (int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		} 
		if (left <= start && end <= right) {
			return max_tree[node];
		}
		
		int temp1 = search_max(node*2, start, (start+end)/2, left, right);
		int temp2 = search_max(node*2+1, (start+end)/2+1, end, left, right);
		return Math.max(temp1, temp2);
	}	
}
