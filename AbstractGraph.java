package edu.psu.ist311.asg5.graph;

import edu.psu.ist311.asg5.graph.vertex.GrVertex;

import java.util.Map;
import java.util.Set;

public abstract class AbstractGraph<V, E> implements IGraph<V, E> {

    protected final boolean directed;

    public AbstractGraph(boolean directed) {
        this.directed = directed;
    }


    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public GrEdge<V, E> addEdge(V e1, V e2) {
        return addEdge(e1, e2, null);
    }

    public abstract Set<GrEdge<V, E>> edgeSet();

    @Override
    public int edgeCount() {
        return this.edgeSet().size();
    }

    /**
     * A helper method that throws the appropriate exception if the provided
     * vertex {@code v} is either {@code null} or not present in the graph (as
     * determined by looking it up in the provided {@code vertices} map).
     *
     * @param v        The vertex to sanity check.
     * @param vertices a mapping from vertex labels to their internal graph
     *                 vertex nodes.
     */
    protected final void sanityCheckVertex(
            V v, Map<V, ? extends GrVertex<V>> vertices) {
        if (v == null) {
            throw new IllegalArgumentException(
                    "Requires violation: vertex is null");
        }
        if (vertices.get(v) == null) {
            throw new IllegalArgumentException("Requires violation: " +
                    "vertex " + v + " is must be in the graph");
        }
    }

    /**
     * A helper method that throws the appropriate exception if either vertex
     * passed ({@code v1} or {@code v2}) is {@code null} or doesn't exist in the
     * provided {@code} vertices map).
     *
     * @param v1       The first vertex to sanity check.
     * @param v2       The second vertex to sanity check.
     * @param vertices a mapping from vertex labels to their internal graph
     *                 vertex nodes.
     */
    protected final void sanityCheckEnds(
            V v1, V v2, Map<V, ? extends GrVertex<V>> vertices) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException(
                    "Requires violation: vertex 1 or vertex 2 is null");
        }
        if (vertices.get(v1) == null || vertices.get(v2) == null) {
            throw new IllegalArgumentException(
                    "Requires violation: both vertices must exist in the graph");
        }
    }
}
