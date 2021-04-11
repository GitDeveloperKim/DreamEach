package greedy.baekjoon2217;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static int N;
	public static ArrayList<Integer> arr;
	public static int answer = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int temp = in.nextInt();
			arr.add(temp);
		}

		Collections.sort(arr, Collections.reverseOrder());
		
		int count = 1;
		for (int i : arr) {
			int temp = i * count;
			if (temp > answer) {
				answer = temp;
			}
			count++;
		}
		System.out.println(answer);
	}
	
}