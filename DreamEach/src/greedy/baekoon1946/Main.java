package greedy.baekoon1946;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static int T;	// 테스트 케이스 
	public static int N;	// 지원자의 숫자 
	
	public static int [][] freshman;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		T = in.nextInt();
		while (T > 0) {
			N = in.nextInt();
			freshman = new int [N][2];
			int FRESH_MIN = 10000000;
			
			for (int i = 0; i<N; i++) {
				// 낮은 숫자일수록 좋은것
				freshman[i][0] = in.nextInt();
				freshman[i][1] = in.nextInt();				
			}		
			
			//Arrays.sort(freshman, new Comp());
			Comparator<int[]> CompJava8 = (int[] a1, int[] a2)-> a1[0] - a2[0];
			Arrays.sort(freshman, CompJava8) ;
			int count = 0;
			for (int i =0; i< N; i++) {				
				if (freshman[i][1] < FRESH_MIN) {
					FRESH_MIN = freshman[i][1];	// 지금까지 찾은 면접 최고 등수 보다 높아야 신입사원으로 뽑힐 수 있다.
					count++;
				}
			}
			System.out.println(count);	 
			T--;
		}
	}
	
	static class Comp implements Comparator<int[]> {
		@Override
		public int compare(int[] o1, int[] o2) {			
			return  o1[0]-o2[0];	// 오름차순 
		}		
	}
}