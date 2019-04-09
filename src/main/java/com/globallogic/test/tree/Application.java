package com.globallogic.test.tree;

import com.globallogic.test.tree.filter.TreePredicate;
import com.globallogic.test.tree.service.ConsoleNotificationListener;
import com.globallogic.test.tree.service.Operations;
import com.globallogic.test.tree.structure.*;

public class Application {

    public static void main(String[] args) throws MaxChildrenException {

        TreePredicate<Integer> predicate = new TreePredicate<>();
        TreePredicate<String> predicate2 = new TreePredicate<>();



        Tree<Integer> tree = new Tree<>();

        tree.events.subscribe(Operations.ADD_ELEMENT, new ConsoleNotificationListener<>());
        tree.events.subscribe(Operations.REMOVE_ELEMENT, new ConsoleNotificationListener<>());
        tree.events.subscribe(Operations.CHANGE_ELEMENT_NAME, new ConsoleNotificationListener<>());
        tree.setRoot(new TreeNode<Integer>(1));

        TreeNode<Integer> treeRoot = tree.getRoot();


        TreeNode<Integer> child45 = new TreeNode<>(11);

        TreeNode<Integer> child = new TreeNode<>(12);


        tree.addChildToParent(child45, treeRoot);
        tree.addChildToParent(child, treeRoot);
        child.addChild(111);


        tree.removeChildFromParent(child45, treeRoot);
        System.out.println(tree.exists(child45.getData()));

        child.addChild(333);
        child.addChild(3331);

        Tree<String> tree2 = new Tree<>();

        System.out.println(predicate.filterNodes(tree.getAllNodes(), predicate.isMoreThan(1) ));
    }
}