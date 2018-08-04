package baekjoon2851;
import java.util.Scanner;

public class Main {

	static int [] arr;
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);				
		int a = 0;		
		arr = new int[10];
		int temp = Integer.MAX_VALUE;		
		for (int i = 0; i < 10; i++){
			arr[i] = in.nextInt();
			a += arr[i];
			if (Math.abs(100-a) <= Math.abs(100-temp)) {
				temp = a;
			}
		}	
		System.out.println(temp);
	}
}
