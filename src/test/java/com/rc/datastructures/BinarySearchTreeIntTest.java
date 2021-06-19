package com.rc.datastructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BinarySearchTreeIntTest {

    BinarySearchTreeInt tree = new BinarySearchTreeInt();

    @Before
    public void setUp() throws Exception {
        tree.add(25);
        tree.add(15);
        tree.add(50);
        tree.add(45);
        tree.add(32);
    }

    @Test
    public void add() {
    }

    @Test
    public void search() {
        Assert.assertTrue(tree.search(45));
        Assert.assertFalse(tree.search(67));
    }

    @Test
    public void removeRoot() {
        tree.add(20);
        Assert.assertTrue(tree.remove(25));
        List<Integer> expected = List.of(15, 20, 32, 45, 50);
        Assert.assertEquals(expected, tree.inorderTraversal());

        tree.remove(50);
        expected = List.of(15, 20, 32, 45);
        Assert.assertEquals(expected, tree.inorderTraversal());
    }

    @Test
    public void remove() {
        tree.remove(50);
        List<Integer> expected = List.of(15, 25, 32, 45);
        Assert.assertEquals(expected, tree.inorderTraversal());
    }

    @Test
    public void inorderTraversal() {
        List<Integer> expected = List.of(15, 25, 32, 45, 50);
        Assert.assertEquals(expected, tree.inorderTraversal());
    }
}