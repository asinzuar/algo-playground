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

    public boolean remove(String data) {
        Node prevPtr = null;
        Node ptr = head;
        while (ptr != null) {
            if (ptr.data.equals(data)) {
                if (prevPtr == null) {
                    // remove head
                    head = head.next;
                } else {
                    prevPtr.next = ptr.next;
                }
                return true;
            }

            prevPtr = ptr;
            ptr = ptr.next;
        }

        return false;
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

    public String toString() {
        Node ptr = head;
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        while (ptr != null) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = false;
            }

            sb.append("(").append(ptr.data).append(", ").append(ptr.next).append(")");
            ptr = ptr.next;
        }

        return sb.toString();
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
