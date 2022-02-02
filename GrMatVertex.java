package edu.psu.ist311.asg5.graph.vertex;

public class GrMatVertex<V> extends GrVertex<V> {

    private final int index;

    public GrMatVertex(V data, int index) {
        super(data);
        this.index = index;
    }

    public int index() {
        return index;
    }
}
