package com.globallogic.test.tree.service

import com.globallogic.test.tree.structure.Tree
import com.globallogic.test.tree.structure.TreeNode
import spock.lang.Specification

class EventManagerTest extends Specification {

    Tree<Integer> tree = new Tree<>()
    TreeNode<Integer> node = new TreeNode<>(1)
    def listener = Mock(ConsoleNotificationListener)

    def "Subscribe"() {
        setup:
        tree.setRoot(node)
        tree.events.subscribe(Operations.ADD_ELEMENT, listener)
        tree.events.subscribe(Operations.CHANGE_ELEMENT_NAME, listener)
        tree.events.subscribe(Operations.REMOVE_ELEMENT, listener)
        when:
        tree.addChildToParent(123, 1)
        tree.addChildToParent(1213, 1)
        tree.removeChildFromParent(tree.findNode(1213), tree.findNode(1))
        then:
        2 * listener.update(Operations.ADD_ELEMENT, _)
        0 * listener.update(Operations.CHANGE_ELEMENT_NAME, _)
        1 * listener.update(Operations.REMOVE_ELEMENT, _)

    }

    def "Unsubscribe"() {
        setup:
        tree.setRoot(node)
        tree.events.subscribe(Operations.ADD_ELEMENT, listener)
        when:
        tree.events.unsubscribe(Operations.ADD_ELEMENT, listener)
        tree.addChildToParent(123, 1)
        tree.addChildToParent(1213, 1)
        then:
        0 * listener.update(Operations.ADD_ELEMENT, _)

    }

    def "NotifyListeners"() {
        setup:
        tree.setRoot(node)
        tree.events.subscribe(Operations.ADD_ELEMENT, listener)
        when:
        tree.addChildToParent(123, 1)
        then:
        1 * listener.update(Operations.ADD_ELEMENT, _)

    }
}
