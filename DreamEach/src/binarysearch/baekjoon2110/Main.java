package binarysearch.baekjoon2110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2110 
// https://m.blog.naver.com/PostView.nhn?blogId=vjhh0712v&logNo=221683269377&proxyReferer=https:%2F%2Fwww.google.co.kr%2F
public class Main {
	public static int N; /// 2<= N <= 200000
	public static int C; // 2<= C<= N
	public static int [] x;
	public static int s;
	public static int e;
	public static int mid;
	public static int cnt;
	public static int answer;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		x = new int [N];
		
		for (int i = 0; i < N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(x);
		
		s = 1;
		e = x[N-1];
		answer = 0;
				
		while (s <= e) {
			cnt = 1;
			mid = (s+e)/2;			
			int std = x[0];
			
			for (int i = 0; i < N; i++) {
				if (x[i] - std >= mid) {
					cnt++;
					std = x[i];
				}
			}
			if (cnt < C) {
				e = mid-1;
			} else {
				answer = Math.max(answer, mid);
				s = mid+1;
			}
		}
		
		System.out.println(answer);
	}
}
