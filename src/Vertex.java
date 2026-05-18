import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Vertex<T> {
    private final T data;
    private final Map<Vertex<T>, Double> adjacentVertices;

    public Vertex(T data) {
        this.data = Objects.requireNonNull(data, "data");
        adjacentVertices = new LinkedHashMap<>();
    }

    public T getData() {
        return data;
    }

    public Map<Vertex<T>, Double> getAdjacentVertices() {
        return Collections.unmodifiableMap(adjacentVertices);
    }

    public void addAdjacentVertex(Vertex<T> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public boolean hasAdjacentVertex(Vertex<T> destination) {
        return adjacentVertices.containsKey(destination);
    }

    public double getWeightTo(Vertex<T> destination) {
        Double weight = adjacentVertices.get(destination);
        if (weight == null) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        return weight;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        Vertex<?> vertex = (Vertex<?>) other;
        return data.equals(vertex.data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
