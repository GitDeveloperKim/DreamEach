package topologysort.baekjoon1766;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
https://www.acmicpc.net/problem/1766
N개의 문제는 모두 풀어야 한다.
먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
가능하면 쉬운 문제부터 풀어야 한다.
 */
public class Main {
	public static int N;
	public static int M;
	public static int [] indegree;
	public static ArrayList <ArrayList<Integer>> graph;
	public static PriorityQueue <Integer> q;
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		N = in.nextInt();
		M = in.nextInt();
		indegree = new int[N+1];
		graph = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			int []temp = new int[2];
			temp[0] = in.nextInt();
			temp[1] = in.nextInt();
			graph.get(temp[0]).add(temp[1]);
			indegree[temp[1]]++;
		}
		
		q = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()){
			int temp = q.poll();
			System.out.print(temp + " ");
			for (int i = 0; i < graph.get(temp).size(); i++) {
				if (--indegree[graph.get(temp).get(i)] == 0) {
					q.add(graph.get(temp).get(i));
				}
			}
		}
	}
}
