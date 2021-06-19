package com.rc.datastructures;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinarySearchTreeInt {

    private Node root;
    private int size = 0;

    public BinarySearchTreeInt() {
    }

    public BinarySearchTreeInt(int rootData) {
        root = new Node(rootData);
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return;
        }

        _add(new Node(data), root);
        size++;
    }

    private void _add(Node newNode, Node root) {
        if (newNode.data < root.data) {
            if (root.getLeftChild() == null) {
                root.setLeftChild(newNode);
            } else {
                _add(newNode, root.getLeftChild());
            }
        } else if (newNode.data > root.data) {
            if (root.getRightChild() == null) {
                root.setRightChild(newNode);
            } else {
                _add(newNode, root.getRightChild());
            }
        } else {
            // element already exists
            return;
        }
    }

    public boolean search(int data) {
        return _search(data, root);
    }

    private boolean _search(int data, Node root) {
        if (root == null) {
            return false;
        }

        if (data < root.data) {
            return _search(data, root.getLeftChild());
        } else if (data > root.data) {
            return _search(data, root.getRightChild());
        } else {
            return true;
        }
    }

    public boolean remove(int data) {
        boolean removed = _remove(data, root, null, false);
        if (removed) {
            size--;
        }

        return removed;
    }

    private boolean _remove(int data, Node root, Node parent, boolean isLeftChild) {
        if (root == null) {
            return false;
        }

        if (data < root.data) {
            return _remove(data, root.getLeftChild(), root, true);
        } else if (data > root.data) {
            return _remove(data, root.getRightChild(), root, false);
        } else {
            Node leftChild = root.getLeftChild();
            Node rightChild = root.getRightChild();
            Node newRoot;

            if (leftChild != null) {
                // Make left child the new root
                newRoot = leftChild;

                // if left child has a right child, it needs to be relocated
                Node lrChild = leftChild.getRightChild();
                leftChild.setRightChild(root.getRightChild());
                if (lrChild != null) {
                    _add(lrChild, leftChild);
                }
            } else if (rightChild != null) {
                // Make right child the new root
                newRoot = rightChild;
            } else {
                newRoot = null;
            }

            if (parent == null) {
                // The node that was removed was root of the tree
                this.root = newRoot;
            } else {
                if (isLeftChild) {
                    parent.setLeftChild(newRoot);
                } else {
                    parent.setRightChild(newRoot);
                }
            }

            return true;
        }
    }

    public List<Integer> inorderTraversal() {
        return _inorderTraversal(root);
    }

    private List<Integer> _inorderTraversal(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new LinkedList<>();

        result.addAll(_inorderTraversal(root.getLeftChild()));
        result.add(root.data);
        result.addAll(_inorderTraversal(root.getRightChild()));

        return result;
    }

    public void prettyPrint() {
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        Map<Integer, Integer> indentMap = new HashMap<>();

        determineLevelAndIndent(root, 0, 0, levelMap, indentMap);

        int minIndent = Collections.min(indentMap.values());
        if (minIndent < 0) {
            int offset = -minIndent;
            indentMap.replaceAll((k, v) -> v + offset);
        }

        for (var entry: levelMap.entrySet()) {
            // Since they were populated using inorder traversal, they will always be
            // in order from left to right indent
            var levelEntries = entry.getValue();

            var indents = levelEntries.stream()
                    .map(indentMap::get)
                    .collect(Collectors.toList());

            List<Integer> posIncrements = new ArrayList<>();
            int previous = 0;
            for (int indent: indents) {
                posIncrements.add(indent - previous);
                previous = indent;
            }

            IntStream.range(0, levelEntries.size())
                    .forEach(i -> printAtOffset(levelEntries.get(i), posIncrements.get(i)));

            System.out.println();
        }
    }

    private void printAtOffset(int data, int indent) {
        IntStream.range(0, indent).forEach(n -> System.out.print("      "));
        System.out.print(data);
    }

    private void determineLevelAndIndent(Node root, int depth, int indent, Map<Integer, List<Integer>> levelMap, Map<Integer, Integer> indentMap) {
        if (root == null) {
            return;
        }

        determineLevelAndIndent(root.getLeftChild(), depth + 1, indent - 1, levelMap, indentMap);

        levelMap.computeIfAbsent(depth, k -> new ArrayList<>()).add(root.data);
        indentMap.put(root.data, indent);

        determineLevelAndIndent(root.getRightChild(), depth + 1, indent + 1, levelMap, indentMap);
    }

    public int getSize() {
        return size;
    }

    private static class Node {
        private final int data;
        private Node leftChild;
        private Node rightChild;

        Node(int data) {
            this.data = data;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }
    }
}
