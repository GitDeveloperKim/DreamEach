package baekjoon1026;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static int N = 0;
	static int [] A;
	static Integer [] B;
	static int answer = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		A = new int[N];
		B = new Integer[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
		}
		for (int i = 0; i < N; i++) {
			B[i] = in.nextInt();
		}

		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			answer+= (A[i]*B[i]);
		}
		System.out.println(answer);
	}

}
