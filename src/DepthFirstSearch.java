import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch<T> extends Search<T> {
    public DepthFirstSearch(UnweightedGraph<T> graph, T source) {
        super(source);

        dfs(graph, source);
    }

    private void dfs(UnweightedGraph<T> graph, T start) {
        if (!graph.hasVertex(start)) {
            return;
        }

        marked.add(start);

        Deque<T> stack = new ArrayDeque<>();
        Map<T, Integer> nextIndex = new HashMap<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            T current = stack.peek();
            List<T> adjacentVertices = graph.adjacencyList(current);
            int index = nextIndex.getOrDefault(current, 0);

            if (index < adjacentVertices.size()) {
                T vertex = adjacentVertices.get(index);
                nextIndex.put(current, index + 1);

                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    edgeTo.put(vertex, current);
                    stack.push(vertex);
                }
            } else {
                stack.pop();
            }
        }
    }
}
