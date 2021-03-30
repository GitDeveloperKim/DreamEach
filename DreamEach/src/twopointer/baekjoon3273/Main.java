package twopointer.baekjoon3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * https://www.acmicpc.net/problem/3273
 * 1) stream 을 이용하여 배열에 넣는법 정리 
 * 2) two 포인터로 문제풀이 하는 법 확인  
 * 3) 이진 탐색으로 풀이가 가능함 
 * 4) https://dalconbox.tistory.com/172
 * 
 */

public class Main {
	public static int n;
	public static int x;
	public static int [] arr;
	public static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 정렬하여 배열에 넣는법 Stream
		arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.sorted()
				.toArray();
		
		int x = Integer.parseInt(br.readLine());
		
		// two pointer 
		int s = 0;
		int e = n-1;
		
		while (s < e) {
			int sum = arr[s] + arr[e];
			if (sum == x)
				answer++;
			if (sum <= x)
				s++; // sum 을 증가시켜야 x를 찾을 수 있으므로 s++ 
			else 
				e--; // sum 을 줄여야 x를 찾을 수 있으므로 e--
		}
		System.out.println(answer);
	}

}
