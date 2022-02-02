package edu.psu.ist311.megalist;

import java.util.NoSuchElementException;

/**
 * A <i>megalist</i> is a variant of a singly linked list that uses the notion
 * of a "handle" to specify insertion and deletion sites for various entries
 * stored in the list.
 * <p>
 * We conceptually model this structure as a tuple (ordered pair) of two
 * generic sequences (seq) of type T:
 * <pre>
 *  (left, right : seq of T)</pre>
 * Upon initialization, the sequences {@code this.left} and {@code this.right}
 * that make up this megalist are empty.
 * <p>
 * The <i>handle</i> part of the list can be thought of as sitting "in between"
 * {@code this.left} and {@code this.right}. Note that, as per the
 * initialization clause above, both sides (left and right) must be empty when
 * the object is initialized.
 *
 * @param <T> the type of the entries stored in this mega-list.
 */
public interface IMegaList<T> {

    /**
     * Adds {@code e} to the beginning of {@code this.right}.
     * <p>
     * <b>ensures:</b> {@code this.right} seq has entry {@code e} prepended onto
     * the front.
     */
    void addToRightAtFront(T e);

    /**
     * Removes the rightmost entry from the right seq.
     * <p>
     * <b>requires:</b> {@code this.right} must be non-empty seq.<p>
     * <b>ensures:</b> removes and returns the leftmost entry of the
     * {@code this.right} seq.
     *
     * @throws NoSuchElementException if one attempts to remove from an empty
     *                                right seq.
     */
    T removeFromRightAtFront();

    /**
     * This method may be called at any time.
     * <p>
     * <b>ensures:</b> first prepends all entries in the left seq to the right
     * seq, and then sets the left seq to be the empty seq.
     */
    void moveToVeryBeginning();

    /**
     * Advances the position of {@code this} metalist by one (i.e.: it advances
     * the position of the handle in this list).
     * <p>
     * <b>requires</b> {@code this.right} must be a non-empty empty seq.<p>
     * <b>ensures</b> the method removes the leftmost entry from the right seq.
     * {@code this.right} and appends it to the seq {this.left}.
     *
     * @throws IllegalStateException if one tries to move forward into an empty
     *                               right list (would put the megalist into an
     *                               illegal 'state')
     */
    void moveForward();

    /**
     * Returns the length of the left sequence. This method may be called at
     * any time.
     * <p>
     * <b>ensures:</b> the length of the {@code this.left} seq is returned.
     */
    int leftLength();

    /**
     * Returns the length of the right sequence. This method may be called at
     * any time.
     * <p>
     * <b>ensures:</b> the length of the {@code this.right} seq is returned.
     */
    int rightLength();

    /**
     * <b>ensures:</b> {@code this.left} and {@code this.right} become the empty
     * sequence.
     */
    void clear();


}
