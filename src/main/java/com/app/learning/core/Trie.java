package com.app.learning.core;

import java.util.*;

/*
 * Auto-complete using tries in java
 */
class Trie {
    @Override
    public String toString() {
        return "Trie{" +
                "root=" + root +
                '}';
    }

    private Node root;

    Trie() {
        root = new Node(' ');
    }

    /**
     * Add word.
     *
     * @param str    the str
     * @param weight the weight
     */
    void addWord(String str, final int weight) {
        int strLen = str.length();
        if (strLen == 0) {
            root.isEndOfString = true;
        } else {
            int i = 0;
            Node current = root;
            while (i < strLen) {
                char currentChar = str.toLowerCase().charAt(i);
                Node child = current.subNode(currentChar);
                if (child == null) {
                    child = new Node(currentChar);
                    current.childs.add(child);
                }
                current = child;
                i++;
            }
            current.isEndOfString = true;
            current.leafNodeStr = str.toLowerCase();
            current.weight = weight;
        }
    }

    Collection<String> getSuggestions(final String str) {
        Map<Integer, String> suggestions = new TreeMap<>(Comparator.reverseOrder());
        Node location = getLocationOfStringInTrie(str);
        if (location != null)
            autocomplete(location, suggestions);
        return suggestions.values();
    }

    private void autocomplete(final Node node, final Map<Integer, String> suggestions) {

        if (node.isEndOfString) {
            suggestions.put(node.weight, node.leafNodeStr);
        }
        for (Node childNode : node.childs) {
            autocomplete(childNode, suggestions);
        }

    }

    private Node getLocationOfStringInTrie(String str) {
        Node current = this.root;
        int strLen = str.length();
        int i = 0;
        while (i < strLen) {
            char currentChar = str.toLowerCase().charAt(i);
            Node child = current.subNode(currentChar);
            if (child != null) {
                current = child;
            } else {
                return null;
            }
            i++;
        }
        if (i == strLen) {
            return current;
        }
        return null;
    }


    boolean contains(String str) {
        int strLen = str.length();
        if (strLen == 0) {
            return true;
        } else {
            int i = 0;
            Node current = root;
            while (i < strLen) {
                Node child = current.subNode(str.charAt(i));
                if (child != null) {
                    current = child;
                } else {
                    return false;
                }
                i++;
            }
            return i == strLen;
        }
    }

    private static class Node {
        private char data;
        private boolean isEndOfString;
        private Collection<Node> childs;
        private String leafNodeStr;
        private int weight = 0;

        private Node(char data) {
            this.data = data;
            childs = new LinkedList<>();
            this.isEndOfString = false;
        }

        private Node subNode(char data) {
            if (childs != null) {
                for (Node childNode : childs) {
                    if (childNode.data == data) {
                        return childNode;
                    }
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", isEndOfString=" + isEndOfString +
                    ", childs=" + childs +
                    ", leafNodeStr='" + leafNodeStr + '\'' +
                    '}';
        }
    }


}
