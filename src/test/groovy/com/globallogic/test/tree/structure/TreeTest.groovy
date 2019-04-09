package com.globallogic.test.tree.structure

import spock.lang.Shared
import spock.lang.Specification

class TreeTest extends Specification {

    @Shared
    def tree1
    @Shared
    def parent
    @Shared
    def child1
    @Shared
    def child2

    def setup() {
        tree1 = new Tree<TreeNode<Integer>>(new TreeNode<>(1))
        parent = new TreeNode<Integer>(10)
        child1 = new TreeNode<Integer>(5)
        child2 = new TreeNode<Integer>(45)
    }


    def "GetRootElement"() {
        def child = new TreeNode<Integer>(2)
        when:
        tree1.setRoot(child)
        then:
        tree1.getRoot() == child
    }

    def "AddChildToParent"() {


        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParent(45, 10)
        then:
        parent.getChildAt(0) == child1
        parent.getChildAt(1).getData() == child2.getData()
    }

    def "AddChildToParentAt"() {

        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParentAt(0, child2, parent)
        then:
        parent.getChildAt(0) == child2
    }


    def "RemoveChildFromParent"() {
        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParentAt(0, child2, parent)
        tree1.removeChildFromParent(child1, parent)
        then:
        parent.getChildren() == [child2]

    }

    def "FindNode"() {
        def child = new TreeNode<Integer>(344)
        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParentAt(0, child, parent)
        then:
        tree1.findNode(344) == child

    }

    def "FindNodeReturnNull"() {
        def child = new TreeNode<Integer>(344)
        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParentAt(0, child, parent)
        then:
        tree1.findNode(1000) == null
    }

    def "GetNumberOfNodes"() {
        def child = new TreeNode<Integer>(999)

        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)
        tree1.addChildToParentAt(0, child, parent)
        parent.addChild(1)
        parent.addChild(2)
        child.addChild(666)
        child.addChild(777)
        then:
        tree1.getNumberOfNodes() == 7
    }

    def "GetAllNodes"() {
        when:
        tree1.setRoot(parent)
        tree1.addChildToParent(child1, parent)

        then:
        tree1.getAllNodes() == [parent, child1]
    }

}
