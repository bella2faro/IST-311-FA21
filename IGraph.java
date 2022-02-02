package edu.psu.ist311.asg5.graph;

import java.util.Set;

/**
 * A general interface for a graph object. Note that this interface encompasses
 * both directed and undirected graphs and is designed to support the
 * implementation of both matrix and adjacency list based graph representations.
 * <p>
 * Conceptually speaking, we can model this an ordered pair, G, containing
 * <ul>
 *     <li>a set of vertices, Vert</li>
 *     <li>a set of edges, E</li>
 * </ul>
 * When the graph initializes, its vertex and edge sets are both empty.
 *
 * @param <V> The type of vertices contained in this graph (in some sense this
 *            type represents the mathematical set, Vert, mentioned above).
 * @param <E> The type of edge-labels in this graph
 *            (in some sense this represents the set E)
 */
public interface IGraph<V, E> {

    /**
     * Adds a vertex to {@code this} graph's vertex set V. The vertex added
     * must be non {@code null}. Does nothing if {@code vertex} is already
     * present.
     * <p>
     * <b>ensures</b>this = (V union {addVertex}, E)
     *
     * @throws IllegalArgumentException if vertex is {@code null} or the graph
     *                                  (depending on implementation) is full
     */
    V addVertex(V vertex);

    /**
     * Adds an edge to {@code this} graph and returns the added edge. In
     * directed graphs, assume {@code e1} is the source, and {@code e2} is
     * the destination.
     * <p>
     * <b>ensures</b> this = (V, E union addEdge)
     *
     * @throws IllegalArgumentException if {@code e1} or {@code e2} is {@code null}
     *                                  or either isn't in the graph
     */
    GrEdge<V, E> addEdge(V e1, V e2, E label);

    /**
     * A variation of {@link #addEdge(V, V, E)} where the label {@code E} on
     * the edge added is assumed to be {@code null}.
     */
    GrEdge<V, E> addEdge(V e1, V e2);

    /**
     * Remove and return any edge that exists between vertices labeled
     * {@code e1} and {@code e2}. For directed edges, consider {@code e1} to be
     * the source.
     * <p>
     * <b>requires</b> (e1 in V) and (e2 in V) <p>
     * <b>ensures</b> this = (V, E - removeEdge)
     *
     * @throws IllegalArgumentException if either {@code e1}, {@code e2} is
     * {@code null} or don't exist in this graph.
     */
    GrEdge<V, E> removeEdge(V e1, V e2);

    /**
     * Returns {@code true} <b>if and only if</b> this graph's edges are
     * directed; {@code false otherwise};
     */
    boolean isDirected();

    /**
     * Returns the vertex set for this graph.
     * <p>
     * Callers of this method cannot rely on a deterministic ordering of the
     * vertices in the set returned (it's dependent on the implementation).
     * <p>
     * <b>ensures</b> vertexSet = V
     */
    Set<V> vertexSet();

    /**
     * Returns the edge set for this graph. Note that this set is kept current
     * as the graph is added to or deleted from.
     * <p>
     * Callers of this method cannot rely on a deterministic ordering of the
     * edges in the set returned (it's dependent on the implementation).
     * <p>
     * <b>ensures</b> edgeSet = E
     */
    Set<GrEdge<V, E>> edgeSet();

    /**
     * Returns the set of neighbors of this vertex {@code v}. So if the graph is
     * directed, and {@code v} has three neighbor vertices
     * (labeled say, a, b, and c), then this method should return the set
     * containing a, b, and c.
     * <p>
     * If the graph is directed, then the neighbor set should only contain the
     * vertices led to by {@code v}s outgoing edges.
     * <p>
     * <b>ensures</b> neighbors = {v such that forall u, (u, v) in E}
     * (in English: the neighbors set is equal to the set of all vertices v
     *              such that forall source vertices u, the directed edge (u, v)
     *              is in the edge set E)
     *
     * @throws IllegalArgumentException if the vertex passed is {@code null}
     *                                  or doesn't exist in the graph.
     */
    Set<V> neighbors(V v);

    /**
     * Returns the number of edges in the graph.
     * <p>
     * <b>ensures</b> edgeCount = |E| (the cardinality of the edge set)
     */
    int edgeCount();

    /**
     * Returns the number of vertices in the graph.
     * <p>
     * <b>ensures</b> vertexCount = |V| (the cardinality of the vertex set)
     */
    int vertexCount();

    /**
     * Returns the out degree of the specified vertex. Where out degree is the
     * number of edges point out of {@code vertex}.
     * <p>
     * In the case of <em>undirected</em> graphs this method simply returns the
     * number of edges touching the vertex. More formally, in the undirected
     * case, in-deg(v) == out-deg(v) forall vertices v in G.
     *
     * @param vertex the vertex we want to calculate the out degree of
     * @throws IllegalArgumentException if vertex is not found in
     *                                  the graph or is {@code null}.
     */
    int outDegree(V vertex);

    /**
     * Returns the in degree of the specified vertex. Where in degree is the
     * number of edges currently pointing to {@code vertex}.
     * <p>
     * In the case of <em>undirected</em> graphs this method simply returns the
     * number of edges touching the vertex.
     *
     * @param vertex the vertex we want to calculate the in degree of
     * @throws IllegalArgumentException if vertex is not found in
     *                                  the graph or is {@code null}.
     */
    int inDegree(V vertex);

    /**
     * Marks a given vertex {@code v}.
     *
     * @throws IllegalArgumentException if the vertex v passed is {@code null}
     *                                  or not present in the graph
     */
    void mark(V v);

    /**
     * Returns {@code true} <b>if and only if</b> vertex {@code v} is marked;
     * {@code false} otherwise.
     *
     * @throws IllegalArgumentException if the vertex v passed is {@code null}
     *                                  or not present in the graph
     */
    boolean isMarked(V v);

    /**
     * Goes through the current graph and sets the "marked" flags on all
     * vertices to {@code false}.
     * <p>
     * In other words, after calling this, nothing in the graph should be
     * considered "marked"/"walked".
     */
    void clearVertexMarks();
}