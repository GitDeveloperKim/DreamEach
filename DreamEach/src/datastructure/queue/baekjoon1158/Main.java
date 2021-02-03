package datastructure.queue.baekjoon1158;

import java.util.Scanner;

//https://www.acmicpc.net/problem/1158

public class Main {
	static int [] arr;	// ���� ť
	static boolean [] checker; 
	static int N;	// N ���� ���
	static int K;	// K ���� 
	
	public static void main(String[] args) {
		// ������ ������� ���ŵǴ� ������ (N,K)- �似�� ���� 
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		K = in.nextInt();
		
		arr = new int [N+1];
		checker = new boolean [N+1];
			// false 
		
		int cur = K; // position 
		
		int index = 1;	// �ᱣ�� �Է� �ε��� 
		
		while (true) {
			arr[index] = cur;
			checker[cur]=true;
			
			if (index == N)
				break;
			
			int i = 1;
			int temp = cur;
			while (i != (K+1)) {
				temp = (cur+i)%(N+1);
				if (temp==0){
					cur++;
					continue;
				}
				if (checker[temp] == true) {
					cur++;
					continue;
				} else {
					i++;
				}				
			}	
			cur = temp;
			index++;
		}		
		
		System.out.print("<");
		for (int i = 1;i<N+1;i++) {
			System.out.print(arr[i]);
			if (i != N) {
				System.out.print(", ");
			}
		}
		System.out.print(">");
	}
}