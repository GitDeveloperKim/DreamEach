package baekjoon2579;

import java.util.Scanner;

public class Main {
	static int N;											// the number of stair (N <= 300)
	static int [] value;										// input value ( value <= 10000)
	static int [][]	cache;									// 바로 뒤에서 온것 
	
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		
		value = new int[N+1];			
		cache = new int [N+1][2];		// 0: 한번 건너뛴 값과 더한 값, 1: 두번 건너뛴 값과 더한 값
		
		for (int i=1; i <= N;  i++) {
			value[i] = in.nextInt();
		}
		
		cache[1][0] = value[1];
		cache[1][1] = value[1];
		cache[2][0] = cache[1][1] + value[2]; 	// 한번 건너뛴 값과 더한 값
		cache[2][1] = cache[0][0] + value[2];	// 두번 건너뛴 값과 더한 값
		
		for (int i = 3; i < N+1;  i++) {			
			// 이전 값과 더하는 경우 두번 건너뛰어 온 값만 더할 수 있다.
			cache[i][0] = cache[i-1][1] + value[i];
			// 두번 건너뛴 값[i-2]과 더할값은 한번 뛰어 온 값[0] 이나 두번 뛰어 온값[1] 둘중 어느것을 더해도 상관없다.
			cache[i][1] = Math.max(cache[i-2][1]+value[i], cache[i-2][0]+value[i] );	
			
		}
		System.out.println(Math.max(cache[N][0], cache[N][1]));
		
		/*
		 *	example table
		 *
		 *  0 | 10 | 20 | 15 | 25 | 10 | 20
		 *  0 | 10 | 30 | 35 | 50 | 65 | 65
		 *  0 | 10 | 20 | 25 | 55 | 45 | 75
		 *    
		 */
	}
	/*
	 * to do list 
	static int dfs (int index) {
		
		if (index > N) {
			return 0;
		} 
		int result = value[index];
				
		isVisited[index] = true;
		
		if (index >= 3) {
			if (isVisited[index-1] && isVisited[index]) {
				result += dfs (index+2);
			} else {
				result += Math.max( dfs(index+1), dfs(index+2) );
			}
		} else {
			result += Math.max( dfs(index+1), dfs(index+2) );
		}
		
		return result;
	}*/
	
}
