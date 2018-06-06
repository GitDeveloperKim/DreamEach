package baekjoon4307;
import java.util.Scanner;

public class Main {
	static int [] arr;

	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int T = in.nextInt();
		for (int t = 0; t < T ; t++) { // test case start
			// input start
			int L = in.nextInt();
			int N = in.nextInt();
			arr = new int [N];
			for (int i=0; i < N; i++) {
				arr[i] = in.nextInt();
			} // input end
			
			int min_answer = Math.min(arr[0]-0, L-arr[0]);	// min 의 최대값 
			int max_answer = Math.max(arr[0]-0, L-arr[0]);	// max 의 최대값
			
			for (int i =0; i< N; i++) {
				int temp = Math.min(arr[i]-0,L-arr[i]);
				min_answer = Math.max(temp, min_answer);
				
				int temp2 = Math.max(arr[i]-0,L-arr[i]);
				max_answer = Math.max(temp2, max_answer);
			}
			System.out.println(min_answer+" " + max_answer);
			
		}// test case end 
	}

}