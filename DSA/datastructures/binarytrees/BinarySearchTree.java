package DSA.datastructures.binarytrees;


import java.util.ArrayList;

public class BinarySearchTree {
    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    break;
                }
                current = current.left;
            }
            else {
                if (current.right == null) {
                    current.right = new Node(value);
                    break;
                }
                current = current.right;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (current.value > value)
                current = current.left;
            else if (current.value < value)
                current = current.right;
            else
                return true;
        }
        return false;
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {
        if (root == null)
            return;
        System.out.println(root.value);
        traversePreOrder(root.left);
        traversePreOrder(root.right);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {
        if (root == null)
            return;
        traversePreOrder(root.left);
        traversePreOrder(root.right);
        System.out.println(root.value);
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            return -1;

        if (isLeafNode(root))
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        if (isLeafNode(root))
            return root.value;
        var left = min(root.left);
        var right = min(root.right);

        return Math.min(Math.min(left, right), root.value);

    }

    public boolean equals(BinarySearchTree other) {
        if (other == null)
            return false;
        return equals(root, other.root);
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null)
            return true;

        if (first != null && second != null)
            return first.value == second.value
                    && equals(first.left, second.left)
                    && equals(first.right, second.right);

        return false;
    }

    public boolean isBst() {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(Node root, int min, int max) {
        if (root == null)
            return true;

        if (root.value < min || root.value > max)
            return false;

        return isBst(root.left, min, root.value-1) && isBst(root.right, root.value+1, max);
    }



    public ArrayList<Integer> getNodesAtDistance(int distance) {
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root, distance, list);
            return list;
    }


        private void getNodesAtDistance(Node root, int distance, ArrayList<Integer> list) {
        if (root == null)
            return;

        if (distance == 0) {
            list.add(root.value);
            return;
        }
            getNodesAtDistance(root.left, distance - 1, list);
            getNodesAtDistance(root.right, distance - 1, list);
    }
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node root) {
        if (root == null)
            return true;

        var balanceFactor = height(root.left) - height(root.right);

        return Math.abs(balanceFactor) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private boolean isLeafNode(Node node) {return node.left == null && node.right == null;}
}