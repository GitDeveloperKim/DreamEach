package baekjoon14501;

import java.util.Scanner;

public class Main {
	static int N = 0;						// case 
	static int [] T;							// consult time
	static int [] P;							// payment
	static int [] mem;	 					// memoization
	static boolean [] isVisited;			// visited 
	
	public static void main(String[] args) {
			// init start 
			Scanner in = new Scanner(System.in);		
			N = in.nextInt();
			T = new int[N];
			P = new int[N];
			mem = new int[N];
			isVisited = new boolean [N];
			
			for (int i = 0; i < N; i++) {
				T[i] = in.nextInt();
				P[i] = in.nextInt();
			}			
			int answer = 0;
			// init end 
			
			for (int i = 0; i < N; i++) {
				int temp = DP(i);
				if (temp >= answer) {
					answer = temp;
				}
			}			
			System.out.println(answer);
	}
	
	static int DP (int index) {
		int answer =0;
		if (index+T [index] <= N) {
			answer = P [index];			
		} else {			
			 answer = 0;
		}
		
		int temp = 0;	// max payment 
		for (int i=index+T [index]; i<N; i++) {
			if (isVisited[i] == false) {
				mem[i] = DP(i);
				isVisited[i] = true;
			}
			if (mem[i]>temp) {
				temp = mem[i];
			}
		}
		answer += temp;
		return answer;
	}
}


/*
for (int i = 0; i < N; i++) {
	System.out.println(T[i]+" " + P[i]);
}*/
