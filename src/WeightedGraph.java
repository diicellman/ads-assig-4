import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> vertices;

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        vertices = new LinkedHashMap<>();
    }

    public void addVertex(T data) {
        Objects.requireNonNull(data, "data");

        if (hasVertex(data)) {
            return;
        }

        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(T source, T destination, double weight) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(destination, "destination");

        if (weight < 0) {
            throw new IllegalArgumentException("Dijkstra requires non-negative weights");
        }

        if (source.equals(destination)) {
            return;
        }

        Vertex<T> sourceVertex = getOrCreateVertex(source);
        Vertex<T> destinationVertex = getOrCreateVertex(destination);

        if (sourceVertex.hasAdjacentVertex(destinationVertex)) {
            return;
        }

        sourceVertex.addAdjacentVertex(destinationVertex, weight);
        if (undirected) {
            destinationVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<T> vertex : vertices.values()) {
            count += vertex.getAdjacentVertices().size();
        }

        if (undirected) {
            count /= 2;
        }

        return count;
    }

    public boolean hasVertex(T data) {
        return vertices.containsKey(data);
    }

    public boolean hasEdge(T source, T destination) {
        if (!hasVertex(source)) {
            return false;
        }

        if (!hasVertex(destination)) {
            return false;
        }

        Vertex<T> sourceVertex = vertices.get(source);
        Vertex<T> destinationVertex = vertices.get(destination);
        return sourceVertex.hasAdjacentVertex(destinationVertex);
    }

    public List<T> adjacencyList(T data) {
        if (!hasVertex(data)) {
            return null;
        }

        List<T> adjacentData = new ArrayList<>();
        Vertex<T> vertex = vertices.get(data);
        for (Vertex<T> adjacentVertex : vertex.getAdjacentVertices().keySet()) {
            adjacentData.add(adjacentVertex.getData());
        }

        return adjacentData;
    }

    public double getWeight(T source, T destination) {
        if (!hasEdge(source, destination)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        Vertex<T> sourceVertex = vertices.get(source);
        Vertex<T> destinationVertex = vertices.get(destination);
        return sourceVertex.getWeightTo(destinationVertex);
    }

    private Vertex<T> getOrCreateVertex(T data) {
        if (!hasVertex(data)) {
            addVertex(data);
        }

        return vertices.get(data);
    }
}
