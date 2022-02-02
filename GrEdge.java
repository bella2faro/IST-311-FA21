package edu.psu.ist311.asg5.graph;

public class GrEdge<V, E> {

    private final V u, v;
    private final E edgeLab;
    private final boolean directed;

    public GrEdge(V end1, V end2, E lab, boolean directed) {
        this.u = end1;
        this.v = end2;
        this.edgeLab = lab;
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }

    public E edgeLabel() {
        return edgeLab;
    }

    // if the graph is undirected then source can be considered 'first', while
    // dest can be considered 'second' (or destination vertex)
    public V source() {
        return u;
    }

    public V dest() {
        return v;
    }

    @Override
    public int hashCode() {
        //return 42 is all else fails
        if(directed){
            //return something (a distinct number)involving ONLY u and v
            return (this.u.hashCode() - this.v.hashCode()) *31;
        }
        else{
            //return something else (a distinct number) involving only u and v
            return (this.v.hashCode() + this.u.hashCode()) * 31;
        }
//        return 0; // try not to do this
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GrEdge<?, ?>) {
            if (directed) {
                return this.u.equals(((GrEdge<?, ?>) obj).u)
                        && this.v.equals(((GrEdge<?, ?>) obj).v);
            } else {
                return (this.u.equals(((GrEdge<?, ?>) obj).v)
                        && this.v.equals(((GrEdge<?, ?>) obj).u))
                        || (this.u.equals(((GrEdge<?, ?>) obj).u)
                        && this.v.equals(((GrEdge<?, ?>) obj).v));
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return directed ?
                "(" + u + ", " + v + ")" :
                "{" + u + ", " + v + "}";
    }
}
