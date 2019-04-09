package com.globallogic.test.tree.structure;

import com.globallogic.test.tree.MaxChildrenException;

import java.util.*;

public class TreeNode<T> {
    private int DEFAULT_CHILDREN_MAX_QUANTITY = 5;

    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;
    private int childrenMaxQuantity;

    public TreeNode() {
        this.childrenMaxQuantity = DEFAULT_CHILDREN_MAX_QUANTITY;
        this.children = new ArrayList<>();
    }

    public TreeNode(T data) {
        this();
        this.data = data;
    }

    public TreeNode(T data, int childrenMaxQuantity) {
        this();
        this.childrenMaxQuantity = childrenMaxQuantity;
        this.data = data;
    }


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getChildAt(int index) {
        return this.children.get(index);
    }

    public void addChild(T data) throws MaxChildrenException {
        if (isEnoughSpace()) {
            TreeNode<T> child = new TreeNode<>(data);
            child.parent = this;
            this.children.add(child);
        } else {
            throw new MaxChildrenException("[Error]: It is reached the maximum child quantity for this node [" + this.childrenMaxQuantity + "]. Element [" + data + "] was not added.");
        }


    }

    public void addChild(TreeNode<T> child) throws MaxChildrenException {
        if (isEnoughSpace()) {
            child.parent = this;
            this.children.add(child);
        } else {
            throw new MaxChildrenException("[Error]: It is reached the maximum child quantity for this node [" + this.childrenMaxQuantity + "]. Element [" + child.getData() + "] was not added.");
        }

    }

    public void addChildAt(int index, T data) throws IndexOutOfBoundsException, MaxChildrenException {

        if (isEnoughSpace()) {
            TreeNode<T> child = new TreeNode<>(data);
            child.parent = this;
            this.children.add(index, child);
        } else {
            throw new MaxChildrenException("[Error]: It is reached the maximum child quantity for this node [" + this.childrenMaxQuantity + "]. Element [" + data + "] was not added.");
        }

    }

    public void addChildAt(int index, TreeNode<T> child) throws IndexOutOfBoundsException, MaxChildrenException {

        if (isEnoughSpace()) {
            child.parent = this;
            this.children.add(index, child);
        } else {
            throw new MaxChildrenException("[Error]: It is reached the maximum child quantity for this node [" + this.childrenMaxQuantity + "]. Element [" + child.getData() + "] was not added.");
        }

    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public void removeChild(TreeNode<T> node) throws IndexOutOfBoundsException {
        this.children.remove(node);
    }

    public List<TreeNode<T>> getChildren() {
        return this.children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public boolean isEnoughSpace() {
        return (children.size() < childrenMaxQuantity);
    }

    public int getChildrenMaxQuantity() {
        return this.childrenMaxQuantity;
    }

    public void setChildrenMaxQuantity(int childrenMaxQuantity) {
        this.childrenMaxQuantity = childrenMaxQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return childrenMaxQuantity == treeNode.childrenMaxQuantity &&
                Objects.equals(data, treeNode.data) &&
                Objects.equals(parent, treeNode.parent) &&
                Objects.equals(children, treeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, parent, children, childrenMaxQuantity);
    }

    @Override
    public String toString() {
        return this.data + "";
    }

    public String toStringAdditional() {
        //Pre-Order

        String string = getData().toString();
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.append(":[");
        for (TreeNode<T> node : getChildren()) {
            if (getChildren().indexOf(node) < getChildren().size() - 1) {
                stringBuilder.append(node.toString()).append(", ");
            } else {
                stringBuilder.append(node.toString());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
