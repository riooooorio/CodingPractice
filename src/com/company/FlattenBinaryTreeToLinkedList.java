package com.company;

public class FlattenBinaryTreeToLinkedList {
    // use preOrder
//    public TreeNode flatten(TreeNode root) {
//        TreeNode[] prev = new TreeNode[1];
//        helper(root, prev);
//        return root;
//    }
//
//    private void helper(TreeNode root, TreeNode[] prev) {
//        if (root == null) {
//            return;
//        }
//        TreeNode leftNode = root.left;  // because leftChild can be changed
//        TreeNode rightNode = root.right;
//
//        if (prev[0] != null) {  // if: root is the 1st node to visit
//            prev[0].right = root;
//        }
//        root.left = null;   // OR = prev[0] if we need to make it a doubly linked list
//        prev[0] = root;
//
//        helper(leftNode, prev);
//        helper(rightNode, prev);
//    }

    // use post order
    public TreeNode flatten(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        helper(root, prev);
        return prev[0];
    }

    private void helper(TreeNode root, TreeNode[] prev) {
        if (root == null) {
            return;
        }
        helper(root.right, prev);
        helper(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }
}
