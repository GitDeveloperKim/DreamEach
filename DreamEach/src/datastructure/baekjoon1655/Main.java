package datastructure.baekjoon1655;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;


// 백준 1655 : 가운데를 말해요 
// https://blog.naver.com/wockd9600/222303467377

public class Main {
	private static int N; // 1<= N <= 100_000
	private static PriorityQueue<Integer> pq_max;
	private static PriorityQueue<Integer> pq_min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		pq_max = new PriorityQueue<>((a,b)-> b-a);
		pq_min = new PriorityQueue<>();
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (count % 2 == 0) {
				pq_max.add(input);
			} else {
				pq_min.add(input);
			}
			
			count++;
			if (!pq_min.isEmpty() && !pq_max.isEmpty() ) {
				if (pq_max.peek() > pq_min.peek() ) {
					int a = pq_min.poll();
					int b = pq_max.poll();
					pq_min.add(b);
					pq_max.add(a);
				}
			}

			sb.append(pq_max.peek() + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
