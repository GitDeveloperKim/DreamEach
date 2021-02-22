package sweeping.baekjoon2170;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int [][] arr;
	public static int answer = 0;
		
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader (new InputStreamReader(System.in))) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			int left = 0;
			int right = 0;
			
			arr = new int [N][2];
			
			for (int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, (int []a, int []b)->a[0] - b[0]);
									
			left = arr[0][0];
			right = arr[0][1];
			
			for (int i = 1; i < N; i++) {
				if (right < arr[i][0]) {
					answer += (right-left);
					left = arr[i][0];
					right = arr[i][1];
				} else {
					if (right < arr[i][1]) {
						right = arr[i][1];
					}
				}
			}
			answer += (right - left);
			
			System.out.println(answer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
