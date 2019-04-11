package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;
import com.globallogic.test.tree.structure.TreeNode;

public class ConsoleNotificationListener<T> implements EventListener<T> {

    @Override
    public void update(TreeNode<T> child, TreeNode<T> parent, Operations eventType) {
        T childData = null;

        if (child != null) {
            childData = child.getData();
        }

        if (eventType == Operations.ADD_ELEMENT) {
            System.out.println("Child = [" + childData + "] was added to a parent = " + parent.toStringAdditional());

        } else if (eventType == Operations.REMOVE_ELEMENT) {
            System.out.println("Child = [" + childData + "] was removed from a parent = " + parent.toStringAdditional());

        } else if (eventType == Operations.CHANGE_ELEMENT_NAME) {
            System.out.println("Element = [" + childData + "] was rename(set) to = [" + parent.getData() + "] ");
        }

    }
}
