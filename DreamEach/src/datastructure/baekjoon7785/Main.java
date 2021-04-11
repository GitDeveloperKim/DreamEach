package datastructure.baekjoon7785;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static int n;
	public static HashSet<String> hs;
	public static ArrayList<String> answer;
	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		n = Integer.parseInt(in.nextLine());
		hs = new HashSet<>();
		answer = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			String temp = in.nextLine();
			String [] str = temp.split("\\s");
			
			if (str[1].equals("enter") || str[1]=="enter") {
				hs.add(str[0]);
			}
			if (str[1].equals("leave") || str[1]=="leave") {
				hs.remove(str[0]);
			}
		}
		for (String temp : hs) {
			answer.add(temp);
		}
		Collections.sort(answer, Collections.reverseOrder());
		
		for (String temp: answer) {
			System.out.println(temp);
		}
	}

}
