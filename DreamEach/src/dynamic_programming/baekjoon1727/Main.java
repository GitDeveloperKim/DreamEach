package dynamic_programming.baekjoon1727;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;	// men
	static int M;	// women 
	
	static int [] men;
	static int [] women;
	
	static int [][] dp;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();	// men number 
		M = in.nextInt();	// women number 
		
		men = new int[N];
		women = new int[M];
		
		dp = new int[N][M];	// N < M 
		
		for (int i = 0; i<N; i++) {
			men[i] = in.nextInt();
		}
		for (int i = 0; i<M; i++) {
			women[i] = in.nextInt();
		}
		
		if (N>M) {
			int [] temp = men;
			men = women;
			women = temp;
			dp = new int[M][N];
		}
		
		Arrays.sort(men);
		Arrays.sort(women);
		
		for (int i = 0; i< men.length; i++) {			
			for (int j=i; j < women.length; j++) {				
				if (i==0) {
					// basis 
					if (j==0){
						dp[0][0] = Math.abs(men[i] - women[j]);
					} else {
						dp[0][j] = Math.min(dp[0][j-1], Math.abs(men[i]-women[j]));
					}
				} else {
					if (i == j) {
						dp[i][j] = Math.abs(men[i]-women[j])+dp[i-1][j-1];
					} else {
						dp[i][j] = Math.min(dp[i][j-1], Math.abs(men[i]-women[j])+dp[i-1][j-1]);
					}
				}
			}
		}
		
		if (N>M)
			System.out.println(dp[M-1][N-1]);
		else 
			System.out.println(dp[N-1][M-1]);
	}
}