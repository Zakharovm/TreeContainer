package com.globallogic.test.tree.filter;

import com.globallogic.test.tree.structure.Tree;
import com.globallogic.test.tree.structure.TreeNode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Search<T> {

    TreeNode<T> findNode(T data);

    Tree<T> getSubTree(TreeNode<T> parent);

    List<TreeNode<T>> filterNodes(List<TreeNode<T>> nodes, Predicate<TreeNode<T>> predicate);

}
