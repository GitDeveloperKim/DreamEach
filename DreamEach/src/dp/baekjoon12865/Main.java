package dp.baekjoon12865;

import java.util.Scanner;

public class Main {
	public static int N,K;
	public static int []w = new int[100000];
	public static int []v = new int[100000];
	public static int [][] d = new int [101][100001];
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		K = in.nextInt();
		
		for (int i = 1; i <= N; i++) {
			w[i] = in.nextInt();
			v[i] = in.nextInt();
		}
				
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++){
				if (j-w[i] >= 0) {
					d[i][j] = Math.max(d[i-1][j],d[i-1][j-w[i]] + v[i]);					
				} else {
					d[i][j] = d[i-1][j];
				}
			}
		}
		System.out.println(d[N][K]);
	}
}