package edu.psu.ist311.asg5.graph.vertex;

public class GrVertex<T> {

    protected final T data;

    /**
     * Backing field that tracks whether this particular vertex has been
     * marked yet ("marked" here just means the vertex was walked over in a
     * traversal, search, etc.)
     */
    protected boolean marked;   // DON'T FACTOR THIS INTO EQUALS CHECK

    public GrVertex(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Vertex data cannot be null");
        }
        this.data = data;
    }

    /**
     * Returns the generic vertex data
     */
    public T vtxData() {
        return data;
    }

    public void mark() {
        this.marked = true;
    }

    public boolean isMarked() {
        return marked;
    }

    /**
     * Clears the state associated with this vertex by resetting the marked
     * flag to {@code false}.
     */
    public void clear() {
        this.marked = false;
    }

    @Override
    public int hashCode() {
        return this.data.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GrVertex<?>) {
            return this.data.equals(((GrVertex<?>) obj).data);
        }
        return false;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
