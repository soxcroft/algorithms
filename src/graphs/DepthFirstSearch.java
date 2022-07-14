/**
 * DFS runs in O(V+E).
 * After searching from a source s, we can check if another vertex v is
 * conntected to s in constant time, and find the path in time proportional to
 * its length (by maintaining edgeTo array).
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintStream;

public class DepthFirstSearch {

	public static void dfs(List<Integer>[] graph, int n, int[] edgeTo, boolean[] visited) {
		for (int neighbour : graph[n]) {
			if (!visited[neighbour]) {
				visited[neighbour] = true;
				edgeTo[neighbour] = n;
				dfs(graph, neighbour, edgeTo, visited);
			}
		}
	}

	public static void main(String[] args) {
		// init variables
		Scanner in = new Scanner(System.in);
		PrintStream out = new PrintStream(System.out);

		// scan graph from standard in
		int v = in.nextInt();
		int e = in.nextInt();
		List<Integer>[] graph = Graph.scanUndirectedGraph(in, v, e);

		out.println("GRAPH");
		Graph.printGraph(out, graph, v);

		// dfs
		int[] edgeTo = new int[v];
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(graph, i, edgeTo, visited);
			}
		}

		out.println("EDGE TO");
		for (int i = 0; i < v; i++) {
			out.println(edgeTo[i] + " -> " + i);
		}

		in.close();
	}
}
