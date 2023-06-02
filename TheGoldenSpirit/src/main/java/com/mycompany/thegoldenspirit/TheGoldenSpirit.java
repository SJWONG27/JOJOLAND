/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.thegoldenspirit;

/**
 *
 * @author Hewlett Packard
 */
import java.util.Scanner;

class Node {
    String input;
    Node left, right;

    public Node(String item) {
        input = item;
        left = right = null;
    }
}

public class TheGoldenSpirit {
    Node root;

    Node findLCA(String n1, String n2) {
        return findLCAUtil(root, n1, n2);
    }

    Node findLCAUtil(Node node, String n1, String n2) {
        if (node == null) {
            return null;
        }

        if (node.left != null && (node.left.input.equals(n1) || node.left.input.equals(n2))) {
            return node;
        }

        if (node.right != null && (node.right.input.equals(n1) || node.right.input.equals(n2))) {
            return node;
        }

        Node leftLCA = findLCAUtil(node.left, n1, n2);
        Node rightLCA = findLCAUtil(node.right, n1, n2);

        if (leftLCA != null && rightLCA != null) {
            return node;
        }

        if (leftLCA != null) {
            return leftLCA;
        }

        if (rightLCA != null) {
            return rightLCA;
        }

        return null;
    }

    public static void main(String args[]) {
        TheGoldenSpirit tree = new TheGoldenSpirit();
        tree.root = new Node("George Joestar I and Mary Joestar");
        tree.root.left = new Node("Jonathan Joestar");
        tree.root.left.left = new Node("George Joestar II");
        tree.root.left.right = new Node("Giorno Giovanna");
        tree.root.left.left.left = new Node("Joseph Joestar");
        tree.root.left.left.left.left = new Node("Holy Kujo");
        tree.root.left.left.left.right = new Node("Josuke Higashikata");
        tree.root.left.left.left.left.left = new Node("Jotaro Kujo");
        tree.root.left.left.left.left.left.left = new Node("Jolyne Cujoh");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the first Joestar: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter the name of the second Joestar: ");
        String name2 = scanner.nextLine();

        Node lca = tree.findLCA(name1, name2);
        if (lca != null) {
            System.out.printf("Lowest Common Ancestor of %s and %s is: %s\n", name1, name2, lca.input);
        } else {
            System.out.println("Name entered is not in the Joestar family.");
        }
    }
}
