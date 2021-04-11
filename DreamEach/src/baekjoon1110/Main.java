package baekjoon1110;

import java.util.Scanner;

public class Main {
	public static int N;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		N = in.nextInt();
		int temp = N;
		int count = 0;
		do {
			count++;
			temp = (temp%10)*10+((temp%10)+(temp/10))%10;
		}while (N != temp);
		System.out.println(count);
	}

}
