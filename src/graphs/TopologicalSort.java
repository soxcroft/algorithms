/**
 * Run dfs and return vertices in reverse postorder.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.io.PrintStream;

public class TopologicalSort {

	public static void dfs(List<Integer>[] graph, int n, Stack<Integer> postorder,
			boolean[] visited) {
		for (int neighbour : graph[n]) {
			if (!visited[neighbour]) {
				visited[neighbour] = true;
				dfs(graph, neighbour, postorder, visited);
			}
		}
		postorder.add(n);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintStream out = new PrintStream(System.out);

		int v = in.nextInt();
		int e = in.nextInt();
		List<Integer>[] graph = Graph.scanDirectedGraph(in, v, e);

		out.println("GRAPH");
		Graph.printGraph(out, graph, v);

		// dfs to get postorder
		Stack<Integer> postorder = new Stack<>();
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(graph, i, postorder, visited);
			}
		}

		// reverse to get topological order
		Stack<Integer> topological = new Stack<>();
		while (!postorder.isEmpty()) {
			topological.add(postorder.pop());
		}

		out.println("TOPOLOGICAL ORDER");
		for (int n : topological) {
			out.print(n + " ");
		}
		out.println();

		in.close();
	}
}
