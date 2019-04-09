package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;


public interface EventListener<T> {
    void update(Operations eventType, Tree<T> tree);
}
