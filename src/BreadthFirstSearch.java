import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch<T> extends Search<T> {
    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);

        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T start) {
        if (!graph.hasVertex(start)) {
            return;
        }

        marked.add(start);

        Queue<T> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            List<T> adjacentVertices = graph.adjacencyList(current);

            for (T vertex : adjacentVertices) {
                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    edgeTo.put(vertex, current);
                    queue.add(vertex);
                }
            }
        }
    }
}
