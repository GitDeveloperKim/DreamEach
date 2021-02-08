package twopointer.baekjoon2003;

import java.util.Scanner;

// https://www.acmicpc.net/problem/2003

public class Main {
	public static int N;
	public static int M;
	public static long [] arr;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		M = in.nextInt();
		arr = new long [N];
		
		// input
		for (int i = 0; i < N; i++) {
			arr [i] = in.nextInt();
		}

		int i = 0;
		int j = 0;
		int count = 0;
		long sum = 0; 
		
		while (true){			
			if (sum >= M) {
				// 왼쪽 올라갈 때 
				sum -= arr[i++];
			} else if (j ==N){
				break;
			} else {
				// 오른쪽 올라갈 때
				sum += arr[j++];
			}
			
			if (sum == M){
				count++;
			}
				
		}
		System.out.println(count);
	}
}
