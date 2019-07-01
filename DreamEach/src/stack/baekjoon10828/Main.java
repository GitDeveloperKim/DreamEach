package stack.baekjoon10828;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static int N;
	public static Stack<Integer> stack;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		stack = new Stack<>();
		
		N = in.nextInt();
		in.nextLine();	//nextInt 이후에 엔터코드가 남아있어서 없애주어야한다.
		int i = 0;
		while (i<N) {
			String str = in.nextLine();
			
			switch (str) {
			case "pop" :
				if (stack.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(stack.pop());
				}
				break;
			case "size" :
				System.out.println(stack.size());
				break;
			case "empty" :
				if (stack.isEmpty()) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
				break;
			case "top" :
				if (stack.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(stack.peek());
				}
				break;
			default :
				String [] temp = str.split("\\s");
				int x = Integer.parseInt(temp[1]);
				stack.push(x);
				break;
			}
			i++;
		}
		
	}

}
