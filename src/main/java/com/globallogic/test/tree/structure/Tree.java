package com.globallogic.test.tree.structure;

import com.globallogic.test.tree.MaxChildrenException;
import com.globallogic.test.tree.filter.Search;
import com.globallogic.test.tree.service.EventManager;
import com.globallogic.test.tree.service.Operations;
import com.globallogic.test.tree.service.TreeUtils;

import java.util.*;

public class Tree<T> implements Search<T>, TreeUtils<T> {

    public EventManager<T> events;

    private TreeNode<T> root;

    public Tree() {
        this.events = new EventManager(Operations.ADD_ELEMENT, Operations.REMOVE_ELEMENT, Operations.CHANGE_ELEMENT_NAME);
    }

    public Tree(TreeNode<T> root) {
        this();
        this.root = root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
        events.notifyListeners(Operations.CHANGE_ELEMENT_NAME, this);
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    @Override
    public Tree<T> getSubTree(TreeNode<T> parent) {
        return new Tree<>(parent);
    }

    @Override
    public void addChildToParent(T childData, T parentData) throws MaxChildrenException {
        TreeNode<T> parent = findNode(parentData);
        parent.addChild(childData);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void addChildToParent(TreeNode<T> child, T parentData) throws MaxChildrenException {
        TreeNode<T> parent = findNode(parentData);
        parent.addChild(child);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void addChildToParent(T child, TreeNode<T> parent) throws MaxChildrenException {
        parent.addChild(child);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void addChildToParent(TreeNode<T> child, TreeNode<T> parent) throws MaxChildrenException {
        parent.addChild(child);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void addChildToParentAt(int index, TreeNode<T> child, TreeNode<T> parent) throws MaxChildrenException {
        parent.addChildAt(index, child);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void addChildToParentAt(int index, T childData, T parentData) throws MaxChildrenException {
        TreeNode<T> parent = findNode(parentData);
        parent.addChildAt(index, childData);
        events.notifyListeners(Operations.ADD_ELEMENT, this);
    }

    @Override
    public void removeChildFromParent(TreeNode<T> child, TreeNode<T> parent) {
        List<TreeNode<T>> toAddUtil = new ArrayList<>();
        int index;
        boolean found = false;

        for (int i = 0; i < parent.getChildren().size(); i++) {
            TreeNode<T> node = parent.getChildren().get(i);
            if (child.equals(node)) {
                found = true;
                index = i;
                parent.setChildrenMaxQuantity(parent.getChildrenMaxQuantity() + node.getChildren().size() - 1);
                for (TreeNode<T> childNode : node.getChildren()) {
                    childNode.setParent(parent);
                    toAddUtil.add(0, childNode);
                }
                parent.removeChildAt(i);
                for (TreeNode<T> element : toAddUtil) {
                    parent.getChildren().add(index, element);
                }
                events.notifyListeners(Operations.REMOVE_ELEMENT, this);
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("[" + child.getData() + "] is absent in that parent");
        }

    }

    public TreeNode<T> findNode(T dataToFind) {
        TreeNode<T> returnNode = null;

        if (root != null) {
            returnNode = privateFindNode(root, dataToFind);
        }
        if (returnNode == null) {
            return null;
        }
        return returnNode;
    }

    private TreeNode<T> privateFindNode(TreeNode<T> currentNode, T dataToFind) {
        TreeNode<T> returnNode = null;
        int i;
        if (currentNode.getData().equals(dataToFind)) {
            return currentNode;
        } else if (currentNode.hasChildren()) {
            i = 0;
            while (returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = privateFindNode(currentNode.getChildAt(i), dataToFind);
                i++;
            }
        }
        return returnNode;
    }

    public boolean exists(T dataToFind) {
        return (findNode(dataToFind) != null);
    }

    @Override
    public int getNumberOfNodes() {
        int numberOfNodes = 0;

        if (root != null) {
            numberOfNodes = privateGetNumberOfNodes(root) + 1;
        }

        return numberOfNodes;
    }

    private int privateGetNumberOfNodes(TreeNode<T> node) {
        int numberOfNodes = node.getNumberOfChildren();

        for (TreeNode<T> child : node.getChildren()) {
            numberOfNodes += privateGetNumberOfNodes(child);
        }

        return numberOfNodes;
    }

    public List<TreeNode<T>> getAllNodes() {
        List<TreeNode<T>> resultList = new ArrayList<>();
        resultList.add(root);
        for (TreeNode<T> child : root.getChildren()) {
            preOrder(child, resultList);
        }
        return resultList;
    }

    @Override
    public String toString() {

        return getRoot().toStringAdditional();
    }

    public void preOrder(TreeNode<T> node, List<TreeNode<T>> resultList) {
        resultList.add(node);
        for (TreeNode<T> child : node.getChildren()) {
            preOrder(child, resultList);
        }

    }

    public void postOrder(TreeNode<T> node, List<TreeNode<T>> resultList) {
        for (TreeNode<T> child : node.getChildren()
        ) {
            preOrder(child, resultList);
        }
        resultList.add(node);

    }

}
