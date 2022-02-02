package edu.psu.ist311.asg5.graph;

import edu.psu.ist311.asg5.graph.vertex.GrAdjListVertex;
import edu.psu.ist311.asg5.graph.vertex.GrVertex;

import java.util.*;

public class AdjListGraph<V, E> extends AbstractGraph<V, E> {
    private final Map<V, GrAdjListVertex<V, E>> neighbors = new HashMap<>();

    public AdjListGraph(boolean directed) {
        super(directed);
    }

    //done
    @Override
    public V addVertex(V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex is null");
        }
        if (neighbors.containsKey(vertex)) {
            return vertex;
        }
        // proceed with normal logic .....
        neighbors.put(vertex, new GrAdjListVertex<>(vertex));
        return vertex;
    }

    // done
    @Override
    public GrEdge<V, E> addEdge(V e1, V e2, E label) {
        sanityCheckEnds(e1, e2, neighbors);

        GrAdjListVertex<V, E> vtx1 = neighbors.get(e1);
        GrAdjListVertex<V, E> vtx2 = neighbors.get(e2);

        GrEdge<V, E> newEdge = new GrEdge<>(vtx1.vtxData(),
                vtx2.vtxData(), label, directed);
        vtx1.addEdge(newEdge);

        if (!directed) {
            vtx2.addEdge(new GrEdge<>(vtx2.vtxData(), vtx1.vtxData(),
                    label, false));
        }
        return newEdge;
    }

    // TODO: 12/5/21 CHECK HERE 
    @Override
    public GrEdge<V, E> removeEdge(V e1, V e2) {
        sanityCheckEnds(e1, e2, neighbors);

        GrAdjListVertex<V, E> vtx1 = neighbors.get(e1);
        GrAdjListVertex<V, E> vtx2 = neighbors.get(e2);
        GrEdge<V, E> removededge = new GrEdge<>(vtx1.vtxData(), vtx2.vtxData(), null, directed);
        vtx1.removeEdge(removededge);
        if (!directed) {
            vtx2.removeEdge(new GrEdge<>(vtx2.vtxData(), vtx1.vtxData(),
                    null, false));
        }
        return removededge;
    }

    // done
    @Override
    public Set<V> vertexSet() {
        return neighbors.keySet();
    }

    // done
    @Override
    public Set<GrEdge<V, E>> edgeSet() {
        Set<GrEdge<V, E>> result = new HashSet<>();
        for (GrAdjListVertex<V, E> x : neighbors.values()) {
            List<GrEdge<V, E>> edges = x.adjacentEdges(); // gives back a list of GrEdges
            result.addAll(edges);
        }
        return result;
    }

    // done
    @Override
    public Set<V> neighbors(V v) {
        sanityCheckVertex(v, neighbors);
        return neighbors.get(v).adjacentVerts();
    }

    // done
    @Override
    public int vertexCount() {
        return neighbors.keySet().size();
    }


    // TODO: 12/6/21 check result--
    @Override
    public int outDegree(V vertex) {
        sanityCheckVertex(vertex, neighbors);  // takes care of exception
        int result = 0;
        for (GrEdge<V, E> edge : edgeSet()) {
            if (directed) {
                if (edge.source().equals(vertex)) {
                    result++; // make sure this is right
                }
            } else {
                if (edge.source().equals(vertex) || edge.dest().equals(vertex)) {
                    result++; // make sure this is right
                }
            }
        }
        return result;
    }

    // done
    @Override
    public int inDegree(V vertex) {
        sanityCheckVertex(vertex, neighbors);  // takes care of exception
        int result = 0;
        for (GrEdge<V, E> edge : edgeSet()) {
            if (directed) {
                if (edge.dest().equals(vertex)) {
                    result++;
                }
            } else {
                // undirected case
                if (edge.dest().equals(vertex) || edge.source().equals(vertex)) {
                    result++;
                }

            }
        }
        return result;
    }

    // done
    @Override
    public void mark(V v) {
        sanityCheckVertex(v, neighbors); // takes care of exceptions
        neighbors.get(v).mark();

    }

    // done
    @Override
    public boolean isMarked(V v) {
        sanityCheckVertex(v, neighbors); // takes care of exceptions
        return neighbors.get(v).isMarked();
    }

    // TODO: 12/6/21 NOT COMPLETE
    @Override
    public void clearVertexMarks() {
        for (GrAdjListVertex<V, E> vertex : neighbors.values()) {
            vertex.clear();
        }
    }
}
