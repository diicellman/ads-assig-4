import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<T> extends Search<T> {
    private final Map<T, Double> distances;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        super(source);
        distances = new HashMap<>();

        dijkstra(graph);
    }

    public double distanceTo(T destination) {
        Double distance = distances.get(destination);
        if (distance == null) {
            return Double.POSITIVE_INFINITY;
        }

        return distance;
    }

    private void dijkstra(WeightedGraph<T> graph) {
        if (!graph.hasVertex(source)) {
            return;
        }

        Set<T> unsettledNodes = new LinkedHashSet<>();
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            T currentNode = getVertexWithMinimumDistance(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (T neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = distanceTo(currentNode) + graph.getWeight(currentNode, neighbor);

                if (distanceTo(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private T getVertexWithMinimumDistance(Set<T> vertices) {
        T minimum = null;
        for (T vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            } else if (distanceTo(vertex) < distanceTo(minimum)) {
                minimum = vertex;
            }
        }

        return minimum;
    }
}
