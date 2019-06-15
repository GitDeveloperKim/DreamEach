package baekjoon10430;

import java.util.Scanner;

public class Main {
	static int a;
	static int b;
	static int c;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		a = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		
		System.out.println((a+b)%c);
		System.out.println( ((a%c)+(b%c))%c );
		System.out.println((a*b)%c);
		System.out.println( ((a%c) * (b%c))%c );
	}
}
