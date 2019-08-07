package dp.baekjoon2293;

import java.util.Scanner;

public class Main {

	public static int n;	// 동전의 종류 
	public static int k;	// 목표치 
	public static int [] c = new int [101];		// 동전의 값 
	public static int [] d = new int[100001]; 	// 경우의 수 누적 
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		n = in.nextInt();
		k = in.nextInt();
		
		for (int i = 0; i < n; i++) {
			c[i] = in.nextInt();
		}
		d[0] = 1;	// 아무것도 고르지 않은 경우의 수 1
		for (int i = 0; i < n; i++) {
			for (int j = c[i]; j <= k; j++) {
				d[j] += d[j-c[i]];	// d(n) = d(n-1) + d(n-2) + d(n-5)
			}
		}
		
		System.out.println(d[k]);
	}
}
