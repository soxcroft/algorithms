/**
 * Lazy because it would be better to use an indexed priority queue. We make E
 * insertions and deletions currently, and 0 decrease-key calls. An indexed
 * priority queue would allows us to make V insertions and deletions, and E
 * decrease-key calls.
 * 
 * Binary heap for PQ gives O(E*log(E)) or O((E+V)*log(E) runtime complexity I
 * think. This would be O((E+V)*log(V)) with an indexed priority queue.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.PrintStream;

public class LazyDjikstra {

	public static void djikstra(List<Integer[]>[] graph, int[] distTo, int[] edgeTo,
			int s) {
		// define comparator with lambda operator to create min heap
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((Integer[] i1, Integer[] i2) ->
				i1[1] - i2[1]);

		// initialize dist to
		for (int i = 0; i < graph.length; i++) {
			distTo[i] = Integer.MAX_VALUE;
		}
		
		// djikstra
		distTo[s] = 0;
		pq.add(new Integer[] {s, 0});
		while (!pq.isEmpty()) {
			int v = pq.poll()[0];
			for (Integer[] e : graph[v]) { // iterate over edges from v
				if (distTo[e[0]] > distTo[v] + e[1]) {
					distTo[e[0]] = distTo[v] + e[1];
					edgeTo[e[0]] = v;
					// if pq contains ... decrease key...
					pq.add(new Integer[] {e[0], distTo[e[0]]});
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
		List<Integer[]>[] graph = Graph.scanWeightedDirectedGraph(in, v, e);

		// find single source shortest paths from vertex 0
		int[] distTo = new int[v];
		int[] edgeTo = new int[v];
		djikstra(graph, distTo, edgeTo, 0);

		out.println("v\tdist\tedge");
		for (int i = 0; i < v; i++) {
			out.println(i + "\t" + distTo[i] + "\t" + edgeTo[i]);
		}

		in.close();
	}
}
