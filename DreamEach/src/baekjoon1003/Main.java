package baekjoon1003;

import java.util.Scanner;

public class Main {

	static boolean [] visited = new boolean [45];		// 방문여부 
	static int [] cacheZero = new int [45];				// 0 캐시
	static int [] cacheOne = new int [45];				// 1 캐시 
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 0 ; t< T ; t++) {
			int N = in.nextInt();
			int [] a = fibonacci(N);		
			System.out.println(a[0] + " " + a[1]);
		}
	}
	
	public static int[] fibonacci(int n) {		
		int znum = 0;
		int onum = 0;
		
	    if (n == 0) {	    
	    	znum++;
	    } else if (n == 1) {
	    	onum++;	        
	    } else {	 
	    	if (visited[n-2] == false) {
	    		int [] temp = fibonacci(n-2);
	    		znum += temp[0];
	    		onum += temp[1];
	    	} else {
	    		znum += cacheZero[n-2];
	    		onum += cacheOne[n-2];
	    	}	    	    	
	    	if (visited[n-1] == false) {
	    		int [] temp = fibonacci(n-1);
	    		znum += temp[0];
	    		onum += temp[1];
	    	} else {
	    		znum += cacheZero[n-1];
	    		onum += cacheOne[n-1];
	    	}	    	    
	    }	    
	    visited[n]= true;
	    cacheZero[n] = znum;
	    cacheOne[n] = onum;
	    return new int[]{znum, onum};
	}
}