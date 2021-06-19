package com.rc.datastructures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListTest {

    private LinkedList<String> list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList<>();
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

    @Test
    public void traversal() {
        List<String> expected = new ArrayList<>(Arrays.asList("Sinead", "Mick", "Andrew", "John"));
        List<String> result = new ArrayList<>();
        for (String data: list) {
            result.add(data);
        }

        Assert.assertEquals(expected, result);
    }
}