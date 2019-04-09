package com.globallogic.test.tree.filter;

import com.globallogic.test.tree.structure.TreeNode;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TreePredicate<T> {

    public Predicate<TreeNode<Integer>> isMoreThan(Integer value) {
        return p -> p.getData() > value;
    }

    public Predicate<TreeNode<Integer>> isLessThan(Integer value) {
        return p -> p.getData() < value;
    }

    public List<TreeNode<T>> filterNodes(List<TreeNode<T>> nodes,
                                             Predicate<TreeNode<T>> predicate) {
        return nodes.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
