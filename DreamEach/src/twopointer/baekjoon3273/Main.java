package twopointer.baekjoon3273;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * https://www.acmicpc.net/problem/3273
 * 1) stream �� �̿��Ͽ� �迭�� �ִ¹� ���� 
 * 2) two �����ͷ� ����Ǯ�� �ϴ� �� Ȯ��  
 * 3) ���� Ž������ Ǯ�̰� ������ 
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
		// �����Ͽ� �迭�� �ִ¹� Stream
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
				s++; // sum �� �������Ѿ� x�� ã�� �� �����Ƿ� s++ 
			else 
				e--; // sum �� �ٿ��� x�� ã�� �� �����Ƿ� e--
		}
		System.out.println(answer);
	}

}
