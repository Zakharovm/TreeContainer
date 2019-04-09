package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;

public class ConsoleNotificationListener<T> implements EventListener<T> {
    @Override
    public void update(Operations eventType, Tree<T> tree) {
        System.out.println("There was performed a(an) " + eventType + " operation with such tree: " + tree.toString());
    }
}
