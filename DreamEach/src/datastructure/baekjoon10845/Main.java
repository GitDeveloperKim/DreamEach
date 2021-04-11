package datastructure.baekjoon10845;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int N;
	static Queue<Integer> q;
	public static int back;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		q = new LinkedList<>();
		N = Integer.parseInt(in.nextLine());
		
		while (N-- > 0) {
			String str = in.nextLine();
			switch (str) {
			case "pop" :
				if (q.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(q.poll());
				}
				break;
			case "size" :
				System.out.println(q.size());
				break;
			case "empty" :
				if (q.isEmpty()) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
				break;
			case "front" :
				if (q.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(q.peek());
				}
				
				break;
			case "back" :
				if (q.isEmpty()) {
					System.out.println("-1");
				} else {
					System.out.println(back);
				}
				break;
			default : 
				String [] temp = str.split("\\s");
				back = Integer.parseInt(temp[1]);
				q.add(back);
				break;
			}
		}
	}
}
