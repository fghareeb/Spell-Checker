package com.example;

/**
 * A simple linked list implementation with generic type.
 *
 * @param <T> the type of elements stored in the list
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    /**
     * Adds a new node to the end of the linked list.
     *
     * @param newNode the node to be added
     */
    public void add(Node<T> newNode) {
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    /**
     * Adds a new node after a specified target node.
     *
     * @param targetNode the node after which the new node will be added (can be
     *                   null for adding at the start)
     * @param newNode    the node to be added
     */
    public void addAfter(Node<T> targetNode, Node<T> newNode) {
        if (targetNode == null) {
            // If targetNode is null, you might want to add newNode at the start of the list
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (targetNode == tail) {
            // Adding after the tail
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            // Adding in the middle of the list
            Node<T> nextNode = targetNode.next;
            targetNode.next = newNode;
            newNode.prev = targetNode;
            newNode.next = nextNode;
            nextNode.prev = newNode;
        }
        size++;
    }

    /**
     * Removes a specified node from the linked list.
     *
     * @param nodeToRemove the node to be removed
     * @return true if the node is successfully removed, false otherwise
     */
    public boolean remove(Node<T> nodeToRemove) {
        if (nodeToRemove == null || head == null) {
            return false; // Nothing to remove if the node or list is empty
        }

        // Special case: removing the head node
        if (nodeToRemove == head) {
            if (head == tail) {
                // The list has only one node
                head = null;
                tail = null;
            } else {
                // Move the head to the next node
                head = head.next;
                head.prev = null;
            }
            size--;
            return true;
        }

        // General case: removing a node other than the head
        Node<T> current = head.next;
        while (current != null) {
            if (current == nodeToRemove) {
                current.prev.next = current.next;
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    // current is the tail
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }

        return false; // Node not found in the list
    }

    /**
     * Retrieves the head node of the linked list.
     *
     * @return the head node of the linked list
     */
    public Node getHead() {
        return head;
    }

    /**
     * Retrieves the size of the linked list.
     *
     * @return the size of the linked list
     */
    public int getSize() {
        return size;
    }

    /**
     * Converts the linked list to a string representation.
     *
     * @return a string representation of the linked list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());

            if (current.next != null) {

                if (current.getIsLast()) {
                    sb.append(current.getPunct() + " ");
                }

                if (current.getLine()) {
                    sb.append("\n");

                } else {
                    sb.append(" ");
                }

            }
            current = current.next;
        }
        return sb.toString();
    }

    /**
     * Searches for a node with the specified data in the linked list.
     *
     * @param data the data to search for
     * @return the node with the specified data, or null if not found
     */
    public Node<T> search(String data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.next;
        }
        return null; // Return null if no matching node is found
    }

}
