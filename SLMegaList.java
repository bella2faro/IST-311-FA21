package edu.psu.ist311.megalist;

import edu.psu.ist311.megalist.IMegaList;

import java.util.NoSuchElementException;

public class SLMegaList<E> implements IMegaList<E> {
    @Override
    public void addToRightAtFront(E e) {
        MNode m = new MNode(lastInLeft.next, e);
        lastInLeft.next = m;
        if (lenRight == 0) {
            last = m;
        }
        lenRight++;
    }

    @Override
    public String toString() {
        // String res = "<";
        StringBuilder sb = new StringBuilder("<");
        MNode curr = preFirst.next;

        boolean first = true;
        while (curr != lastInLeft.next) {
            if (first) {
                sb.append(curr.data);
                first = false;
            } else {
                sb.append(", ").append(curr.data);
            }
            curr = curr.next;
        }
        sb.append(">" + "<");

        first = true;
        while (curr != null) {
            if (first) {
                sb.append(curr.data);
                first = false;
            } else {
                sb.append(", ").append(curr.data);
            }
            curr = curr.next;
        }
        sb.append(">");
        return sb.toString();
    }


    @Override
    public E removeFromRightAtFront() {
        if (lenRight == 0) {
            throw new NoSuchElementException("Right List Empty! :(");
        }
        E res = lastInLeft.next.data; // create variable type E
        MNode newFrontRight = lastInLeft.next.next;
        lastInLeft.next = newFrontRight;
        if (lenRight == 1) {
            last = lastInLeft;
        }
        lenRight--;
        return res;
    }

    // ASK
    @Override
    public void moveToVeryBeginning() {
        lastInLeft = preFirst;
        lenRight = lenRight + lenLeft;
        lenLeft = 0;
    }

    @Override
    public void moveForward() {
        if (lenRight == 0) {
            throw new IllegalStateException("illegal state exception!");
        }
        MNode newRightSeq = lastInLeft.next;
        lastInLeft = lastInLeft.next;
        if (newRightSeq != null) {
            last = lastInLeft;
            lenRight--;
        }
        lenLeft++;
    }

    @Override
    public void clear() {
        this.preFirst = new MNode(null, null);  // from github 90-92 (Is is correct??)
        this.last = preFirst;
        this.lastInLeft = preFirst;

        lenRight = 0;
        lenLeft = 0;
    }

    @Override
    public int leftLength() {  // getter methods
        return lenLeft;
    }

    @Override
    public int rightLength() {
        return lenRight;
    }

    private int lenLeft;
    private int lenRight;
    private MNode lastInLeft;
    private MNode last;
    private MNode preFirst;

    public SLMegaList() {
        this.preFirst = new MNode(null, null);
        this.last = preFirst;
        this.lastInLeft = preFirst;
    }

    private final class MNode {
        E data;  // generic payload stored here
        MNode next; // pointer to next node or null

        public MNode(MNode next, E data) {
            this.next = next;
            this.data = data;
        }

        @Override
        public String toString() {
            return data == null ? "<preFirst>" : data.toString();
        }
    }
}
