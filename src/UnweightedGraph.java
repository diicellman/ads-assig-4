public class UnweightedGraph<T> extends WeightedGraph<T> {
    public UnweightedGraph() {
        this(true);
    }

    public UnweightedGraph(boolean undirected) {
        super(undirected);
    }

    public void addEdge(T source, T destination) {
        super.addEdge(source, destination, 1.0);
    }
}
