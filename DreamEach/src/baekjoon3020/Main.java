package baekjoon3020;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int H;
	static int [] up;
	static int [] down;
	static int [] weight;
	static int answer;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);		
		N = in.nextInt();
		H = in.nextInt();		
		answer = 1;		
		int count1 = 0;
		int count2 = 0;
		
		up = new int [N/2];
		down = new int [N/2];
		weight = new int[H];
		
		for (int i=0; i < N; i++) {
			if (i%2 == 0) {
				up[count1] = in.nextInt();
				count1++;
			} else {
				down[count2] = in.nextInt();
				count2++;
			}
		}
		
		Arrays.sort(up);
		Arrays.sort(down);
		
		for (int i = 0; i < H; i++) {
			weight[i] = bsearch(up, (i+1)) + bsearch(down, H-(i+1)+1);			
		}
		Arrays.sort(weight);
		
		int i = 0;
		while ( ((i+1)<H) && weight[i] == weight[i+1]) {
			answer++;
			i++;
		}
		System.out.println(weight[0]+" "+answer);
	}
	
	public static int bsearch(int [] temp, int value) {
		int s = 0;			// start index
		int e = N/2-1;		// end index
		
		while (s<=e ) {
			int m = (s+e)/2;
			if (temp[m] < value) {
				s = m+1;	// change start index
			} else {
				e = m-1;	// change end index
			}
		}		
		int d = N/2 - e -1;
		return d;
	}
}
/*
for (int i = 0; i < count1; i++) {
	System.out.println(up[i]);
}
System.out.println("====");
for (int i = 0; i < count2; i++) {
	System.out.println(down[i]);
}
for (int i = 0; i < H; i++) {
			System.out.println(weight[i]);
}		
		
System.out.println("up"+bsearch(up, (i+1)));
System.out.println("down"+bsearch(down, H-(i+1)+1));
System.out.println("wieght"+weight[i]);

*/
