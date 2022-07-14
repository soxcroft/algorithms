/**
 * Preprocess graph to answer queries (in constant time):
 *   is v conntected to w?
 *   how many conntected components are there?
 *   what component is v in?
 *
 * (preprocess = run dfs from each node, like in DFS.java, but keep track of
 * which component we are searching)
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintStream;

public class ConnectedComponents {

	public static void dfs(List<Integer>[] graph, int n, int id, int[] components,
			boolean[] visited) {
		components[n] = id;
		for (int neighbour : graph[n]) {
			if (!visited[neighbour]) {
				visited[neighbour] = true;
				dfs(graph, neighbour, id, components, visited);
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
		int counter = 0;
		int[] components = new int[v];
		boolean[] visited = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(graph, i, counter, components, visited);
				counter++;
			}
		}

		out.println("COMPONENTS");
		for (int i = 0; i < v; i++) {
			out.println(i + ": " + components[i]);
		}

		in.close();
	}
}
