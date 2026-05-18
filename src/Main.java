public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);
        fillWithWeights(graph);

        System.out.println("Dijkstra:");
        Search<String> dijkstra = new DijkstraSearch<>(graph, "Almaty");
        outputPath(dijkstra, "Kyzylorda");

        System.out.println("--------------------------------");

        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, "Almaty");
        outputPath(bfs, "Kyzylorda");
    }

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void outputPath(Search<String> search, String key) {
        if (!search.hasPathTo(key)) {
            System.out.println("No path");
            return;
        }

        boolean first = true;
        for (String vertex : search.pathTo(key)) {
            if (!first) {
                System.out.print(" -> ");
            }

            System.out.print(vertex);
            first = false;
        }

        System.out.println();
    }
}
