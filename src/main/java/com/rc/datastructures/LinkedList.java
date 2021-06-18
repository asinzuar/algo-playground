package com.rc.datastructures;

public class LinkedList {
    private Node head;

    public void addToHead(String data) {
        if (head == null) {
            head = new Node(data, null);
            return;
        }

        Node temp = head;
        head = new Node(data);
        head.next = temp;
    }

    public void remove(String data) {

    }

    public boolean search(String data) {
        Node ptr = head;
        while (ptr != null) {
            if (ptr.data.equals(data)) {
                return true;
            } else {
                ptr = ptr.next;
            }
        }

        return false;
    }
}

class Node {
    final String data;
    Node next;

    public Node(String data) {
        this.data = data;
    }

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
}
