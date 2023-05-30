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
        return findLCA(root, n1, n2);
    }

    
    Node findLCA(Node node, String n1, String n2) {
        if (node == null) {
            return null;
        }
        // key becomes LCA
        if (node.input.equals(n1) || node.input.equals(n2)) { //if either input matches the root key
            return node;
        }

        Node left_lca = findLCA(node.left, n1, n2);
        Node right_lca = findLCA(node.right, n1, n2);

        if (left_lca != null && right_lca != null) {
            return node;
        }
        return (left_lca != null) ? left_lca : right_lca;
    }

    public static void main(String args[]) {
        TheGoldenSpirit tree = new TheGoldenSpirit();
        tree.root = new Node("George Joestar I and Mary Joestar");
        tree.root.left = new Node("Jonathan Joestar and Erina Joestar");
        tree.root.right = new Node("Jonathan Joestar and DIO");
        tree.root.left.left = new Node("George Joestar II and Lisa Lisa");
        tree.root.right.right = new Node("Giorno Giovanna");
        tree.root.left.left.left = new Node("Jospeh  Joestar and Suzi Q");
        tree.root.left.left.left.left = new Node("Holy Kujo and Sadao Kujo");
        tree.root.left.left.right = new Node("Jospeh Joestar and Tomoko Higashikata");
        tree.root.left.left.right.right = new Node("Josuke Higashikata");
        tree.root.left.left.left.left = new Node("Holy Kujo and Sadao Kujo");
        tree.root.left.left.left.left.left = new Node("Jotaro Kujo");
        tree.root.left.left.left.left.left.left = new Node("Jolyne Cujoh");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the first Joestar: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter the name of the second Joestar ");
        String name2 = scanner.nextLine();

        Node lca = tree.findLCA(name1, name2);
        if (lca != null) {
            System.out.printf("Lowest Common Ancestor of %s and %s is: %s" ,name1,name2, lca.input);
        } else {
            System.out.println("Name entered is not in the Joestar family.");
        }
    }
}
