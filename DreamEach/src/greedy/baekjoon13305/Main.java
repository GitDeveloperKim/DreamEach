package greedy.baekjoon13305;

import java.util.PriorityQueue;
import java.util.Scanner;

//https://www.acmicpc.net/problem/13305

public class Main {
	public static int N;
	public static long [] edge;	// 문제에서 1<= x <= 10
	public static long [] city;
	public static PriorityQueue<Long> pq;	// 우선순위 큐(힙)을 이용해서 항상 최소값으로 주유 
	
	public static void main(String[] args) {
		//declare 
		Scanner in = new Scanner (System.in);
		long answer = 0;
		pq = new PriorityQueue<>();
		
		// input 
		N = in.nextInt();
		edge = new long[N-1];
		for (int i = 0; i < N-1; i++) {
			edge[i] = in.nextInt();
		}
		
		city = new long [N];
		for (int i = 0; i < N; i++) {
			city[i] = in.nextInt();
		}
		
		// greedy 
		// 마지막 노드(도착지점)에서의 주유가격은 의미가 없다 
		for (int i = 0; i < N-1; i++) {
			pq.add(city[i]);
			long min = pq.peek();		// 현재 위치에서 주유할수 있는 최소값으로 주유 
			answer += (min * edge[i]);	// 주유가격과 거리를 곱하여 더한다. 
		}
		System.out.println(answer);
	}
}
