package com.globallogic.test.tree.service;

import com.globallogic.test.tree.structure.Tree;

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

    public void notifyListeners(Operations eventType, Tree<T> tree) {
        List<EventListener<T>> users = listeners.get(eventType);
        for (EventListener<T> user : users) {
            user.update(eventType, tree);
        }
    }
}
