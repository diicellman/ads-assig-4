import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Search<T> {
    protected final Set<T> marked;
    protected final Map<T, T> edgeTo;
    protected final T source;

    public Search(T source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(T destination) {
        return marked.contains(destination);
    }

    public Iterable<T> pathTo(T destination) {
        if (!hasPathTo(destination)) {
            return Collections.emptyList();
        }

        LinkedList<T> path = new LinkedList<>();
        for (T current = destination; !Objects.equals(current, source); current = edgeTo.get(current)) {
            if (current == null) {
                return Collections.emptyList();
            }

            path.push(current);
        }

        path.push(source);

        return path;
    }
}
