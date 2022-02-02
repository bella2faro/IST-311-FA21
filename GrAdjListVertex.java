package edu.psu.ist311.asg5.graph.vertex;

import edu.psu.ist311.asg5.graph.GrEdge;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class GrAdjListVertex<V, E> extends GrVertex<V> {
// m = neighbors (map)
    private final List<GrEdge<V, E>> adjacencies;

    public GrAdjListVertex(V v) {
        super(v);
        this.adjacencies = new LinkedList<>();
    }

    public void addEdge(GrEdge<V, E> edge) {
        // two edges mapping from the same source vertex from the same target
        // vertex not supported (i.e.: "multigraphs" not supported).
        if (!adjacencies.contains(edge)) {
            adjacencies.add(edge);
        }
    }

    public void removeEdge(GrEdge<V, E> edge) {
        adjacencies.remove(edge);
    }

    // all edges that are outgoing (if the graph is directed); both if the
    // graph is undirected
    public List<GrEdge<V, E>> adjacentEdges() {
        return adjacencies;
    }

    /**
     * returns set of adjacent vertices.
     */
    public Set<V> adjacentVerts() {
        Set<V> result = new LinkedHashSet<>(); // want deterministic vertex ordering
        for (GrEdge<V, E> adjacentEdge : adjacencies) {
            if (adjacentEdge.source().equals(this.data)) {
                result.add(adjacentEdge.dest());
            } else {
                // if the adjacent edge's first is not equal to this edge's u
                // vertex, that implies this adjacent edge's second is equal
                // to this edge's first (meaning it's a self loop)
                result.add(adjacentEdge.source());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return adjacencies.toString();
    }
}
