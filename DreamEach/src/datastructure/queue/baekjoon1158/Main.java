package datastructure.queue.baekjoon1158;

import java.util.Scanner;

//https://www.acmicpc.net/problem/1158

public class Main {
	static int [] arr;	// 원형 큐
	static boolean [] checker; 
	static int N;	// N 명의 사람
	static int K;	// K 순번 
	
	public static void main(String[] args) {
		// 원에서 사람들이 제거되는 순서를 (N,K)- 요세프 순열 
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		K = in.nextInt();
		
		arr = new int [N+1];
		checker = new boolean [N+1];
			// false 
		
		int cur = K; // position 
		
		int index = 1;	// 결괏값 입력 인덱스 
		
		while (true) {
			arr[index] = cur;
			checker[cur]=true;
			
			if (index == N)
				break;
			
			int i = 1;
			int temp = cur;
			while (i != (K+1)) {
				temp = (cur+i)%(N+1);
				if (temp==0){
					cur++;
					continue;
				}
				if (checker[temp] == true) {
					cur++;
					continue;
				} else {
					i++;
				}				
			}	
			cur = temp;
			index++;
		}		
		
		System.out.print("<");
		for (int i = 1;i<N+1;i++) {
			System.out.print(arr[i]);
			if (i != N) {
				System.out.print(", ");
			}
		}
		System.out.print(">");
	}
}