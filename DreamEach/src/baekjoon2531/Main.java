package baekjoon2531;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

	static int N; // 접시의 수					2<=N<=30000
	static int d; // 초밥의 가짓수				2<=d<=3000
	static int k; // 연속해서 먹는 접시 수	2<=k<=3000 (k<=N)
	static int c;	// 쿠폰 번호					1<=c<=d
	
	static int [] sushi;
	static int [] buffer;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);				
		N = in.nextInt();	d = in.nextInt();	k = in.nextInt();	c = in.nextInt();		
		sushi = new int [N];
		
		HashSet <Integer> set = new HashSet<>();
		buffer = new int[3009];		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			sushi[i] = in.nextInt();			
		}
		
		// init 
		set.add(c);						// 쿠폰은 그냥 디폴트		
		buffer[c]++;
		
		for (int i = 0; i < k; i++) {
			set.add(sushi[i]);
			buffer[sushi[i]]++;
		}
		answer = set.size();
		
		for (int i = k; i < N+k; i++) {
			buffer[ sushi[(i-k)%N] ]--;			// 앞부분	
			if (buffer[ sushi[(i-k)%N] ] == 0){
				set.remove(sushi[(i-k)%N]);		// 중복 없으면 제거				
			}
			set.add(sushi[i%N]);
			buffer[sushi[i%N]]++;
						
			answer = Math.max(answer, set.size());
		}
		System.out.println(answer);
	}	
}