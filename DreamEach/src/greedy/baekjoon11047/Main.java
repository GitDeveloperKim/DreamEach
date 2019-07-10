package greedy.baekjoon11047;

import java.util.Scanner;

public class Main {
	static int N;	// 동전의 종류 
	static int K;	// 가치의 
	static int []A;	// 동전의 가
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int answer = 0;
		N = in.nextInt();
		K = in.nextInt();
		
		A = new int[N];
		for (int i=0;i<N;i++) {
			A[i] = in.nextInt();
		}
		
		for (int i = N-1; i >= 0; i--) {
			// 제일 큰 단위에서 부터 계속 줄여 나가면 된다.
			while (K >= A[i]) {
				answer++;
				K -= A[i];
			}
		}
		System.out.println(answer);
		
	}

}
