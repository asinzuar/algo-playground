package com.rc.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;

    public void addToHead(T data) {
        if (head == null) {
            head = new Node(data, null);
            return;
        }

        Node temp = head;
        head = new Node(data);
        head.next = temp;
    }

    public boolean remove(T data) {
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

    public boolean search(T data) {
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

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private static class Node<T> {
        final T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private class Itr implements Iterator<T> {
        Node cursor = head;

        Itr() {
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }

            Node ptr = cursor;
            cursor = cursor.next;
            return (T) ptr.data;
        }
    }
}