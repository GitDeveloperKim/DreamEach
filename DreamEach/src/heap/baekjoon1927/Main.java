package heap.baekjoon1927;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N;		// 경우의 수 입력 
	static int x;		// 커맨드 입력 
	static int [] heap; // 2*N+1 왼쪽 자식, 2*N+2 오른쪽 자식
	static int leaf;	// 새로 집어 넣어야하는 노드의 인덱스를 가리킴 
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		heap = new int [2*N+1];	// 배열 개수를 몇개로 해야 최적화 일까 ... 
		
		int n= 0;
		leaf = 0;
		
		while(n<N) {
			x = in.nextInt();
			if (x == 0) {
				pop_heap();
			} else {
				buildHeap(x);
			}
			n++;
		}		
	}
	
	public static void buildHeap(int x) {
		heap[leaf] = x;
		int idx = leaf;
		
		while (idx > 0 && heap[(idx-1)/2] > heap[idx]) {
			swap (idx, (idx-1)/2);
			idx = (idx-1)/2;	// 루트 노드는 (idx-1)/2 로 찾아간다. 
		}
		leaf++;
	}
	
	public static void pop_heap () {
		if (leaf-1 < 0) {
			System.out.println(0);
			return;
		}
		
		System.out.println(heap[0]);
		
		heap[0] = heap[--leaf];	// 최대 힙 끄내고 leaf 노드를 가져온
		heap[leaf+1]=0;			// leaf 있던 자리에 0으로 초기
		
		int idx = 0;
		while (true) {
			int left = idx *2 +1;
			int right = idx *2 + 2;
			
			if (left > leaf) 
				break;
			int next = idx;
			if (heap[idx] > heap[left])
				next = left;
			if (right < leaf && heap[next] > heap[right])
				next = right;
			if (next == idx)
				break;
			swap(idx, next);	// leaf 노드가 새로 자리를 찾아간다 
			idx = next;
		}
	}
	
	public static void swap (int from, int to) {
		int temp = heap[from];
		heap[from] = heap[to];
		heap[to] = temp;
	}
}
