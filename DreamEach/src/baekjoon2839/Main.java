package baekjoon2839;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N;
	static int answer;
	static int [] mem;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		mem = new int [N+1];	// can have maximum tree level

		answer = Integer.MAX_VALUE;
		Arrays.fill(mem, 0);
		answer = sugarPackage(N);
		System.out.println(answer == Integer.MAX_VALUE ?-1 :answer-1);
	}
	
	static int sugarPackage (int n) {
		if (n==0) {
			return 1;
		}
		int a = 0;
		int b = 0;
		int c = 0;
		
		if (n-3<0 && n-5<0) {
			return Integer.MAX_VALUE;	// can not make exactly amount
		} else {
			if (mem[n] == 0) {
				a = sugarPackage(n-3);
				b = sugarPackage(n-5);
				if (a == Integer.MAX_VALUE && b == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				if (a != Integer.MAX_VALUE && b == Integer.MAX_VALUE) {
					return a+1;
				}
				if (a == Integer.MAX_VALUE && b != Integer.MAX_VALUE) {
					return b+1;
				}
				
				if (a >= b) {
					c = b;
				} else {
					c = a;
				}
				mem[n] = c;
			} else {
				c = mem[n];
			}
			return c+1;
		}
		
	}

}
