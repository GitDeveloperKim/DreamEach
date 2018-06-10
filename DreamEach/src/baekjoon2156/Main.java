package baekjoon2156;

/*
 * input 6 10 13 9 8 1
 * output 33 
 * 
 */
import java.util.Scanner;

public class Main {
	static int [] arr;
	static int [] mem;
	static int max_answer=0;
	static int n;
		
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		n = in.nextInt();                                                                                                                 
		arr = new int [n];
		mem = new int [n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		for (int i = 0; i < n; i++) {
			 int temp = dynamicWine(i);
			 if (temp > max_answer) {
				 max_answer = temp;
			 }
		}
		System.out.println(max_answer);
	}
	static int dynamicWine(int index) {
		int result = arr[index];
		int a=result;
		int c = result;
		int b = result;
		
		if (mem[index] == 0) {
			if ((index+1) < n) {		
				a += arr[index+1];
				int temp = 0;
				for (int i = index+3; i<n; i++) {
					 temp = Math.max(temp, dynamicWine(i));
				}
				a+= temp;				
			}			
			if ((index+2) <n) {
				b += dynamicWine(index+2);
			}
			result = Math.max(a, b);
			result = Math.max(result, c);
			mem[index] = result;
		} else {
			result = mem[index];
		}
		return result;
	}
}