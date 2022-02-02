package edu.psu.ist311.asg5.disjset;

/**
 * An interface for a disjoint set data structure.
 *
 * @param <T> The type of elements being related
 */
public interface IDisjointSets<T> {

    /**
     * Creates a singleton set containing data element {@code e}.
     *
     * <pre>
     *     <b>ensures</b> singleton set created containing the supplied data
     *                 element {@code e}.</pre>
     */
    T singleton(T e);

    /**
     * Returns the representative element for whichever set {@code e} happens to
     * inhabit.
     * <pre>
     *     <b>ensures</b> find = the represent element for whichever set
     *     {@code e} happens to inhabit.</pre>
     *
     * @throws IllegalArgumentException if the element {@code e} is not present
     *                                  in the disjoint set forest
     */
    T find(T e);

    /**
     * Given two elements {@code e1} and {@code e2}, unions together the sets
     * containing those elements.
     *
     * @throws IllegalArgumentException If either element does not exist
     *                                  (or either is {@code null}).
     */
    void union(T e1, T e2);

    /**
     * Empties out (clears) this disjoint set forest
     */
    void clear();
}