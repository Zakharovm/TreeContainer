package com.globallogic.test.tree.service;

import com.globallogic.test.tree.MaxChildrenException;
import com.globallogic.test.tree.structure.TreeNode;

public interface TreeUtils<T> {

    int getNumberOfNodes();

    void addChildToParent(T childData, T parentData) throws MaxChildrenException;

    void addChildToParent(T childData, TreeNode<T> parent) throws MaxChildrenException;

    void addChildToParent(TreeNode<T> child, T parentData) throws MaxChildrenException;

    void addChildToParent(TreeNode<T> child, TreeNode<T> parent) throws MaxChildrenException;

    void addChildToParentAt(int index, TreeNode<T> parent, TreeNode<T> child) throws MaxChildrenException;

    void addChildToParentAt(int index, T childData, T parentData) throws MaxChildrenException;

    void removeChildFromParent(TreeNode<T> parent, TreeNode<T> child) throws MaxChildrenException;

}

