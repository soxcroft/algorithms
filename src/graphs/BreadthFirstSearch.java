
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.PrintStream;

public class BreadthFirstSearch {

	public static void bfs(List<Integer>[] graph, int s, int[] edgeTo,
			int[] distTo, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		visited[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int neighbour : graph[v]) {
				if (!visited[neighbour]) {
					visited[neighbour] = true;
					q.add(neighbour);
					edgeTo[neighbour] = v;
					distTo[neighbour] = distTo[v] + 1;
				}
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

		// bfs
		int[] edgeTo = new int[v];
		int[] distTo = new int[v];
		boolean[] visited = new boolean[v];
		bfs(graph, 0, edgeTo, distTo, visited);

		out.println("EDGE TO");
		for (int i = 0; i < v; i++) {
			out.println(edgeTo[i] + " -> " + i);
		}
		out.println("DIST TO");
		for (int i = 0; i < v; i++) {
			out.println(i + ": " + distTo[i]);
		}

		in.close();
	}

}
