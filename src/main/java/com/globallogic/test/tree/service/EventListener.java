package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;
import com.globallogic.test.tree.structure.TreeNode;


public interface EventListener<T> {

    void update(TreeNode<T> child, TreeNode<T> parent, Operations eventType);

}
