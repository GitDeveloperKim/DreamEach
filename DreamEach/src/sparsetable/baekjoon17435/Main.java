package sparsetable.baekjoon17435;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// 백준 17435번 합성함수와 쿼리 
// https://www.acmicpc.net/problem/17435
// https://blog.naver.com/jinhan814/222144331762
public class Main {
	public static int n;
	public static int x;
	public static int Q;
	public static int [][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		dp = new int [n+1][20];		// 1 << (log2(20_0000)+1) == 20
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int j = 1; j <= n; j++) {
			dp [j][0] = Integer.parseInt(st.nextToken());
		}
		
		for (int j = 1; j < 20; j++) {
			for (int i = 1; i <= n; i++) {
				dp[i][j] = dp[ dp[i][j-1] ][j-1];	// sparse table
			}
		}
		
		Q = Integer.parseInt(br.readLine());
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for (int i = 0; a!=0; i++) {
				//System.out.println(a&1);
				if ((a&1) == 1) 
					b = dp[b][i];
				a >>= 1;
			}
			sb.append(b+"\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}

