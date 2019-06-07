package baekjoon2438;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		int row = in.nextInt();
		
		for (int i=0; i < row; i++) {
			for (int j=0; j < i+1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
