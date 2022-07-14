/**
 * Bellman ford can be used when there are edges with negative weights in the
 * graph to be processed.
 * Algorithm:
 *   repeat V times: (v-1?)
 *     relax all edges
 *
 * This works because after itaration i, we have found shortest paths containing
 * i or fewer edges. And a shortest path can't contain more than V edges.
 *
 * Runs in O(E*V), but if we remain a queue of vertices whose distances changed,
 * average runtime is proportional to V+E.
 *
 * We can also use Bellman Ford to detect negative cycles. If the distance to a
 * vertex changes in iteration V, there is a negative cycle and no SPT exists.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.PrintStream;

public class BellmanFord {

	public static void bellmanFord(List<Integer[]>[] graph, int[] distTo, int edgeTo,
			int s) {
		// initialize distances
		for (int i = 0; i < graph.length; i++) {
			distTo[i] = Integer.MAX_VALUE;
		}
		distTo[s] = 0;

		// bellman ford
		Queue<Integer> q = new LinkedList<>();
		// don't use a while loop because there could be a negative cycle
		for (int i = 0; i < graph.length && !q.isEmpty(); i++) {
			int v = q.poll()[0];
			for (Integer[] e : graph[v]) {
				// relax edge (from v)
				if (distTo[e[0]] > distTo[v] + e[1]) {
					distTo[e[0]] = distTo[v] + e[1];
					edgeTo(e[0]) = v;
					q.add(e[0]);
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
		List<Integer>[] graph = Graph.scanWeightedDirectedGraph(in, v, e);

		in.close();
	}
}
