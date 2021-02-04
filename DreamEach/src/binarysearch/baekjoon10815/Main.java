package binarysearch.baekjoon10815;
import java.util.Arrays;
import java.util.Scanner;

// https://www.acmicpc.net/problem/10815

public class Main {

	public static int N;
	public static int M;
	public static int [] input;
	public static int [] matching;

	
	public static boolean binarySearch (int [] arr, int start, int end, int value) {
			
		boolean isDetect = false; 
		int center = (start+end)/2;
		if (value == arr[center]) {
			isDetect = true;
		} else {
			if (value < arr[center] && (center-1 >= 0)) {
				isDetect = binarySearch (arr,start, center-1,value);
			} else if (value > arr[center] && (center+1) <= end) {
				isDetect = binarySearch (arr,center+1, end,value);
			} 
		}
		return isDetect;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		
		Arrays.sort(input);
		
		M = in.nextInt();
		matching = new int[M];
		
		int value = 0;
		for (int i = 0; i<M; i++) {
			value = in.nextInt();
			matching[i] = 0;
			if (binarySearch(input, 0, input.length-1,value)) {
				matching[i] = 1;
			}
		}
		
		for (int i = 0; i < M; i++) {
			System.out.print(matching[i] + " ");
		}		
	}	
}
