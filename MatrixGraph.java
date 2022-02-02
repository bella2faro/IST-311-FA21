package edu.psu.ist311.asg5.graph;

import edu.psu.ist311.asg5.graph.vertex.GrMatVertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A basic implementation of adjacency matrix based graphs.
 * <p>
 * Note that quite a bit of duplicated code in this implementation can be
 * cleaned up (and many things can even be moved into the {@link AbstractGraph}
 * class). Doing so would lessen the number of methods you need to implement
 * in the adjacency list implementation (as they would be shared among both).
 *
 * @param <V> Label type for vertices
 * @param <E> Label type for edges
 */
public class MatrixGraph<V, E> extends AbstractGraph<V, E> {

    private final Map<V, GrMatVertex<V>> vertices;

    /**
     * Here is the matrix where edge data is stored. Every edge appears on one
     * (directed) or two (undirected) locations within graph.
     */
    private final Object[][] table;

    private final int maxSize;
    private int currentSize = 0;

    /**
     * @param maxVertices The maximum number of vertices for this graph
     * @param directed    Whether or not the graph's edges are directed
     */
    public MatrixGraph(int maxVertices, boolean directed) {
        super(directed);
        this.maxSize = maxVertices;
        this.table = new Object[maxVertices][maxVertices];
        this.vertices = new HashMap<>(maxVertices);
    }

    @Override
    public V addVertex(V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("vertex cannot be null");
        }
        if (vertices.containsKey(vertex)) {
            return vertex;
        }
        if (currentSize >= maxSize) {
            throw new IllegalArgumentException(
                    "Graph (matrix) is already filled");
        }
        vertices.put(vertex, new GrMatVertex<>(vertex, currentSize));
        currentSize++;
        return vertex;
    }

    @Override
    public GrEdge<V, E> addEdge(V e1, V e2, E label) {
        sanityCheckEnds(e1, e2, vertices);

        GrMatVertex<V> vtx1 = vertices.get(e1);
        GrMatVertex<V> vtx2 = vertices.get(e2);

        //update our matrix with a new edge..
        GrEdge<V, E> edge = new GrEdge<>(vtx1.vtxData(), vtx2.vtxData(),
                label, directed);

        table[vtx1.index()][vtx2.index()] = edge;

        if (!directed) {
            table[vtx2.index()][vtx1.index()] = edge;
        }
        return edge;
    }

    @SuppressWarnings("unchecked")
    @Override
    public GrEdge<V, E> removeEdge(V e1, V e2) {
        sanityCheckEnds(e1, e2, vertices);

        int row = vertices.get(e1).index();
        int col = vertices.get(e2).index();

        GrEdge<V, E> e = (GrEdge<V, E>) table[row][col];

        //update the table to remove the edge e
        table[row][col] = null;
        if (!directed) {
            table[col][row] = null;
        }
        return e;
    }

    @Override
    public Set<V> vertexSet() {
        return vertices.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<GrEdge<V, E>> edgeSet() {

        Set<GrEdge<V, E>> result = new HashSet<>();

        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                if (table[i][j] != null) { //there's an edge here to retrieve
                    result.add((GrEdge<V, E>) table[i][j]);
                }
            }
        }
        return result;
    }

    @Override
    public Set<V> neighbors(V v) {
        sanityCheckVertex(v, vertices);

        Set<V> result = new HashSet<>();

        for (GrEdge<V, E> edg : this.edgeSet()) {

            if (directed) { // directed case
                if (edg.source().equals(v)) {
                    result.add(edg.dest());
                }
            } else { // undirected case
                if (edg.source().equals(v)) {
                    result.add(edg.dest());
                } else if (edg.dest().equals(v)) {
                    result.add(edg.source());
                }
            }
        }
        return result;
    }

    @Override
    public int vertexCount() {
        return vertices.keySet().size();
    }

    @Override
    public int outDegree(V vertex) {
        sanityCheckVertex(vertex, vertices);
        int count = 0;
        for (GrEdge<V, E> edge : this.edgeSet()) {
            if (edge.source().equals(vertex)) {
                count++;
            }
            else if (!directed && edge.dest().equals(vertex)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int inDegree(V vertex) {
        sanityCheckVertex(vertex, vertices);
        int count = 0;
        for (GrEdge<V, E> edge : this.edgeSet()) {
            if (edge.dest().equals(vertex)) {
                count++;
            }
            else if (!directed && edge.source().equals(vertex)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void mark(V v) {
        sanityCheckVertex(v, vertices);
        vertices.get(v).mark();
    }

    @Override
    public boolean isMarked(V v) {
        sanityCheckVertex(v, vertices);
        return vertices.get(v).isMarked();
    }

    @Override
    public void clearVertexMarks() {
        for (V vert : vertices.keySet()) {
            vertices.get(vert).clear();
        }
    }
}