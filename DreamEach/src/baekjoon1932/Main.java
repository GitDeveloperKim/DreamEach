package baekjoon1932;

import java.util.Scanner;

public class Main {
	static int answer = 0;	// answer
	static int [][] triangle;	// triangle
	static int [][] mem;		// memoization
	static int N = 0;			// height
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		
		triangle = new int [N][N];
		mem = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i+1; j++) {
				triangle[i][j] = in.nextInt();
			}
		}
		
		answer = maxTriangle(0,0);
		System.out.println(answer);
	}
	
	public static int maxTriangle (int i, int j) {
		int temp = 0;
		
		temp += triangle[i][j];
		if ( (i+1) < N) {	// end of triangle
			int temp1 = 0;
			int temp2 = 0;
			if (mem[i+1][j] == 0) {
				temp1 = maxTriangle(i+1,j);
				mem[i+1][j] = temp1;
			} else {
				temp1 = mem[i+1][j] ;
			}
			
			if (mem[i+1][j+1] == 0) {
				temp2 = maxTriangle(i+1,j+1);
				mem[i+1][j+1] = temp2;
			} else {
				temp2 = mem[i+1][j+1];
			}
			temp += Math.max(temp1, temp2);
		}
		return temp;
	}
} 

