package lis.baekjoon2352;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int [] input;
	public static int [] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		input = new int[n+1];
		dp = new int [n+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = input[1];
		int index = 1;
		
		for (int i = 2; i <= n; i++) {
			if (dp[index] < input[i]) { 
				dp[++index] = input[i]; 
			} else {
				/*
				int j = 1;
				for (j = 1; j <= index;j++) {
					if (dp[j] >= input[i])
						break;
				}*/
				dp[lower_bound(1,index,input[i])] = input[i];
			}
		}
		
		sb.append(index+"\n");
		bw.write(sb.toString());
		
		bw.close();
		br.close();
	}
	
	private static int lower_bound (int s, int e, int v) {
		while (s < e) {
			int mid = (s+e)/2;
			if (dp[mid] >= v) {
				e = mid;
			} else {
				s = mid+1;
			}
		}
		return e;
	}
}


