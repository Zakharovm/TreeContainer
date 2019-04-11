package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;
import com.globallogic.test.tree.structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager<T> {
    private Map<Operations, List<EventListener<T>>> listeners = new HashMap<>();

    public EventManager(Operations... operations) {
        for (Operations operation : operations) {
            listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(Operations eventType, EventListener<T> listener) {
        List<EventListener<T>> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(Operations eventType, EventListener<T> listener) {
        List<EventListener<T>> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notifyListeners(TreeNode<T> child, TreeNode<T> parent, Operations eventType) {
        List<EventListener<T>> users = listeners.get(eventType);
        for (EventListener<T> user : users) {
            user.update(child, parent, eventType);
        }
    }
}
