package binarysearch.baekjoon2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * url : https://www.acmicpc.net/problem/2805
 * @author GitDeveloperKim
 * 1. stream 을 이용한 sorted 배열 받기 
 * 2. 파라매트릭 서치를 이용 (이분 탐색) 알고리즘 구현 
 * 3. 떡볶이 자르기 문제와 똑같음 (참고)
 */
public class Main {
	public static int N;
	public static int M;
	public static int [] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb;
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// stream 으로 sorted 배열 받기 
		input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.sorted()
				.toArray();
		
		int start = 1;
		int end = input[N-1]; // 나무를 자를수 있는 범위는 1~input[n-1] 
		int answer = 0;
		
		while (start <= end) {
			int mid = (start+end)/2;
			long total = 0;
			
			for (int i = 0; i < N; i++) {
				if (input[i] > mid) {
					total += (input[i]-mid);
				}
			}
			if (total < M) {
                // 왼쪽 탐색 
				end = mid-1;
			} else {			
				// total >= M 
                // 오른쪽 탐색 
				answer = mid; // (적어도 M 미터의 나무) 
				start = mid+1;
			}
		}
		System.out.println(answer);
	}	
}
