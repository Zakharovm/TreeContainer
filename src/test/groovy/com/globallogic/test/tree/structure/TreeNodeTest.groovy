package com.globallogic.test.tree.structure

import com.globallogic.test.tree.MaxChildrenException
import spock.lang.Shared
import spock.lang.Specification

class TreeNodeTest extends Specification {
    @Shared
    def root1
    @Shared
    def root2
    @Shared
    def child1
    @Shared
    def child2
    @Shared
    def child3
    @Shared
    def childInt


    def setup() {
        root1 = new TreeNode<String>("root1")
        root2 = new TreeNode<String>("root2")
        child1 = new TreeNode<String>("child1")
        child2 = new TreeNode<String>("child2")
        child3 = new TreeNode<String>("child3")
        childInt = new TreeNode<Integer>(10)
    }

    def "GetData"() {

        expect:
        root1.getData() == "root1"
        childInt.getData() == 10
    }

    def "AddChild"() {
        when:
        root1.addChild(child1)
        then:
        child1.getParent() == root1
    }

    def "GetChildAt"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(1, child3)
        then:
        root1.getChildAt(1).getData() == child3.getData()

    }

    def "GetParent"() {
        when:
        root1.addChild(child1)
        then:
        child1.getParent() == root1
    }

    def "AddChildAt"() {
        given:
        TreeNode<String> child4 = new TreeNode<>("child4")
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(0, child3)
        root1.addChildAt(1, child4.getData())
        then:
        root1.getChildAt(0).getData() == child3.getData()
        root1.getChildAt(1).getData() == child4.getData()
    }

    def "AddChildAtThrowingMaxChildrenException"() {

        when:
        root1.setChildrenMaxQuantity(2)
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(0, child3)  // Adding more than 2 elements = exception is thrown
        then:
        MaxChildrenException e = thrown()
        root1.getChildrenMaxQuantity() == 2

    }


    def "RemoveChildAt"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(0, child3)
        root1.removeChildAt(0)
        then:
        root1.getChildren() == [child1, child2]
    }

    def "RemoveChildAtThrowingIndexOfBoundException"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(0, child3)
        root1.removeChildAt(4)
        then:
        IndexOutOfBoundsException e = thrown()
    }

    def "RemoveChild"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChildAt(0, child3)
        root1.removeChild(child3)
        then:
        root1.getChildren() == [child1, child2]

    }

    def "GetChildren"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        then:
        root1.getChildren() == [child1, child2]
    }

    def "SetChildren"() {
        given:
        List<TreeNode<String>> list = new ArrayList<>()
        when:
        list.add(child1)
        list.add(child2)
        root1.setChildren(list)
        then:
        root1.getChildren() == [child1, child2]
    }

    def "GetNumberOfChildren"() {
        when:
        root1.addChild(child1)
        root1.addChild(child2)
        root1.addChild(child3)
        then:
        root1.getNumberOfChildren() == 3

    }

    def "HasChildren"() {
        when:
        root1.addChild(child1)
        then:
        root1.hasChildren() == true
        root2.hasChildren() == false
    }

    def "IsEnoughSpace"() {
        when:
        root1.setChildrenMaxQuantity(1)
        root1.addChild(child1)
        then:
        root1.isEnoughSpace() == false
    }

    def "GetChildrenMaxQuantity"() {
        when:
        root1.setChildrenMaxQuantity(3)
        then:
        root1.getChildrenMaxQuantity() == 3
    }

}
