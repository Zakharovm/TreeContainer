package com.globallogic.test.tree.filter;

import com.globallogic.test.tree.structure.Tree;
import com.globallogic.test.tree.structure.TreeNode;

import java.util.List;

public interface Search<T> {

    TreeNode<T> findNode (T data);

    Tree<T> getSubTree(TreeNode<T> parent);

}
