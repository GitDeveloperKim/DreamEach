package tree.baekjoon5639;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

// https://www.acmicpc.net/problem/5639
// 이진트리를 만드는것이 어렵다기 보다는 입력갯수가 주어지지 않을때 입력을 어떻게 하는가 고민한 문제 
public class Main {
	// 트리노드 
	public static class Node {
		int value;	// 
		Node left;	// 왼쪽 
		Node right;	// 오른쪽 
		
		Node (int value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner (System.in);
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String str = br.readLine();
		Node root = new Node (Integer.parseInt(str));
		
		while ((str=br.readLine()) != null && str.length() != 0) {
			Node child = new Node (Integer.parseInt(str));
			make_bst(root, child);
		}
		post_order(root);
		br.close();
	}
	
	public static void make_bst (Node from, Node to) {
		if (from.value > to.value) {
			// 값이 노드보다 작을 때 왼쪽 
			if (from.left != null) {
				make_bst(from.left, to);
			} else {
				from.left = to;
			}
		} else if (from.value < to.value) {
			// 값이 노드보다 클때 오른쪽 
			if (from.right != null) {
				make_bst (from.right, to);
			} else {
				from.right = to;
			}
		}
	}
	
	public static void post_order (Node n) {
		// 후위 순위 탐색 
		if (n.left != null) post_order (n.left);
		if (n.right != null) post_order (n.right);
		System.out.println(n.value);
	}

}
