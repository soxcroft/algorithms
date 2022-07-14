/**
 * Kosaraju-Sharir algorithm
 * If vertices v and w are strongly connected, then there is a path from v to w
 * AND a path from w to v (in a directed graph).
 * Based on idea that strong components in a graph G are the same as those in
 * the reverse graph.
 * Algorithm:
 *   Compute reverse postorder of G^R
 *   Run DFS in G, visiting vertices in order found in previous step
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintStream;

public class StrongComponents {

	public static List<Integer>[] reverse(List<Integer>[] graph, int v, int e) {
		List<Integer>[] rev = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			rev[i] = new LinkedList<>();
		}
		for (int i = 0; i < v; i++) {
			for (int n : graph[i]) {
				rev[n] = i;
			}
		}
		return rev;
	}

	public static void main(String[] args) {
		// init variables
		Scanner in = new Scanner(System.in);
		PrintStream out = new PrintStream(System.out);

		// scan graph from standard in
		int v = in.nextInt();
		int e = in.nextInt();
		List<Integer>[] graph = Graph.scanUndirectedGraph(in, v, e);

		// reverse graph
		List<Integer>[] rev = reverse(graph, v, e);

		// get topological order
		List<Integer> postorder = new LinkedList<>();
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(rev, i, postorder, visited);
			}
		}
		List<Integer> topological = new LinkedList<>();
		for (int v : postorder) {
			topological.add(v);
		}

		in.close();
	}
}
