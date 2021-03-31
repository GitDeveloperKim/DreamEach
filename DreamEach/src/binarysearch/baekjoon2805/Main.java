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
 * 1. stream �� �̿��� sorted �迭 �ޱ� 
 * 2. �Ķ��Ʈ�� ��ġ�� �̿� (�̺� Ž��) �˰��� ���� 
 * 3. ������ �ڸ��� ������ �Ȱ��� (����)
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
		
		// stream ���� sorted �迭 �ޱ� 
		input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.sorted()
				.toArray();
		
		int start = 1;
		int end = input[N-1]; // ������ �ڸ��� �ִ� ������ 1~input[n-1] 
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
                // ���� Ž�� 
				end = mid-1;
			} else {			
				// total >= M 
                // ������ Ž�� 
				answer = mid; // (��� M ������ ����) 
				start = mid+1;
			}
		}
		System.out.println(answer);
	}	
}
