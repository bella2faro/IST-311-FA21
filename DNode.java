package edu.psu.ist311.asg5.disjset;

public final class DNode<T> {

    public final T data;
    public DNode<T> parent;
    public int rank;

    public DNode(T data, DNode<T> parent) {
        this.data = data;
        //make this node point to itself if its parent is null
        this.parent = parent == null ? this : parent;
        this.rank = 0;
    }

    public DNode(T data) {
        this(data, null);
    }

    @Override
    public int hashCode() {
        //rank here isn't a significant field for hashcode..
        // (note how it's not used to determine equality in equals(..))
        return 31 * data.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = o instanceof DNode<?>;
        if (result) {
            result = ((DNode<?>) o).data.equals(data);
        }
        return result;
    }

    @Override
    public String toString() {
        return data + ":" + rank;
    }
}
