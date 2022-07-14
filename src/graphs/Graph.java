/**
 * Just some utility methods for scanning and printing a graph. Does not let you
 * instantiate a graph object or anything like that.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintStream;

public class Graph {

	public static List<Integer>[] scanUndirectedGraph(Scanner in, int v, int e) {
		List<Integer>[] graph = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < e; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			graph[from].add(to);
			graph[to].add(from);
		}
		return graph;
	}

	public static List<Integer>[] scanDirectedGraph(Scanner in, int v, int e) {
		List<Integer>[] graph = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < e; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			graph[from].add(to);
		}
		return graph;
	}

	public static List<Integer[]>[] scanWeightedDirectedGraph(Scanner in, int v,
			int e) {
		List<Integer[]>[] graph = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < e; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int weight = in.nextInt();
			graph[from].add(new Integer[] {to, weight});
		}
		return graph;
	}

	public static void printGraph(PrintStream out, List<Integer>[] graph,
			int v) {
		for (int i = 0; i < v; i++) {
			out.print(i + ": ");
			for (int n : graph[i]) {
				out.print(n + " ");
			}
			out.println();
		}
	}

	public static void printWeightedGraph(PrintStream out, List<Integer[]>[] graph,
			int v) {
		for (int i = 0; i < v; i++) {
			out.print(i + ": ");
			for (Integer[] e : graph[i]) {
				out.print("(" + e[0] + "," + e[1] + ")");
			}
			out.println();
		}
	}
}
