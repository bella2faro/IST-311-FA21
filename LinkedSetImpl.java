package edu.psu.ist311.asg5.disjset;

import edu.psu.ist311.asg5.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LinkedSetImpl<T> implements IDisjointSets<T> {

    private final Map<T, DNode<T>> contents = new LinkedHashMap<>();

    @Override
    public T singleton(T e) {
        if (e == null) {
            throw new IllegalArgumentException("attempt to add null element");
        }
        if (contents.containsKey(e)) {
            return e;
        }
        DNode<T> newNode = new DNode<>(e);
        contents.put(e, newNode);
        return e;
    }

    @Override
    public T find(T e) {
        DNode<T> node = contents.get(e);
        if (node == null) {
            throw new IllegalArgumentException("no such element");
        }
        DNode<T> rep = findRepWPC(node);
        return rep.data;
    }

    @Override
    public void union(T e1, T e2) {
        if (e1 == null || e2 == null) {
            throw new IllegalArgumentException("can't union null elements");
        }
        if (!(contents.containsKey(e1) && contents.containsKey(e2))) {
            throw new IllegalArgumentException("both elements must be present " +
                    "to union");
        }
        DNode<T> n1 = contents.get(e1);
        DNode<T> n2 = contents.get(e2);

        if (n1.equals(n2)) {
            return; //already in same set..
        }
        link(findRepWPC(n1), findRepWPC(n2));
    }

//    private DNode<T> findRepNaive(DNode<T> n) {
//        if (n == null) {
//            throw new IllegalArgumentException("DNode n is null");
//        }
//        DNode<T> result = n;
//        while (n != n.parent) {
//            result = result.parent;
//        }
//        return result;
//    }

    // finds and returns the representative node for n, but also performs path
    // compression while doing so...
    private DNode<T> findRepWPC(DNode<T> n) {
        if (n == null) {
            //this shouldn't happen since the representative at the root of
            //each tree should point to itself..
            throw new IllegalArgumentException("findRepWPC n is null");
        }

        if (!n.equals(n.parent)) {
            n.parent = findRepWPC(n.parent);
        }
        return n.parent;
    }

    private void link(DNode<T> a, DNode<T> b) {
        if (a.rank > b.rank) {
            b.parent = a; //make a root of b
        } else { // else x.rank <= y.rank
            a.parent = b;  //make b root of a
            if (a.rank == b.rank) {
                b.rank = b.rank + 1;
            }
        }
    }

    @Override
    public void clear() {
        contents.clear();
    }

    @Override
    public String toString() {
        List<String> nodesToRender = new ArrayList<>();
        for (DNode<T> n : contents.values()) {
            if (n.equals(n.parent)) {
                nodesToRender.add(n.toString());
            } else {
                List<String> chain = new ArrayList<>();

                DNode<T> curr = n;
                while (!curr.equals(curr.parent)) {
                    chain.add(curr.toString());
                    curr = curr.parent;
                }
                chain.add(curr.toString());
                if (!chain.isEmpty()) {
                    nodesToRender.add(Utils.join(chain, "->"));
                }
            }
        }
        return Utils.join(nodesToRender, "  ");
    }

}
