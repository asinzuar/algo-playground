package com.rc.datastructures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList();
        list.addToHead("John");
        list.addToHead("Andrew");
        list.addToHead("Mick");
        list.addToHead("Sinead");
        //System.out.println(list);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToHead() {
        Assert.assertFalse(list.search("Quincy"));
        list.addToHead("Quincy");
        Assert.assertTrue(list.search("Quincy"));
    }

    @Test
    public void remove() {
        Assert.assertTrue(list.search("John"));
        Assert.assertTrue(list.remove("John"));
        Assert.assertFalse(list.search("John"));
    }

    @Test
    public void search() {
        Assert.assertTrue(list.search("Mick"));
        Assert.assertFalse(list.search("Horace"));
    }
}