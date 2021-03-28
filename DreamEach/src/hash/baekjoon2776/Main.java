package hash.baekjoon2776;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// https://www.acmicpc.net/problem/2776
// https://blog.naver.com/ka28/222187079267
// hash map, arraylist 보다 hash set이 더 빠르다??
public class Main {
	public static int T;
	public static int N;
	public static int M;
	public static HashSet<Integer> note1;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			note1 = new HashSet<>();
			
			String [] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				note1.add(Integer.parseInt(str[i]));
			}
			
			M = Integer.parseInt(br.readLine());
			
			str = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				int temp = Integer.parseInt(str[i]);				
				sb.append(note1.contains(temp)?"1\n":"0\n");				
			}
		}
		System.out.print(sb);		
	}
}
