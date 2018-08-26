package baekjoon15489;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long [][] matrix = new long [31][31];
		int R,C,W;			//  (2<=R+W<=30), (2<=C+W<=30), (1<=W<=29), (C<=R)
		long sum = 0;
		
		for (int i = 0; i < 30; i++) {
			for (int j=0; j < i+1; j++) {
				if (j == 0 || j==i) {
					matrix[i][j] = 1L;
				} else {					
					matrix [i][j] = matrix[i-1][j] + matrix[i-1][j-1];					
				}
			}
		}
		
		Scanner in = new Scanner (System.in);
		R = in.nextInt();
		C = in.nextInt();
		W = in.nextInt();
		
		for (int i = R-1; i < W+(R-1); i++) {
			for (int j=C-1; j < i-(R-1)+C ; j++) {				
				sum += matrix[i][j];
			}			
		}
		System.out.println(sum);		
	}
}