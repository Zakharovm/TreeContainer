package com.globallogic.test.tree.filter

import com.globallogic.test.tree.structure.TreeNode
import com.globallogic.test.tree.structure.Tree
import spock.lang.Specification

class TreePredicateTest extends Specification {

    def "FilterNodes"() {
        given:
        Tree<Integer> tree = new Tree<>()

        TreeNode<Integer> node1 = new TreeNode(1)
        TreeNode<Integer> node2 = new TreeNode(2)
        TreeNode<Integer> node3 = new TreeNode(3)
        TreeNode<Integer> node4 = new TreeNode(4)
        tree.setRoot(node1)
        node1.addChild(node2)
        node1.addChild(node3)
        node1.addChild(node4)

        TreePredicate<Integer> predicate = new TreePredicate<>()
        when:
        List<TreeNode<Integer>> resultList = predicate.filterNodes(tree.getAllNodes(), predicate.isLessThan(3))
        then :
        resultList == [node1, node2]
    }
}
